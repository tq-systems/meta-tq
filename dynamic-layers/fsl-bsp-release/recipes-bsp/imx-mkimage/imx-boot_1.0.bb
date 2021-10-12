# Copyright 2017-2018 NXP

require imx-mkimage_git.inc

DESCRIPTION = "Generate Boot Loader for i.MX 8 device"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
SECTION = "BSP"

IMX_EXTRA_FIRMWARE      = "firmware-imx-8 imx-sc-firmware imx-seco"
IMX_EXTRA_FIRMWARE_mx8m = "firmware-imx-8m"
IMX_EXTRA_FIRMWARE_mx8x = "imx-sc-firmware imx-seco"
DEPENDS += " \
    u-boot \
    ${IMX_EXTRA_FIRMWARE} \
    imx-atf \
    ${@bb.utils.contains('MACHINE_FEATURES', 'optee', 'optee-os', '', d)} \
"
DEPENDS_append_mx8m = " u-boot-mkimage-native dtc-native"
BOOT_NAME = "imx-boot"
PROVIDES = "${BOOT_NAME}"

DEPLOYDIR_IMXBOOT ?= "${BOOT_TOOLS}"

inherit deploy seco-firmware-name

# Add CFLAGS with native INCDIR & LIBDIR for imx-mkimage build
CFLAGS = "-O2 -Wall -std=c99 -I ${STAGING_INCDIR_NATIVE} -L ${STAGING_LIBDIR_NATIVE}"

IMX_M4_DEMOS        ?= ""
IMX_M4_DEMOS_mx8qm  ?= "imx-m4-demos:do_deploy"
IMX_M4_DEMOS_mx8x   ?= "imx-m4-demos:do_deploy"
IMX_M4_DEMOS_mx8dxl ?= "imx-m4-demos:do_deploy"

M4_DEFAULT_IMAGE ?= "m4_image.bin"
M4_DEFAULT_IMAGE_mx8qxp ?= "imx8qx_m4_TCM_power_mode_switch.bin"
M4_DEFAULT_IMAGE_mx8phantomdxl ?= "imx8dxl-phantom_m4_TCM_srtm_demo.bin"
M4_DEFAULT_IMAGE_mx8dxl ?= "imx8dxl_m4_TCM_power_mode_switch.bin"
M4_DEFAULT_IMAGE_mx8dx ?= "imx8qx_m4_TCM_power_mode_switch.bin"

# This package aggregates output deployed by other packages,
# so set the appropriate dependencies
do_compile[depends] += " \
    virtual/bootloader:do_deploy \
    ${@' '.join('%s:do_deploy' % r for r in '${IMX_EXTRA_FIRMWARE}'.split() )} \
    imx-atf:do_deploy \
    ${IMX_M4_DEMOS} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'optee', 'optee-os:do_deploy', '', d)} \
"

SC_FIRMWARE_NAME ?= "scfw_tcm.bin"

ATF_MACHINE_NAME ?= "bl31-imx8qm.bin"
ATF_MACHINE_NAME_mx8qm = "bl31-imx8qm.bin"
ATF_MACHINE_NAME_mx8x  = "bl31-imx8qx.bin"
ATF_MACHINE_NAME_mx8mq = "bl31-imx8mq.bin"
ATF_MACHINE_NAME_mx8mm = "bl31-imx8mm.bin"
ATF_MACHINE_NAME_mx8mn = "bl31-imx8mn.bin"
ATF_MACHINE_NAME_mx8mnlite = "bl31-imx8mn.bin"
ATF_MACHINE_NAME_mx8mp = "bl31-imx8mp.bin"
ATF_MACHINE_NAME_mx8phantomdxl = "bl31-imx8qx.bin"
ATF_MACHINE_NAME_mx8dxl = "bl31-imx8dxl.bin"
ATF_MACHINE_NAME_mx8dx = "bl31-imx8dx.bin"
ATF_MACHINE_NAME_append = "${@bb.utils.contains('MACHINE_FEATURES', 'optee', '-optee', '', d)}"

SECO_FIRMWARE_NAME ?= ""

UBOOT_NAME = "u-boot-${MACHINE}.bin-${UBOOT_CONFIG}"
UBOOT_SPL_NAME = "${@os.path.basename(d.getVar("SPL_BINARY"))}-${MACHINE}-${UBOOT_CONFIG}"
BOOT_CONFIG_MACHINE = "${BOOT_NAME}-${MACHINE}-${UBOOT_CONFIG}.bin"

TOOLS_NAME ?= "mkimage_imx8"

