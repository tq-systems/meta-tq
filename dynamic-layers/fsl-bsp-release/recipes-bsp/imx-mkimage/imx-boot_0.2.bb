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

IMX_M4_DEMOS        = ""
IMX_M4_DEMOS_mx8qm  = "imx-m4-demos:do_deploy"
IMX_M4_DEMOS_mx8x   = "imx-m4-demos:do_deploy"
IMX_M4_DEMOS_mx8qxp   = "imx-m4-demos:do_deploy"

M4_DEFAULT_IMAGE ?= "m4_image.bin"
M4_DEFAULT_IMAGE_mx8qxp = "imx8qx_m4_TCM_srtm_demo.bin"
M4_DEFAULT_IMAGE_mx8dxl-phantom = "imx8dxl-phantom_m4_TCM_srtm_demo.bin"


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
ATF_MACHINE_NAME_mx8qxp = "bl31-imx8qx.bin"
ATF_MACHINE_NAME_mx8dx = "bl31-imx8dx.bin"
ATF_MACHINE_NAME_mx8mq = "bl31-imx8mq.bin"
ATF_MACHINE_NAME_mx8mm = "bl31-imx8mm.bin"
ATF_MACHINE_NAME_mx8mn = "bl31-imx8mn.bin"
ATF_MACHINE_NAME_append = "${@bb.utils.contains('MACHINE_FEATURES', 'optee', '-optee', '', d)}"

SECO_FIRMWARE_NAME ?= ""

UBOOT_NAME = "u-boot-${MACHINE}.bin-${UBOOT_CONFIG}"
UBOOT_SPL_NAME = "${@os.path.basename(d.getVar("SPL_BINARY"))}-${MACHINE}-${UBOOT_CONFIG}"
BOOT_CONFIG_MACHINE = "${BOOT_NAME}-${MACHINE}-${UBOOT_CONFIG}.bin"

TOOLS_NAME ?= "mkimage_imx8"

SOC_TARGET       ?= "INVALID"
SOC_TARGET_mx8qm  = "iMX8QM"
SOC_TARGET_mx8qxp = "iMX8QX"
SOC_TARGET_mx8dx  = "iMX8DX"
SOC_TARGET_mx8mq  = "iMX8M"
SOC_TARGET_mx8mm  = "iMX8MM"
SOC_TARGET_mx8mn  = "iMX8MN"

SOC_DIR ?= "${SOC_TARGET}"
SOC_DIR_mx8m = "iMX8M"
SOC_DIR_mx8dx = "iMX8QX"

DEPLOY_OPTEE = "${@bb.utils.contains('MACHINE_FEATURES', 'optee', 'true', 'false', d)}"

IMXBOOT_TARGETS ??= \
    "${@bb.utils.contains('UBOOT_CONFIG', 'fspi', 'flash_flexspi', \
        bb.utils.contains('UBOOT_CONFIG', 'nand', 'flash_nand', \
                                                  'flash_multi_cores flash_dcd', d), d)}"

S = "${WORKDIR}/git"

REV_OPTION ?= ""
REV_OPTION_mx8qxpc0 = "REV=C0"
REV_OPTION_imx8qxpc0mek = "REV=C0"
REV_OPTION_imx8qxpc0lpddr4arm2 = "REV=C0"

