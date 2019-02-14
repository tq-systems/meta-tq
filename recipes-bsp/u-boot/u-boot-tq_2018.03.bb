
DESCRIPTION = "u-boot for TQ-Group NXP i.MX8xX based modules"

require recipes-bsp/u-boot/u-boot.inc
inherit pythonnative

PROVIDES += "u-boot"
DEPENDS_append = " python dtc-native bc-native"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

SRCREV = "7ade5b407fe6164c0d07f32f72e487ae5f6f3964"
SRCBRANCH = "TQMa8xx-v2018.03-rel_imx_4.14.78_1.0.0_ga"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
	file://0001-tqc-add-common-dir.patch \
	file://0002-tqc-common-add-common-mmc-boot-env-stuff-for-tqc-boa.patch \
	file://0003-tqma8xx-initial-board-support.patch \
	file://0004-tqma8x-compile-fixes-for-tqma8xx.patch \
	file://0005-buildfixes.patch \
	file://0006-tqma8qx_mba8qx_mmc_defconfig-regenerate.patch \
	file://0007-tqma8qx-wip-slim-u-boot-dts.patch \
	file://0008-build-fixes.patch \
	file://0009-tqma8xx-wip-board-bringup.patch \
	file://0010-serial-serial_lpuart-prepare-using-different-uarts-f.patch \
	file://0011-WIP-adapt-for-net-TQMa8xX-board.patch \
	file://0012-WIP-adapt-for-TQMaxX-board-part-2.patch \
	file://0013-WIP-adapt-for-TQMaxX-board-part-3.patch \
	file://0014-IP-adapt-for-TQMaxX-board-part-4-fix-env-for-mmc.patch \
	file://0015-WIP-adapt-for-TQMaxX-board-part-5-rename-boart-to-TQ.patch \
	file://0016-WIP-adapt-for-TQMaxX-board-part-6-move-board-to-tqc.patch \
	file://0017-tqc-common-simplify-tqc_sdmmc.patch \
	file://0018-WIP-adapt-for-TQMaxX-board-part-6-fix-RAM-layout.patch \
	file://0019-IP-adapt-for-TQMaxX-board-part-7-split-board-for-tqc.patch \
	file://0020-WIP-adapt-for-TQMa8xX-board-part-7-rename-dt-and-con.patch \
	file://0021-WIP-adapt-for-TQMaxX-board-part-9-remove-cleanup.patch \
	file://0022-board-tqc-tqma8qx-compile-fixes.patch \
	file://0023-WIP-adapt-for-TQMaxX-board-part-10-baseboard-config-.patch \
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

#COMPATIBLE_MACHINE = "tqma8qx-mba8qx|tqma8qxs-mb-smarc-2"
#COMPATIBLE_MACHINE .= "|tqma8mq-mba8mx|tqma8mq-2gm-mba8mx"

# empty for now
COMPATIBLE_MACHINE = "tqma8qx-mba8qx"

UBOOT_NAME_mx8 = "u-boot-${MACHINE}.bin-${UBOOT_CONFIG}"