SOC_TARGET       ?= "INVALID"
SOC_TARGET_mx8qm  = "iMX8QM"
SOC_TARGET_mx8x   = "iMX8QX"
SOC_TARGET_mx8mq  = "iMX8M"
SOC_TARGET_mx8mm  = "iMX8MM"
SOC_TARGET_mx8mn  = "iMX8MN"
SOC_TARGET_mx8mnlite  = "iMX8MN"
SOC_TARGET_mx8mp  = "iMX8MP"
SOC_TARGET_mx8dxl = "iMX8DXL"
SOC_TARGET_mx8phantomdxl = "iMX8QX"
SOC_TARGET_mx8dx  = "iMX8DX"

DEPLOY_OPTEE = "${@bb.utils.contains('MACHINE_FEATURES', 'optee', 'true', 'false', d)}"

IMXBOOT_TARGETS ??= \
    "${@bb.utils.contains('UBOOT_CONFIG', 'fspi', 'flash_flexspi', \
        bb.utils.contains('UBOOT_CONFIG', 'nand', 'flash_nand', \
                                                  'flash_multi_cores flash_dcd', d), d)}"

S = "${WORKDIR}/git"

BOOT_STAGING       = "${S}/${SOC_TARGET}"
BOOT_STAGING_mx8m  = "${S}/iMX8M"
BOOT_STAGING_mx8dx = "${S}/iMX8QX"

SOC_FAMILY      = "INVALID"
SOC_FAMILY_mx8  = "mx8"
SOC_FAMILY_mx8m = "mx8m"
SOC_FAMILY_mx8x = "mx8x"

REV_OPTION ?= ""
REV_OPTION_mx8qxpc0 = "REV=C0"
REV_OPTION_mx8phantomdxl = "REV=C0"

compile_mx8m() {
    bbnote 8MQ/8MM/8MN/8MP boot binary build
    for ddr_firmware in ${DDR_FIRMWARE_NAME}; do
        bbnote "Copy ddr_firmware: ${ddr_firmware} from ${DEPLOY_DIR_IMAGE} -> ${BOOT_STAGING} "
        cp ${DEPLOY_DIR_IMAGE}/${ddr_firmware}               ${BOOT_STAGING}
    done
    cp ${DEPLOY_DIR_IMAGE}/signed_dp_imx8m.bin               ${BOOT_STAGING}
    cp ${DEPLOY_DIR_IMAGE}/signed_hdmi_imx8m.bin             ${BOOT_STAGING}
    cp ${STAGING_DIR_NATIVE}/${bindir}/mkimage               ${BOOT_STAGING}/mkimage_uboot
    cp ${DEPLOY_DIR_IMAGE}/${BOOT_TOOLS}/${ATF_MACHINE_NAME} ${BOOT_STAGING}/bl31.bin
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
    cp ${DEPLOY_DIR_IMAGE}/${BOOT_TOOLS}/${ATF_MACHINE_NAME} ${BOOT_STAGING}/bl31.bin
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
    cp ${DEPLOY_DIR_IMAGE}/${BOOT_TOOLS}/${ATF_MACHINE_NAME} ${BOOT_STAGING}/bl31.bin
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
            make clean

            if [ "$target" = "flash_linux_m4_no_v2x" ]; then
                # Special target build for i.MX 8DXL with V2X off
                bbnote "building ${SOC_TARGET} - ${REV_OPTION} V2X=NO ${target}"
                make SOC=${SOC_TARGET} ${REV_OPTION} V2X=NO dtbs=${UBOOT_DTB_NAME} flash_linux_m4
            else
                bbnote "building ${SOC_TARGET} - ${REV_OPTION} ${target}"
                make SOC=${SOC_TARGET} ${REV_OPTION} dtbs=${UBOOT_DTB_NAME} ${target}
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
    install -m 0755 ${BOOT_STAGING}/mkimage_uboot            ${DEPLOYDIR}/${BOOT_TOOLS}
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
    # copy the generated boot image to deploy path
    if [ -e ${DEPLOYDIR}/${BOOT_NAME} ]; then
        rm ${DEPLOYDIR}/${BOOT_NAME}
    fi
    for target in ${IMXBOOT_TARGETS}; do
        for type in ${UBOOT_CONFIG}; do
            install -m 0644 ${S}/${BOOT_NAME}-${MACHINE}-${type}.bin-${target} \
                                                             ${DEPLOYDIR}
            if ! [ -e ${DEPLOYDIR}/${BOOT_NAME} ]; then
                ln -sf ${BOOT_NAME}-${MACHINE}-${type}.bin-${target} \
                                                             ${DEPLOYDIR}/${BOOT_NAME}
            fi
        done
    done
}
addtask deploy before do_build after do_compile

PACKAGE_ARCH = "${MACHINE_ARCH}"
FILES_${PN} = "/boot"

COMPATIBLE_MACHINE = "(mx8)"