do_compile () {
    if [ "${SOC_TARGET}" = "iMX8M" -o "${SOC_TARGET}" = "iMX8MM" -o "${SOC_TARGET}" = "iMX8MN" ]; then
        bbnote 8MQ/8MM/8MN boot binary build
        SOC_DIR="iMX8M"
        for ddr_firmware in ${DDR_FIRMWARE_NAME}; do
            echo "Copy ddr_firmware: ${ddr_firmware} from ${DEPLOY_DIR_IMAGE} -> ${S}/${SOC_DIR} "
            cp ${DEPLOY_DIR_IMAGE}/${ddr_firmware}               ${S}/${SOC_DIR}/
        done
        cp ${DEPLOY_DIR_IMAGE}/signed_*_imx8m.bin             ${S}/${SOC_DIR}/
        cp ${DEPLOY_DIR_IMAGE}/${UBOOT_SPL_NAME} ${S}/${SOC_DIR}/u-boot-spl.bin
        for dtb in ${UBOOT_DTB_NAME}; do
            cp ${DEPLOY_DIR_IMAGE}/${BOOT_TOOLS}/${dtb}   ${S}/${SOC_DIR}/
        done
        cp ${DEPLOY_DIR_IMAGE}/${BOOT_TOOLS}/u-boot-nodtb.bin-${MACHINE}-${UBOOT_CONFIG}    ${S}/${SOC_DIR}/u-boot-nodtb.bin
        cp ${DEPLOY_DIR_IMAGE}/${BOOT_TOOLS}/mkimage_uboot       ${S}/${SOC_DIR}/

        cp ${DEPLOY_DIR_IMAGE}/${BOOT_TOOLS}/${ATF_MACHINE_NAME} ${S}/${SOC_DIR}/bl31.bin
        cp ${DEPLOY_DIR_IMAGE}/${UBOOT_NAME}                     ${S}/${SOC_DIR}/u-boot.bin

    elif [ "${SOC_TARGET}" = "iMX8QM" ]; then
        bbnote 8QM boot binary build
        cp ${DEPLOY_DIR_IMAGE}/${BOOT_TOOLS}/${SC_FIRMWARE_NAME} ${S}/${SOC_DIR}/scfw_tcm.bin
        cp ${DEPLOY_DIR_IMAGE}/${BOOT_TOOLS}/${ATF_MACHINE_NAME} ${S}/${SOC_DIR}/bl31.bin
        cp ${DEPLOY_DIR_IMAGE}/${UBOOT_NAME}                     ${S}/${SOC_DIR}/u-boot.bin
        if [ ${DEPLOY_OPTEE} ] || [ ${SPL_BINARY} ]; then
            cp ${DEPLOY_DIR_IMAGE}/${UBOOT_SPL_NAME} ${S}/${SOC_DIR}/u-boot-spl.bin
        fi

        cp ${DEPLOY_DIR_IMAGE}/imx8qm_m4_0_TCM_rpmsg_lite_pingpong_rtos_linux_remote_m40.bin ${S}/${SOC_DIR}/m40_tcm.bin
        cp ${DEPLOY_DIR_IMAGE}/imx8qm_m4_1_TCM_power_mode_switch_m41.bin                     ${S}/${SOC_DIR}/m41_tcm.bin
        cp ${DEPLOY_DIR_IMAGE}/imx8qm_m4_0_TCM_rpmsg_lite_pingpong_rtos_linux_remote_m40.bin ${S}/${SOC_DIR}/m4_image.bin
        cp ${DEPLOY_DIR_IMAGE}/imx8qm_m4_1_TCM_power_mode_switch_m41.bin                     ${S}/${SOC_DIR}/m4_1_image.bin

        cp ${DEPLOY_DIR_IMAGE}/${SECO_FIRMWARE_NAME} ${S}/${SOC_DIR}/

    else
        bbnote 8QX boot binary build
        cp ${DEPLOY_DIR_IMAGE}/imx8qx_m4_TCM_power_mode_switch.bin ${S}/${SOC_DIR}/m40_tcm.bin
        cp ${DEPLOY_DIR_IMAGE}/imx8qx_m4_TCM_power_mode_switch.bin ${S}/${SOC_DIR}/m4_image.bin
        cp ${DEPLOY_DIR_IMAGE}/${SECO_FIRMWARE_NAME} ${S}/${SOC_DIR}/
        cp ${DEPLOY_DIR_IMAGE}/${BOOT_TOOLS}/${SC_FIRMWARE_NAME} ${S}/${SOC_DIR}/scfw_tcm.bin
        cp ${DEPLOY_DIR_IMAGE}/${BOOT_TOOLS}/${ATF_MACHINE_NAME} ${S}/${SOC_DIR}/bl31.bin
        cp ${DEPLOY_DIR_IMAGE}/${UBOOT_NAME}                     ${S}/${SOC_DIR}/u-boot.bin
        if [ ${DEPLOY_OPTEE} ] || [ ${SPL_BINARY} ]; then
            cp ${DEPLOY_DIR_IMAGE}/${UBOOT_SPL_NAME} ${S}/${SOC_DIR}/u-boot-spl.bin
        fi
    fi

    # Copy TEE binary to SoC target folder to mkimage
    if ${DEPLOY_OPTEE}; then
        cp ${DEPLOY_DIR_IMAGE}/tee.bin             ${S}/${SOC_DIR}/
    fi

    # mkimage for i.MX8
    for target in ${IMXBOOT_TARGETS}; do
        bbnote "building ${SOC_TARGET} - ${REV_OPTION} ${target}"
        make SOC=${SOC_TARGET} ${REV_OPTION}  dtbs="${UBOOT_DTB_NAME}" ${target}
        if [ -e "${S}/${SOC_DIR}/flash.bin" ]; then
            cp ${S}/${SOC_DIR}/flash.bin ${S}/${BOOT_CONFIG_MACHINE}-${target}
        fi
    done
}

do_install () {
    install -d ${D}/boot
    for target in ${IMXBOOT_TARGETS}; do
        install -m 0644 ${S}/${BOOT_CONFIG_MACHINE}-${target} ${D}/boot/
    done
}

