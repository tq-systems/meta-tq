
DESCRIPTION = "u-boot for TQ-Group NXP i.MX8xX based modules"

require recipes-bsp/u-boot/u-boot.inc
inherit pythonnative

PROVIDES += "u-boot"
DEPENDS_append = " python dtc-native bc-native"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

SRCREV = "621f0a35bb1f69f9d4c2ff190f60928da3cf7d58"
SRCBRANCH = "TQMa8xx-bringup-v2018.03-rel_imx_4.14.78_1.0.0_ga"

SRC_URI = " \
    ${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
    file://0001-boards-tqma8xx-add-initial-support-for-MBpa8Xx.patch \
    file://0002-arm64-dt-fsl-imx8qxp-tqma8xqp-mbpa8xx-add-led-suppor.patch \
    file://0003-tqma8xqp_mbpa8xx-add-led-support-to-defconfig.patch \
    file://0004-arm-dt-fsl-imx8qxp-tqma8xqp-mbpa8xx-mark-SD-as-non-r.patch \
    file://0005-arm-dt-fsl-imx8qxp-tqma8xqp-mbpa8xx-add-revision-det.patch \
    file://0006-arm-dt-add-reset-GPIO-as-hog-pins.patch \
    file://0007-board-tqma8xx-mbpa8xx-handle-board-gpio-init.patch \
    file://0008-arm-dt-fsl-imx8qxp-tqma8xqp-mbpa8xx-fix-ethernet-LED.patch \
    file://0009-net-phy-ti-add-support-for-LEDCR1-.-3.patch \
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
