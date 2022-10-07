# Copyright (C) 2017-2020 NXP
# Copyright (C) 2020-2022 TQ-Systems GmbH

require imx-mkimage-tq_git.inc

DESCRIPTION = "Generate Boot Stream for i.MX 8 device"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"
SECTION = "BSP"

inherit use-imx-security-controller-firmware

BOOT_TOOLS = "imx-boot-tools"

IMX_EXTRA_FIRMWARE        = "firmware-imx-8 imx-sc-firmware imx-seco"
IMX_EXTRA_FIRMWARE:append = " ${@bb.utils.contains('IMXBOOT_TARGETS', 'flash_linux_m4', 'virtual/imx-cortexm-demos', '', d)}"
IMX_EXTRA_FIRMWARE:mx8m-generic-bsp   = "firmware-imx-8m"
IMX_EXTRA_FIRMWARE:mx8x-generic-bsp   = "imx-sc-firmware imx-seco"

DEPENDS += "\
    u-boot \
    ${IMX_EXTRA_FIRMWARE} \
    imx-atf \
    ${@bb.utils.contains('MACHINE_FEATURES', 'optee', 'optee-os', '', d)} \
"

# xxd is a dependency of fspi_packer.sh
DEPENDS:append = "\
    xxd-native \
"

# imx8m needs mkimage and dtc for ITB images
DEPENDS:append:mx8m-generic-bsp = "\
    u-boot-mkimage-native \
    dtc-native \
"

BOOT_NAME = "imx-boot"
PROVIDES = "${BOOT_NAME}"

inherit deploy

# Add CFLAGS with native INCDIR & LIBDIR for imx-mkimage build
CFLAGS = "-O2 -Wall -std=c99 -I ${STAGING_INCDIR_NATIVE} -L ${STAGING_LIBDIR_NATIVE}"

M4_DEFAULT_IMAGE    ??= "INVALID"
M4_1_DEFAULT_IMAGE  ??= "INVALID"

# This package aggregates output deployed by other packages,
# so set the appropriate dependencies
do_compile[depends] += "\
    virtual/bootloader:do_deploy \
    ${@' '.join('%s:do_deploy' % r for r in '${IMX_EXTRA_FIRMWARE}'.split() )} \
    imx-atf:do_deploy \
    ${@bb.utils.contains('MACHINE_FEATURES', 'optee', 'optee-os:do_deploy', '', d)} \
"

SC_FIRMWARE_NAME ?= "scfw_tcm.bin"

ATF_MACHINE_NAME ?= "bl31-${ATF_PLATFORM}.bin"
ATF_MACHINE_NAME:append = "${@bb.utils.contains('MACHINE_FEATURES', 'optee', '-optee', '', d)}"

UBOOT_NAME = "u-boot-${MACHINE}.bin-${UBOOT_CONFIG}"
UBOOT_SPL_NAME = "${@os.path.basename(d.getVar("SPL_BINARY"))}-${MACHINE}-${UBOOT_CONFIG}"

TOOLS_NAME ?= "mkimage_imx8"

IMX_BOOT_SOC_TARGET       ??= "INVALID"

DEPLOY_OPTEE = "${@bb.utils.contains('MACHINE_FEATURES', 'optee', 'true', 'false', d)}"

IMXBOOT_TARGETS ??= "unknown"

BOOT_STAGING       = "${S}/${IMX_BOOT_SOC_TARGET}"
BOOT_STAGING:mx8m-generic-bsp  = "${S}/iMX8M"
BOOT_STAGING:mx8dx-generic-bsp = "${S}/iMX8QX"

SOC_FAMILY      = "INVALID"
SOC_FAMILY:mx8-generic-bsp  = "mx8"
SOC_FAMILY:mx8m-generic-bsp = "mx8m"
SOC_FAMILY:mx8x-generic-bsp = "mx8x"

REV_OPTION ?= ""
REV_OPTION:mx8qxp-generic-bsp = \
    "${@bb.utils.contains('MACHINE_FEATURES', 'soc-revb0', '', \
                                                           'REV=C0', d)}"