do_deploy () {
    install -d ${DEPLOYDIR}/${DEPLOYDIR_IMXBOOT}

    # copy the tool mkimage to deploy path and sc fw, dcd and uboot
    install -m 0644 ${DEPLOY_DIR_IMAGE}/${UBOOT_NAME} ${DEPLOYDIR}/${DEPLOYDIR_IMXBOOT}
    if [ "${SOC_TARGET}" = "iMX8M" -o "${SOC_TARGET}" = "iMX8MM" -o "${SOC_TARGET}" = "iMX8MN" ]; then
        SOC_DIR="iMX8M"
        install -m 0644 ${DEPLOY_DIR_IMAGE}/u-boot-spl.bin-${MACHINE}-${UBOOT_CONFIG} ${DEPLOYDIR}/${DEPLOYDIR_IMXBOOT}
        for ddr_firmware in ${DDR_FIRMWARE_NAME}; do
            install -m 0644 ${DEPLOY_DIR_IMAGE}/${ddr_firmware} ${DEPLOYDIR}/${DEPLOYDIR_IMXBOOT}
        done
        install -m 0644 ${DEPLOY_DIR_IMAGE}/signed_*_imx8m.bin ${DEPLOYDIR}/${DEPLOYDIR_IMXBOOT}

        install -m 0755 ${S}/${SOC_DIR}/${TOOLS_NAME} ${DEPLOYDIR}/${DEPLOYDIR_IMXBOOT}

        install -m 0755 ${S}/${SOC_DIR}/mkimage_fit_atf.sh ${DEPLOYDIR}/${DEPLOYDIR_IMXBOOT}
    elif [ "${SOC_TARGET}" = "iMX8QM" ]; then
        install -m 0644 ${S}/${SOC_DIR}/${SECO_FIRMWARE_NAME} ${DEPLOYDIR}/${DEPLOYDIR_IMXBOOT}
        install -m 0644 ${S}/${SOC_DIR}/m40_tcm.bin ${DEPLOYDIR}/${DEPLOYDIR_IMXBOOT}
        install -m 0644 ${S}/${SOC_DIR}/m41_tcm.bin ${DEPLOYDIR}/${DEPLOYDIR_IMXBOOT}
        install -m 0644 ${S}/${SOC_DIR}/m4_image.bin ${DEPLOYDIR}/${DEPLOYDIR_IMXBOOT}
        install -m 0644 ${S}/${SOC_DIR}/m4_1_image.bin ${DEPLOYDIR}/${DEPLOYDIR_IMXBOOT}

        install -m 0755 ${S}/${TOOLS_NAME} ${DEPLOYDIR}/${DEPLOYDIR_IMXBOOT}
        if ${DEPLOY_OPTEE}; then
            install -m 0644 ${DEPLOY_DIR_IMAGE}/u-boot-spl.bin-${MACHINE}-${UBOOT_CONFIG} ${DEPLOYDIR}/${DEPLOYDIR_IMXBOOT}
        fi
    else
        install -m 0644 ${S}/${SOC_DIR}/${SECO_FIRMWARE_NAME} ${DEPLOYDIR}/${DEPLOYDIR_IMXBOOT}
        install -m 0644 ${S}/${SOC_DIR}/m40_tcm.bin ${DEPLOYDIR}/${DEPLOYDIR_IMXBOOT}
        install -m 0644 ${S}/${SOC_DIR}/m4_image.bin ${DEPLOYDIR}/${DEPLOYDIR_IMXBOOT}

        install -m 0755 ${S}/${TOOLS_NAME} ${DEPLOYDIR}/${DEPLOYDIR_IMXBOOT}
        if ${DEPLOY_OPTEE}; then
            install -m 0644 ${DEPLOY_DIR_IMAGE}/u-boot-spl.bin-${MACHINE}-${UBOOT_CONFIG} ${DEPLOYDIR}/${DEPLOYDIR_IMXBOOT}
        fi
    fi

    # copy tee.bin to deploy path
    if "${DEPLOY_OPTEE}"; then
        install -m 0644 ${DEPLOY_DIR_IMAGE}/tee.bin ${DEPLOYDIR}/${DEPLOYDIR_IMXBOOT}
    fi

    # copy makefile (soc.mak) for reference
    install -m 0644 ${S}/${SOC_DIR}/soc.mak     ${DEPLOYDIR}/${DEPLOYDIR_IMXBOOT}

    # copy the generated boot image to deploy path
    for target in ${IMXBOOT_TARGETS}; do
        # Use first "target" as IMAGE_IMXBOOT_TARGET
        if [ "$IMAGE_IMXBOOT_TARGET" = "" ]; then
            IMAGE_IMXBOOT_TARGET="$target"
            echo "Set boot target as $IMAGE_IMXBOOT_TARGET"
        fi
        install -m 0644 ${S}/${BOOT_CONFIG_MACHINE}-${target} ${DEPLOYDIR}
    done
    cd ${DEPLOYDIR}
    ln -sf ${BOOT_CONFIG_MACHINE}-${IMAGE_IMXBOOT_TARGET} ${BOOT_CONFIG_MACHINE}
    cd -
}

addtask deploy before do_build after do_compile

PACKAGE_ARCH = "${MACHINE_ARCH}"
FILES_${PN} = "/boot"

COMPATIBLE_MACHINE = "(mx8)"
