
DESCRIPTION = "u-boot for TQ-Group NXP i.MX8xX based modules"

require recipes-bsp/u-boot/u-boot.inc
inherit pythonnative

PROVIDES += "u-boot"
DEPENDS_append = " python dtc-native bc-native"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

SRCREV = "8e520350d045818acc76d377b3ba49929dc1de14"
SRCBRANCH = "TQMa8xx-bringup-v2018.03-rel_imx_4.14.78_1.0.0_ga"

SRC_URI = " \
    ${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
    file://0001-tqma8qx-tqma8qx-mba8qx-fix-wrong-placed-return-state.patch \
    file://0002-tqma8qx-mba8qx-fix-default-fdt-file-in-env.patch \
    file://0003-tqma8qx-mba8qx-force-pcie-pads-muxed-early.patch \
    file://0004-boards-add-support-for-TQMa8DX-variant.patch \
    file://0005-board-tqma8qx-rename-baseboard-file-configs-mba8qx-m.patch \
    file://0006-board-tqma8qx-select-default-u-boot-and-linux-device.patch \
    file://0007-board-tqma8qx-regen-defconfig-for-tqma8qx-mba8qx-mmc.patch \
    file://0008-boards-tqma8qx-use-device-tree-configured-in-KConfig.patch \
    file://0009-boards-tqma8qx-add-defconfig-for-TQMa8DX-on-MBa8xX-w.patch \
    file://0010-tqma8qx-refine-board-name-output.patch \
    file://0011-tqma8qx-support-different-RAM-sizes.patch \
    file://0012-board-rename-tqma8qx-tqma8xx.patch \
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

COMPATIBLE_MACHINE = "tqma8qx|tqma8qxs"
COMPATIBLE_MACHINE .= "|tqma8mq"

UBOOT_NAME_mx8 = "u-boot-${MACHINE}.bin-${UBOOT_CONFIG}"
