# Copyright (C) 2017-2020 NXP
# Copyright (C) 2020-2022 TQ-Systems GmbH

require imx-mkimage-tq_git.inc

DESCRIPTION = "Generate Boot Stream for i.MX 8 device"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"
SECTION = "BSP"

# This is needed to set SECO_FIRMWARE_NAME for imx8 / imx8x
inherit use-imx-security-controller-firmware

IMX_EXTRA_FIRMWARE = "\
    firmware-imx-8 \
    imx-seco \
    imx-sc-firmware \
"

IMX_EXTRA_FIRMWARE:mx8m-generic-bsp = "firmware-imx-8m"

IMX_EXTRA_FIRMWARE:mx8x-generic-bsp = "\
    imx-seco \
    imx-sc-firmware \
"

IMX_EXTRA_FIRMWARE:mx9-generic-bsp = "\
    firmware-imx-8 \
    firmware-sentinel \
"

IMX_EXTRA_FIRMWARE:append = " ${@bb.utils.contains('IMXBOOT_TARGETS', 'flash_linux_m4', 'virtual/imx-cortexm-demos', '', d)}"

DEPENDS += "\
    ${IMX_EXTRA_FIRMWARE} \
    imx-atf \
    ${@bb.utils.contains('MACHINE_FEATURES', 'optee', 'optee-os', '', d)} \
    u-boot \
    xxd-native \
"

# imx8m needs mkimage and dtc for ITB images
DEPENDS:append:mx8m-generic-bsp = "\
    dtc-native \
    u-boot-mkimage-native \
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
    imx-atf:do_deploy \
    ${@' '.join('%s:do_deploy' % r for r in '${IMX_EXTRA_FIRMWARE}'.split() )} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'optee', 'optee-os:do_deploy', '', d)} \
    virtual/bootloader:do_deploy \
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

BOOT_STAGING = "${S}/${IMX_BOOT_SOC_TARGET}"
BOOT_STAGING:mx8m-generic-bsp = "${S}/iMX8M"
BOOT_STAGING:mx8dx-generic-bsp = "${S}/iMX8QX"

SOC_FAMILY = "INVALID"
SOC_FAMILY:mx8-generic-bsp = "mx8"
SOC_FAMILY:mx8m-generic-bsp = "mx8m"
SOC_FAMILY:mx8x-generic-bsp = "mx8x"
SOC_FAMILY:mx9-generic-bsp = "mx9"

REV_OPTION ?= ""
REV_OPTION:mx8qxp-generic-bsp = \
    "${@bb.utils.contains('MACHINE_FEATURES', 'soc-revb0', '', 'REV=C0', d)}"

##
# do assignment for TQMa8Xx[S] / TQMa8x SOM to enable bootstream with M4 demo
##
M4_DEFAULT_IMAGE:tqma8xx ?= "rpmsg_lite_pingpong_rtos_linux_remote.bin"
M4_DEFAULT_IMAGE:tqma8xxs ?= "rpmsg_lite_pingpong_rtos_linux_remote.bin"

M4_DEFAULT_IMAGE:tqma8x ?= "rpmsg_lite_pingpong_rtos_linux_remote_m40.bin"
M4_1_DEFAULT_IMAGE:tqma8x ?= "rpmsg_lite_pingpong_rtos_linux_remote_m41.bin"

compile_prepare() {
    bberror 'Invalid SOC family'
}