##
# do assignment for TQMa8Xx[S] / TQMa8x SOM to enable bootstream with M4 demo
##
M4_DEFAULT_IMAGE:tqma8xx ?= "rpmsg_lite_pingpong_rtos_linux_remote.bin"
M4_DEFAULT_IMAGE:tqma8xxs ?= "rpmsg_lite_pingpong_rtos_linux_remote.bin"

M4_DEFAULT_IMAGE:tqma8x ?= "rpmsg_lite_pingpong_rtos_linux_remote_m40.bin"
M4_1_DEFAULT_IMAGE:tqma8x ?= "rpmsg_lite_pingpong_rtos_linux_remote_m41.bin"

compile_mx8m() {
    bbnote 8MQ/8MM/8MN/8MP boot binary build
    for ddr_firmware in ${DDR_FIRMWARE_NAME}; do
        bbnote "Copy ddr_firmware: ${ddr_firmware} from ${DEPLOY_DIR_IMAGE} -> ${BOOT_STAGING} "
        cp ${DEPLOY_DIR_IMAGE}/${ddr_firmware}               ${BOOT_STAGING}
    done
    cp ${DEPLOY_DIR_IMAGE}/signed_dp_imx8m.bin               ${BOOT_STAGING}
    cp ${DEPLOY_DIR_IMAGE}/signed_hdmi_imx8m.bin             ${BOOT_STAGING}
    for type in ${UBOOT_CONFIG}; do
        for dtb in ${UBOOT_DTB_NAME}; do
            cp ${DEPLOY_DIR_IMAGE}/${BOOT_TOOLS}/${dtb}-${MACHINE}-${type} \
                                                             ${BOOT_STAGING}/${dtb}-${type}
        done
        cp ${DEPLOY_DIR_IMAGE}/u-boot-spl.bin-${MACHINE}-${type} \
                                                             ${BOOT_STAGING}/u-boot-spl.bin-${type}
        cp ${DEPLOY_DIR_IMAGE}/${BOOT_TOOLS}/u-boot-nodtb.bin-${MACHINE}-${type} \
                                                             ${BOOT_STAGING}/u-boot-nodtb.bin-${type}
        cp ${DEPLOY_DIR_IMAGE}/u-boot-${MACHINE}.bin-${type} ${BOOT_STAGING}/u-boot.bin-${type}
    done
}

compile_mx8() {
    bbnote 8QM boot binary build
    cp ${DEPLOY_DIR_IMAGE}/${BOOT_TOOLS}/${SC_FIRMWARE_NAME} ${BOOT_STAGING}/scfw_tcm.bin
    cp ${DEPLOY_DIR_IMAGE}/${SECO_FIRMWARE_NAME}             ${BOOT_STAGING}
    if [ "$1" = "flash_linux_m4" ]; then
        cp ${DEPLOY_DIR_IMAGE}/${M4_DEFAULT_IMAGE}           ${BOOT_STAGING}/m4_image.bin
        cp ${DEPLOY_DIR_IMAGE}/${M4_1_DEFAULT_IMAGE}           ${BOOT_STAGING}/m4_1_image.bin
    fi
    for type in ${UBOOT_CONFIG}; do
        cp ${DEPLOY_DIR_IMAGE}/u-boot-${MACHINE}.bin-${type} ${BOOT_STAGING}/u-boot.bin-${type}
        if [ -e ${DEPLOY_DIR_IMAGE}/u-boot-spl.bin-${MACHINE}-${type} ] ; then
            cp ${DEPLOY_DIR_IMAGE}/u-boot-spl.bin-${MACHINE}-${type} \
                                                             ${BOOT_STAGING}/u-boot-spl.bin-${type}
        fi
    done
}

