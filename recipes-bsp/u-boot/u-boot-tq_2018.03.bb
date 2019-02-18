
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
           file://0003-tqc-common-simplify-tqc_sdmmc.patch \
           file://0004-serial-serial_lpuart-prepare-using-different-uarts-f.patch \
           file://0005-tqc-common-add-missing-include-for-in-tqc_sdmmc.patch \
           file://0006-tqc-common-fix-prototypes-in-baseboard-API-header.patch \
           file://0007-tqma8qx-initial-board-support.patch \
           file://0008-tqma8qx-fix-compiler-warnings-for-doubled-defined-sy.patch \
           file://0009-FEC-debug-bringup.patch \
           file://0010-tqma8qx-bringup-net-for-MBa8QX.patch \
           file://0011-tqma8qx-add-ethernet-to-DT.patch \
           file://0012-eth-dm-fec-Add-gpio-phy-reset-binding.patch \
           file://0013-arm-dt-fsl-imx8qxp-tqma8qx-mba8qx-add-reset-gpio-to-.patch \
           file://0014-net-fec_mxc-parse-also-phy-handle.patch \
           file://0015-arm-dt-fsl-imx8qxp-tqma8qx-mba8qx-correct-phy-mode-f.patch \
           file://0016-net-phy-ti-enable-extended-read-write-for-driver.patch \
           file://0017-tqma8qx-HACK-configure-static-ip-and-MAC-for-bringup.patch \
           file://0018-net-fec_mxc-rewrite-fec_phy_init.patch \
           file://0019-phy-ti-debug-bringup.patch \
           file://0020-tqma8qx-mba8qx-add-lpgc-support.patch \
           file://0021-tqma8qx-mba8qx-update-defconfig.patch \
           file://0022-tqma8qx-enable-FAT_WRITE.patch \
           file://0023-tqma8qx-env-add-update-scripts-for-kernel-and-u-boot.patch \
           file://0024-tqma8qx-configure-memtest.patch \
           file://0025-tqma8qx-enable-usb-in-defconfig.patch \
           file://0026-tqma8qx-enable-i2c-devices-in-defconfig.patch \
           file://0027-tqma8qx-enable-i2c1-in-dt.patch \
           file://0028-tqma8qx-enable-usb-in-dt.patch \
           file://0029-tqma8qx-add-devices-to-i2c1-bus.patch \
           file://0030-tqma8qx-mba8qx-add-devices-to-i2c1-bus.patch \
           file://0031-tqma8qx-config-enable-rtc-driver-and-date-command-su.patch \
           file://0032-tqma8qx-dt-enable-rtc.patch \
           file://0033-cmd-mtest-give-more-info.patch \
           file://0034-mmc-allow-selection-of-CONFIG_FSL_ESDHC-per-Kconfig.patch \
           file://0035-tqma8qx_mba8qx-select-mmc-stuff-in-defconfig.patch \
           file://0036-tqma8qx_mba8qx-add-init-hooks-to-defconfig.patch \
           file://0037-tqma8qx-mba8qx-dt-fixes-for-usb-otg1.patch \
           file://0038-arm-dt-fsl-imx8qxp-tqma8qx-mba8qx-remove-unused-lpua.patch \
           file://0039-mmc-init-debug.patch \
           file://0040-tqma8qx-remove-unused-pinmux-defines.patch \
           file://0041-tqma8qx-move-board_quiesce_devices-to-mainboard-file.patch \
           file://0042-tqma8qx-remove-ethernet-and-sdhci-related-code.patch \
           file://0043-tqma8qx-Kconfig-select-dm-drivers.patch \
           file://0044-tqma8qx-default-DT-to-B0-Stepping.patch \
           file://0045-arm-dt-fsl-imx8qxp-tqma8qx-fsl-imx8qxp-tqma8qx-mba8q.patch \
           file://0046-arm-dt-fsl-imx8qxp-tqma8qx-remove-unused-SD-regulato.patch \
           file://0047-arm-dt-fsl-imx8qxp-tqma8qx-mba8qx-fix-SD1-regulator.patch \
           file://0048-arm-dt-fsl-imx8qxp-tqma8qx-mba8qx-reorder-nodes-alph.patch \
           file://0049-add-tqma8qxs-with-mb-smarc-2.patch \
           file://0050-arm-dt-fsl-imx8qxp-tqma8qxs-add-i2c1.patch \
           file://0051-arm-dt-fsl-imx8qxp-tqma8qxs-mb-smarc-2-add-fec.patch \
           file://0052-arm-dt-fsl-imx8qxp-tqma8qxs-mb-smarc-2-add-i2c1.patch \
           file://0053-arm-dt-fsl-imx8qxp-tqma8qxs-mb-smarc-2-add-usb.patch \
           file://0054-arm-dt-fsl-imx8qxp-tqma8qxs-mb-smarc-2-prepare-SD3-H.patch \
           file://0055-arm-dt-fsl-imx8qxp-tqma8qxs-prepare-e-MMC-HS-mode.patch \
           file://0056-mtd-ubi-allow-via-Kconfig.patch \
           file://0057-serial-mxc-Add-match-string-for-i.mx6-quad-dual-lite.patch \
           file://0058-mmc-fsl_esdhc-revert-usage-of-CONFIG_IS_ENABLED-DM_R.patch \
           file://0059-power-pmic-uclass-allow-selection-of-CONFIG_PMIC_CHI.patch \
           file://0060-net-Kconfig-mark-FEC_MXC-also-compatible-for-MX8MQ-M.patch \
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

#COMPATIBLE_MACHINE .= "|tqma8mq-mba8mx|tqma8mq-2gm-mba8mx"

# empty for now
COMPATIBLE_MACHINE = "tqma8qx-mba8qx|tqma8qxs-mb-smarc-2"

UBOOT_NAME_mx8 = "u-boot-${MACHINE}.bin-${UBOOT_CONFIG}"