compile_prepare:mx8-generic-bsp() {
    bbnote '8QM boot binary build'
    cp ${DEPLOY_DIR_IMAGE}/${SC_FIRMWARE_NAME} ${BOOT_STAGING}/scfw_tcm.bin
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

compile_prepare:mx8m-generic-bsp() {
    bbnote '8MQ/8MM/8MN/8MP boot binary build'
    for ddr_firmware in ${DDR_FIRMWARE_NAME}; do
        bbnote "Copy ddr_firmware: ${ddr_firmware} from ${DEPLOY_DIR_IMAGE} -> ${BOOT_STAGING} "
        cp ${DEPLOY_DIR_IMAGE}/${ddr_firmware}               ${BOOT_STAGING}
    done
    cp ${DEPLOY_DIR_IMAGE}/signed_dp_imx8m.bin               ${BOOT_STAGING}
    cp ${DEPLOY_DIR_IMAGE}/signed_hdmi_imx8m.bin             ${BOOT_STAGING}
    for type in ${UBOOT_CONFIG}; do
        for dtb in ${UBOOT_DTB_NAME}; do
            cp ${DEPLOY_DIR_IMAGE}/u-boot-${dtb}-${MACHINE}-${type} \
                                                             ${BOOT_STAGING}/${dtb}-${type}
        done
        cp ${DEPLOY_DIR_IMAGE}/u-boot-spl.bin-${MACHINE}-${type} \
                                                             ${BOOT_STAGING}/u-boot-spl.bin-${type}
        cp ${DEPLOY_DIR_IMAGE}/u-boot-nodtb.bin-${MACHINE}-${type} \
                                                             ${BOOT_STAGING}/u-boot-nodtb.bin-${type}
        cp ${DEPLOY_DIR_IMAGE}/u-boot-${MACHINE}.bin-${type} ${BOOT_STAGING}/u-boot.bin-${type}
    done
}

compile_prepare:mx8x-generic-bsp() {
    bbnote '8QX boot binary build'
    if [ "$1" = "flash_linux_m4" ]; then
        cp ${DEPLOY_DIR_IMAGE}/${M4_DEFAULT_IMAGE}           ${BOOT_STAGING}/m4_image.bin
    fi
    cp ${DEPLOY_DIR_IMAGE}/${SECO_FIRMWARE_NAME}             ${BOOT_STAGING}
    cp ${DEPLOY_DIR_IMAGE}/${SC_FIRMWARE_NAME}               ${BOOT_STAGING}/scfw_tcm.bin
    for type in ${UBOOT_CONFIG}; do
        cp ${DEPLOY_DIR_IMAGE}/u-boot-${MACHINE}.bin-${type} ${BOOT_STAGING}/u-boot.bin-${type}
        if [ -e ${DEPLOY_DIR_IMAGE}/u-boot-spl.bin-${MACHINE}-${type} ] ; then
            cp ${DEPLOY_DIR_IMAGE}/u-boot-spl.bin-${MACHINE}-${type} \
                                                             ${BOOT_STAGING}/u-boot-spl.bin-${type}
        fi
    done
}

compile_prepare:mx9-generic-bsp() {
    bbnote 'i.MX9 boot binary build'
    for ddr_firmware in ${DDR_FIRMWARE_NAME}; do
        bbnote "Copy ddr_firmware: ${ddr_firmware} from ${DEPLOY_DIR_IMAGE} -> ${BOOT_STAGING} "
        cp ${DEPLOY_DIR_IMAGE}/${ddr_firmware}               ${BOOT_STAGING}
    done
    cp ${DEPLOY_DIR_IMAGE}/${SECO_FIRMWARE_NAME}             ${BOOT_STAGING}
    for type in ${UBOOT_CONFIG}; do
        cp ${DEPLOY_DIR_IMAGE}/u-boot-spl.bin-${MACHINE}-${type} \
                                                             ${BOOT_STAGING}/u-boot-spl.bin-${type}
        cp ${DEPLOY_DIR_IMAGE}/u-boot-${MACHINE}.bin-${type} ${BOOT_STAGING}/u-boot.bin-${type}
    done
}

compile_finish() {
    # Do nothing by default
    :
}

generate_habinfo_mx8m() {
    local target="$1" print_fit_hab_target

    awk '
        /^ csf_off\>/ { print "SPL_CSF_OFF=\""$2"\"" }
        /^ spl hab block:/ { print "SPL_HAB_BLOCK=\""$4" "$5" "$6"\"" }
        /^ sld_csf_off\>/ { print "SLD_CSF_OFF=\""$2"\"" }
        /^ sld hab block:/ { print "SLD_HAB_BLOCK=\""$4" "$5" "$6"\"" }
    ' ${BOOT_STAGING}/mkimage.log

    case "${target}" in
    *_flexspi)
        print_fit_hab_target='print_fit_hab_flexspi'
        ;;
    *)
        print_fit_hab_target='print_fit_hab'
        ;;
    esac

    echo -n 'FIT_HAB_BLOCK="'
    oe_runmake SOC=${IMX_BOOT_SOC_TARGET} ${REV_OPTION} dtbs=${UBOOT_DTB_NAME} -s ${print_fit_hab_target}
    echo '"'
}

compile_finish:mx8m-generic-bsp() {
    local target="$1" type="$2"

    generate_habinfo_mx8m "${target}" > ${S}/habinfo-${type}.env-${target}
}

do_compile() {
    rm -f ${S}/habinfo-*

    # mkimage for i.MX8
    # Copy TEE binary to SoC target folder to mkimage
    if ${DEPLOY_OPTEE}; then
        cp ${DEPLOY_DIR_IMAGE}/tee.bin ${BOOT_STAGING}
    fi
    cp ${DEPLOY_DIR_IMAGE}/${ATF_MACHINE_NAME} ${BOOT_STAGING}/bl31.bin
    for target in ${IMXBOOT_TARGETS}; do
        for config in ${UBOOT_CONFIG}; do
            compile_prepare "$target"

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
                compile_finish "$target" "$config"
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

do_deploy() {
    # copy the generated boot images to deploy path
    for target in ${IMXBOOT_TARGETS}; do
        for type in ${UBOOT_CONFIG}; do
            install -m 0644 ${S}/${BOOT_NAME}-${MACHINE}-${type}.bin-${target} \
                                                             ${DEPLOYDIR}
            if [ -e ${S}/habinfo-${type}.env-${target} ]; then
                install -m 0644 ${S}/habinfo-${type}.env-${target} ${DEPLOYDIR}/
            fi
        done
    done
}
addtask deploy before do_build after do_compile

PACKAGE_ARCH = "${MACHINE_ARCH}"
FILES:${PN} = "/boot"

COMPATIBLE_MACHINE = "(mx8-generic-bsp|mx9-generic-bsp)"