compile_mx8x() {
    bbnote 8QX boot binary build
    if [ "$1" = "flash_linux_m4" ]; then
        cp ${DEPLOY_DIR_IMAGE}/${M4_DEFAULT_IMAGE}           ${BOOT_STAGING}/m4_image.bin
    fi
    cp ${DEPLOY_DIR_IMAGE}/${SECO_FIRMWARE_NAME}             ${BOOT_STAGING}
    cp ${DEPLOY_DIR_IMAGE}/${BOOT_TOOLS}/${SC_FIRMWARE_NAME} ${BOOT_STAGING}/scfw_tcm.bin
    for type in ${UBOOT_CONFIG}; do
        cp ${DEPLOY_DIR_IMAGE}/u-boot-${MACHINE}.bin-${type} ${BOOT_STAGING}/u-boot.bin-${type}
        if [ -e ${DEPLOY_DIR_IMAGE}/u-boot-spl.bin-${MACHINE}-${type} ] ; then
            cp ${DEPLOY_DIR_IMAGE}/u-boot-spl.bin-${MACHINE}-${type} \
                                                             ${BOOT_STAGING}/u-boot-spl.bin-${type}
        fi
    done
}

do_compile() {
    # mkimage for i.MX8
    # Copy TEE binary to SoC target folder to mkimage
    if ${DEPLOY_OPTEE}; then
        cp ${DEPLOY_DIR_IMAGE}/tee.bin ${BOOT_STAGING}
    fi
    cp ${DEPLOY_DIR_IMAGE}/${BOOT_TOOLS}/${ATF_MACHINE_NAME} ${BOOT_STAGING}/bl31.bin
    for target in ${IMXBOOT_TARGETS}; do
        for config in ${UBOOT_CONFIG}; do
            compile_${SOC_FAMILY} "$target"
            allbins="u-boot.bin u-boot-nodtb.bin u-boot-spl.bin"
            for bin in ${allbins} ; do
                if [ -e "${BOOT_STAGING}/${bin}" ]; then
                    rm ${BOOT_STAGING}/${bin}
                fi
                if [ -e "${BOOT_STAGING}/${bin}-${config}" ]; then
                    ln -s ${bin}-${config} ${BOOT_STAGING}/${bin}
                fi
            done
            #bbnote "SOC_FAMILY is "${SOC_FAMILY}"
            if [ "${SOC_FAMILY}" = "mx8m" ] ; then
                for dtb in ${UBOOT_DTB_NAME}; do
                    if [ -e "${BOOT_STAGING}/${dtb}" ]; then
                        rm ${BOOT_STAGING}/${dtb}
                    fi
                    if [ -e "${BOOT_STAGING}/${dtb}-${config}" ]; then
                        ln -s ${dtb}-${config} ${BOOT_STAGING}/${dtb}
                    fi
                done
            fi

            oe_runmake clean

            if [ "$target" = "flash_linux_m4_no_v2x" ]; then
                # Special target build for i.MX 8DXL with V2X off
                bbnote "building ${IMX_BOOT_SOC_TARGET} - ${REV_OPTION} V2X=NO ${target}"
                oe_runmake SOC=${IMX_BOOT_SOC_TARGET} ${REV_OPTION} V2X=NO dtbs=${UBOOT_DTB_NAME} flash_linux_m4
            else
                bbnote "building ${IMX_BOOT_SOC_TARGET} - ${REV_OPTION} ${target}"
                oe_runmake SOC=${IMX_BOOT_SOC_TARGET} ${REV_OPTION} dtbs=${UBOOT_DTB_NAME} ${target}
            fi

            if [ -e "${BOOT_STAGING}/flash.bin" ]; then
                cp ${BOOT_STAGING}/flash.bin ${S}/${BOOT_NAME}-${MACHINE}-${config}.bin-${target}
            fi
        done
    done
}

do_install () {
    install -d ${D}/boot
    for target in ${IMXBOOT_TARGETS}; do
        for type in ${UBOOT_CONFIG}; do
            install -m 0644 ${S}/${BOOT_NAME}-${MACHINE}-${type}.bin-${target} ${D}/boot/
        done
    done
}

