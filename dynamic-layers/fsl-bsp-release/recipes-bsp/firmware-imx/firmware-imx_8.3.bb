# Copyright (C) 2012-2016 Freescale Semiconductor
# Copyright (C) 2018 O.S. Systems Software LTDA.
# Copyright 2017-2018 NXP

SUMMARY = "Freescale IMX firmware"
DESCRIPTION = "Freescale IMX firmware such as for the VPU"
SECTION = "base"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://COPYING;md5=72c0f70181bb6e83eee6aab8de12a9f3"

PE = "1"

SRCBRANCH ?= "master"

#BRCM firmware v1.141.100.6
IMX_FIRMWARE_SRC ?= "git://github.com/NXP/imx-firmware.git;protocol=https"
SRC_URI = "${FSL_MIRROR}/firmware-imx-${PV}.bin;fsl-eula=true \
           ${IMX_FIRMWARE_SRC};branch=${SRCBRANCH};destsuffix=${S}/git "

# SRC_URI[md5sum] = "b8ae7cdd2a648168f1c90471ca87f6ba"
# SRC_URI[sha256sum] = "63ec62f5d229cbed00918c8449173933f1c9d594c59396b8dd217e94f47138b0"
SRC_URI[md5sum] = "776c7fa64a6e96d9f0d9cd50bbf79ffc"
SRC_URI[sha256sum] = "48e4b9e4064930e2b6cd7f20f39177c8f4c5ef1a296f2ebc6ed422412ab56681"

#BRCM firmware git
SRCREV = "8ce9046f5058fdd2c5271f86ccfc61bc5a248ae3"

inherit fsl-eula-unpack allarch

do_install() {

    install -d ${D}${base_libdir}/firmware/imx

    install -d ${D}${sysconfdir}/firmware

    cp -rfv firmware/* ${D}${base_libdir}/firmware/

    # Install SDMA Firmware: sdma-imx6q.bin & sdma-imx7d.bin into lib/firmware/imx/sdma
    install -d ${D}${base_libdir}/firmware/imx/sdma
    mv ${D}${base_libdir}/firmware/sdma/sdma-imx6q.bin ${D}${base_libdir}/firmware/imx/sdma
    mv ${D}${base_libdir}/firmware/sdma/sdma-imx7d.bin ${D}${base_libdir}/firmware/imx/sdma

    # No need to do install for ddr & hdmi binaries
    if [ -d ${D}${base_libdir}/firmware/ddr ]; then
        rm -rf ${D}${base_libdir}/firmware/ddr
    fi
    if [ -d ${D}${base_libdir}/firmware/hdmi ]; then
        rm -rf ${D}${base_libdir}/firmware/hdmi
    fi

    # Don't install seco related binary
    if [ -d ${D}${base_libdir}/firmware/seco ]; then
        rm -rf ${D}${base_libdir}/firmware/seco
    fi

    mv ${D}${base_libdir}/firmware/epdc/ ${D}${base_libdir}/firmware/imx/epdc/
    mv ${D}${base_libdir}/firmware/imx/epdc/epdc_ED060XH2C1.fw.nonrestricted ${D}${base_libdir}/firmware/imx/epdc/epdc_ED060XH2C1.fw

    mv ${D}${base_libdir}/firmware/easrc/ ${D}${base_libdir}/firmware/imx/easrc/

    find ${D}${base_libdir}/firmware -type f -exec chmod 644 '{}' ';'
    find ${D}${base_libdir}/firmware -type f -exec chown root:root '{}' ';'

    # Remove files not going to be installed
    find ${D}${base_libdir}/firmware/ -name '*.mk' -exec rm '{}' ';'
}

IS_MX8 = "0"
IS_MX8_mx8m = "8m"
IS_MX8_mx8qm = "8qm"
IS_MX8_mx8qxp = "8qx"
inherit deploy
addtask deploy before do_build after do_install
do_deploy () {
    # Deploy i.MX8 related firmware files
    if [ "${IS_MX8}" = "8m" ]; then
        # Deploy ddr/synopsys
        for ddr_firmware in ${DDR_FIRMWARE_NAME}; do
            install -m 0644 ${S}/firmware/ddr/synopsys/${ddr_firmware} ${DEPLOYDIR}
        done

        # Deploy hdmi/cadence
        install -m 0644 ${S}/firmware/hdmi/cadence/signed_dp_imx8m.bin ${DEPLOYDIR}
        install -m 0644 ${S}/firmware/hdmi/cadence/signed_hdmi_imx8m.bin ${DEPLOYDIR}
    elif [ "${IS_MX8}" = "8qm" ]; then
        # Deploy hdmi/cadence
        install -m 0644 ${S}/firmware/hdmi/cadence/hdmitxfw.bin ${DEPLOYDIR}
        install -m 0644 ${S}/firmware/hdmi/cadence/hdmirxfw.bin ${DEPLOYDIR}
        install -m 0644 ${S}/firmware/hdmi/cadence/dpfw.bin ${DEPLOYDIR}
        # Deploy seco
        install -m 0644 ${S}/firmware/seco/mx8qm-ahab-container.img ${DEPLOYDIR}

    elif [ "${IS_MX8}" = "8qx" ]; then
        # Deploy seco
        install -m 0644 ${S}/firmware/seco/mx8qx-ahab-container.img ${DEPLOYDIR}
    fi
}

python populate_packages_prepend() {
    vpudir = bb.data.expand('${base_libdir}/firmware/vpu', d)
    do_split_packages(d, vpudir, '^vpu_fw_([^_]*).*\.bin',
                      output_pattern='firmware-imx-vpu-%s',
                      description='Freescale IMX Firmware %s',
                      extra_depends='',
                      prepend=True)
    sdmadir = bb.data.expand('${base_libdir}/firmware/sdma', d)
    do_split_packages(d, sdmadir, '^sdma-([^-]*).*\.bin',
                      output_pattern='firmware-imx-sdma-%s',
                      description='Freescale IMX Firmware %s',
                      extra_depends='',
                      prepend=True)
}

ALLOW_EMPTY_${PN} = "1"

PACKAGES_DYNAMIC = "${PN}-vpu-* ${PN}-sdma-*"

PACKAGES =+ "${PN}-epdc ${PN}-scfw ${PN}-sdma ${PN}-easrc"

FILES_${PN}-epdc = "${base_libdir}/firmware/imx/epdc/"
FILES_${PN}-brcm = "${base_libdir}/firmware/bcm/*/*.bin ${base_libdir}/firmware/bcm/*/*.cal ${sysconfdir}/firmware/"
FILES_${PN}-scfw = "${base_libdir}/firmware/scfw/"
FILES_${PN}-sdma = " ${base_libdir}/firmware/imx/sdma"
FILES_${PN}-easrc = "${base_libdir}/firmware/imx/easrc/"
