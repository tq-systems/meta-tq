
DESCRIPTION = "u-boot for TQ-Group NXP i.MX8xX based modules"

require recipes-bsp/u-boot/u-boot.inc
inherit pythonnative

PROVIDES += "u-boot"
DEPENDS_append = " python dtc-native bc-native"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

SRCREV = "4856b061b908589e9d7869fae7ad51316ba2c073"
SRCBRANCH = "TQMa8xx-bringup-v2018.03-rel_imx_4.14.78_1.0.0_ga"

SRC_URI = " \
    ${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
    file://0001-boards-add-support-for-TQMa8XDS-variant.patch \
    file://0002-board-tqma8qxs-select-default-u-boot-and-linux-devic.patch \
    file://0003-board-tqma8qxs-regen-defconfig-for-tqma8qxs-mb-smarc.patch \
    file://0004-boards-tqma8qxs-use-device-tree-configured-in-KConfi.patch \
    file://0005-boards-tqma8xxs-add-defconfig-for-TQMa8XDS-on-MB-SMA.patch \
    file://0006-tqma8qxs-refine-board-name-output.patch \
    file://0007-tqma8qxs-support-different-RAM-sizes.patch \
    file://0008-board-rename-tqma8qxs-tqma8xxs.patch \
    file://0009-tqma8mx-implement-board_mmc_env_dev-and-mmc_map_to_k.patch \
    file://0010-tqma8xx-implement-mmc_map_to_kernel_blk.patch \
    file://0011-tqma8xxs-implement-mmc_map_to_kernel_blk.patch \
    file://0012-tqma8-config-label-SYS_MMC_ENV_DEV-invalid.patch \
"

S = "${WORKDIR}/git"

BOOT_TOOLS = "imx-boot-tools"

do_deploy_append_mx8m () {
    # Deploy the mkimage, u-boot-nodtb.bin and fsl-imx8mq-XX.dtb for mkimage to generate boot binary
    if [ -n "${UBOOT_CONFIG}" ]
    then
        for config in ${UBOOT_MACHINE}; do
            i=$(expr $i + 1);
            for type in ${UBOOT_CONFIG}; do
                j=$(expr $j + 1);
                if [ $j -eq $i ]
                then
                    install -d ${DEPLOYDIR}/${BOOT_TOOLS}
                    install -m 0777 ${B}/${config}/arch/arm/dts/${UBOOT_DTB_NAME}  ${DEPLOYDIR}/${BOOT_TOOLS}
                    install -m 0777 ${B}/${config}/tools/mkimage  ${DEPLOYDIR}/${BOOT_TOOLS}/mkimage_uboot
                    install -m 0777 ${B}/${config}/u-boot-nodtb.bin  ${DEPLOYDIR}/${BOOT_TOOLS}/u-boot-nodtb.bin-${MACHINE}-${UBOOT_CONFIG}
                fi
            done
            unset  j
        done
        unset  i
    fi

}

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "tqma8xx"
COMPATIBLE_MACHINE_append = "|tqma8xxs"
COMPATIBLE_MACHINE_append = "|tqma8mq"

UBOOT_NAME_mx8 = "u-boot-${MACHINE}.bin-${UBOOT_CONFIG}"