deploy_mx8m() {
    install -d ${DEPLOYDIR}/${BOOT_TOOLS}
    for type in ${UBOOT_CONFIG}; do
        install -m 0644 ${DEPLOY_DIR_IMAGE}/u-boot-spl.bin-${MACHINE}-${type} \
                                                             ${DEPLOYDIR}/${BOOT_TOOLS}
    done
    for ddr_firmware in ${DDR_FIRMWARE_NAME}; do
        install -m 0644 ${DEPLOY_DIR_IMAGE}/${ddr_firmware}  ${DEPLOYDIR}/${BOOT_TOOLS}
    done
    install -m 0644 ${BOOT_STAGING}/signed_dp_imx8m.bin      ${DEPLOYDIR}/${BOOT_TOOLS}
    install -m 0644 ${BOOT_STAGING}/signed_hdmi_imx8m.bin    ${DEPLOYDIR}/${BOOT_TOOLS}
    install -m 0755 ${BOOT_STAGING}/${TOOLS_NAME}            ${DEPLOYDIR}/${BOOT_TOOLS}
    install -m 0755 ${BOOT_STAGING}/mkimage_fit_atf.sh       ${DEPLOYDIR}/${BOOT_TOOLS}
}

deploy_mx8() {
    install -d ${DEPLOYDIR}/${BOOT_TOOLS}
    install -m 0644 ${BOOT_STAGING}/${SECO_FIRMWARE_NAME}    ${DEPLOYDIR}/${BOOT_TOOLS}
    install -m 0755 ${S}/${TOOLS_NAME}                       ${DEPLOYDIR}/${BOOT_TOOLS}
    for type in ${UBOOT_CONFIG}; do
        if [ -e ${DEPLOY_DIR_IMAGE}/u-boot-spl.bin-${MACHINE}-${type} ] ; then
            install -m 0644 ${DEPLOY_DIR_IMAGE}/u-boot-spl.bin-${MACHINE}-${type} \
                                                             ${DEPLOYDIR}/${BOOT_TOOLS}
        fi
    done
}

deploy_mx8x() {
    install -d ${DEPLOYDIR}/${BOOT_TOOLS}
    install -m 0644 ${BOOT_STAGING}/${SECO_FIRMWARE_NAME}    ${DEPLOYDIR}/${BOOT_TOOLS}
    install -m 0755 ${S}/${TOOLS_NAME}                       ${DEPLOYDIR}/${BOOT_TOOLS}
    for type in ${UBOOT_CONFIG}; do
        if [ -e ${DEPLOY_DIR_IMAGE}/u-boot-spl.bin-${MACHINE}-${type} ] ; then
            install -m 0644 ${DEPLOY_DIR_IMAGE}/u-boot-spl.bin-${MACHINE}-${type} \
                                                             ${DEPLOYDIR}/${BOOT_TOOLS}
        fi
    done
}

do_deploy() {
    deploy_${SOC_FAMILY}

    # copy the tool mkimage to deploy path and sc fw, dcd and uboot
    for type in ${UBOOT_CONFIG}; do
        install -m 0644 ${DEPLOY_DIR_IMAGE}/u-boot-${MACHINE}.bin-${type} \
                                                             ${DEPLOYDIR}/${BOOT_TOOLS}
    done
    # copy tee.bin to deploy path
    if ${DEPLOY_OPTEE}; then
       install -m 0644 ${DEPLOY_DIR_IMAGE}/tee.bin ${DEPLOYDIR}/${BOOT_TOOLS}
    fi

    # copy makefile (soc.mak) for reference
    install -m 0644 ${BOOT_STAGING}/soc.mak                  ${DEPLOYDIR}/${BOOT_TOOLS}
    # copy the generated boot images to deploy path
    for target in ${IMXBOOT_TARGETS}; do
        for type in ${UBOOT_CONFIG}; do
            install -m 0644 ${S}/${BOOT_NAME}-${MACHINE}-${type}.bin-${target} \
                                                             ${DEPLOYDIR}
        done
    done
}
addtask deploy before do_build after do_compile

PACKAGE_ARCH = "${MACHINE_ARCH}"
FILES:${PN} = "/boot"

COMPATIBLE_MACHINE = "(mx8-generic-bsp)"
