
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
           file://0009-tqma8qx-bringup-net-for-MBa8QX.patch \
           file://0010-tqma8qx-add-ethernet-to-DT.patch \
           file://0011-eth-dm-fec-Add-gpio-phy-reset-binding.patch \
           file://0012-arm-dt-fsl-imx8qxp-tqma8qx-mba8qx-add-reset-gpio-to-.patch \
           file://0013-net-fec_mxc-parse-also-phy-handle.patch \
           file://0014-arm-dt-fsl-imx8qxp-tqma8qx-mba8qx-correct-phy-mode-f.patch \
           file://0015-net-phy-ti-enable-extended-read-write-for-driver.patch \
           file://0016-tqma8qx-HACK-configure-static-ip-and-MAC-for-bringup.patch \
           file://0017-net-fec_mxc-rewrite-fec_phy_init.patch \
           file://0018-phy-ti-debug-bringup.patch \
           file://0019-tqma8qx-mba8qx-add-lpgc-support.patch \
           file://0020-tqma8qx-mba8qx-update-defconfig.patch \
           file://0021-tqma8qx-enable-FAT_WRITE.patch \
           file://0022-tqma8qx-env-add-update-scripts-for-kernel-and-u-boot.patch \
           file://0023-tqma8qx-configure-memtest.patch \
           file://0024-tqma8qx-enable-usb-in-defconfig.patch \
           file://0025-tqma8qx-enable-i2c-devices-in-defconfig.patch \
           file://0026-tqma8qx-enable-i2c1-in-dt.patch \
           file://0027-tqma8qx-enable-usb-in-dt.patch \
           file://0028-tqma8qx-add-devices-to-i2c1-bus.patch \
           file://0029-tqma8qx-mba8qx-add-devices-to-i2c1-bus.patch \
           file://0030-tqma8qx-config-enable-rtc-driver-and-date-command-su.patch \
           file://0031-tqma8qx-dt-enable-rtc.patch \
           file://0032-cmd-mtest-give-more-info.patch \
           file://0033-mmc-allow-selection-of-CONFIG_FSL_ESDHC-per-Kconfig.patch \
           file://0034-tqma8qx_mba8qx-select-mmc-stuff-in-defconfig.patch \
           file://0035-tqma8qx_mba8qx-add-init-hooks-to-defconfig.patch \
           file://0036-tqma8qx-mba8qx-dt-fixes-for-usb-otg1.patch \
           file://0037-arm-dt-fsl-imx8qxp-tqma8qx-mba8qx-remove-unused-lpua.patch \
           file://0038-tqma8qx-remove-unused-pinmux-defines.patch \
           file://0039-tqma8qx-move-board_quiesce_devices-to-mainboard-file.patch \
           file://0040-tqma8qx-remove-ethernet-and-sdhci-related-code.patch \
           file://0041-tqma8qx-Kconfig-select-dm-drivers.patch \
           file://0042-tqma8qx-default-DT-to-B0-Stepping.patch \
           file://0043-arm-dt-fsl-imx8qxp-tqma8qx-fsl-imx8qxp-tqma8qx-mba8q.patch \
           file://0044-arm-dt-fsl-imx8qxp-tqma8qx-remove-unused-SD-regulato.patch \
           file://0045-arm-dt-fsl-imx8qxp-tqma8qx-mba8qx-fix-SD1-regulator.patch \
           file://0046-arm-dt-fsl-imx8qxp-tqma8qx-mba8qx-reorder-nodes-alph.patch \
           file://0047-add-tqma8qxs-with-mb-smarc-2.patch \
           file://0048-arm-dt-fsl-imx8qxp-tqma8qxs-add-i2c1.patch \
           file://0049-arm-dt-fsl-imx8qxp-tqma8qxs-mb-smarc-2-add-fec.patch \
           file://0050-arm-dt-fsl-imx8qxp-tqma8qxs-mb-smarc-2-add-i2c1.patch \
           file://0051-arm-dt-fsl-imx8qxp-tqma8qxs-mb-smarc-2-add-usb.patch \
           file://0052-arm-dt-fsl-imx8qxp-tqma8qxs-mb-smarc-2-prepare-SD3-H.patch \
           file://0053-arm-dt-fsl-imx8qxp-tqma8qxs-prepare-e-MMC-HS-mode.patch \
           file://0054-mtd-ubi-allow-via-Kconfig.patch \
           file://0055-serial-mxc-Add-match-string-for-i.mx6-quad-dual-lite.patch \
           file://0056-mmc-fsl_esdhc-revert-usage-of-CONFIG_IS_ENABLED-DM_R.patch \
           file://0057-power-pmic-uclass-allow-selection-of-CONFIG_PMIC_CHI.patch \
           file://0058-net-Kconfig-mark-FEC_MXC-also-compatible-for-MX8MQ-M.patch \
           file://0059-add-i.MX8MQ-modul-tqma8mq-with-base-board-mba8mx.patch \
           file://0060-tqma8mx-port-to-NXP-2018.03-release.patch \
           file://0061-tqma8mx-fix-SPL-allocation.patch \
           file://0062-tqma8mq-defconfig-port-ram-timing-storage-address.patch \
           file://0063-tqma8mq-spl-correct-chip-rev-handling.patch \
           file://0064-board-tqma8mq-allow-two-different-lpddr4-timings.patch \
           file://0065-board-tqc-common-prepare-prototypes-for-split-spl-in.patch \
           file://0066-board-tqma8mx-split-spl-in-module-and-mainboard-part.patch \
           file://0067-boards-tqma8mx-prepare-config-header-for-gpio-port-e.patch \
           file://0068-arm-dt-fsl-imx8mq-tqma8mq-add-i2c-eeproms.patch \
           file://0069-arm-dt-fsl-imx8mq-tqma8mq-mba8mx-add-i2c-port-expand.patch \
           file://0070-arm-dt-tqma8mq-mba8mx-add-ethernet-support.patch \
           file://0071-board-tqma8mx-prepare-RGMII-FEC-interface.patch \
           file://0072-boards-tqma8mx-mba8mx-enable-net-support-per-config.patch \
           file://0073-arm-dt-fsl-imx8mq-tqma8mq-add-regulators-for-e-MMC-d.patch \
           file://0074-arm-dt-fsl-imx8mq-tqma8mq-mba8mx-regulators-dont-nee.patch \
           file://0075-arm-dt-fsl-imx8mq-tqma8mq-mba8mx-fixup-SD-Card.patch \
           file://0076-arm-dt-fsl-imx8mq-tqma8mq-mba8mx-fixup-ethernet.patch \
           file://0077-tqma8mq_mba8mx-improve-config.patch \
           file://0078-board-tqma8mx-mark-USB0-as-not-supported.patch \
           file://0079-board-tqma8mx-fix-dt-name-in-env.patch \
           file://0080-board-tqma8mx-env-add-update-scripts.patch \
           file://0081-fix-tqma8mq-select-LPDDR4-per-default.patch \
           file://0082-tqc-common-eeprom-fix-checkpatch-warnings-and-checks.patch \
           file://0083-tq-common-eeprom-update-header.patch \
           file://0084-tqc-tqmaxx_eeprom-Add-option-to-read-from-other-star.patch \
           file://0085-board-tqma8mx-select-TQC_EEPROM.patch \
           file://0086-tqc-tqc_eeprom-remove-CONFIG_SYS_I2C_EEPROM_ADDR_LEN.patch \
           file://0087-tqc-tqc_eeprom-add-spl-support.patch \
           file://0088-tqma8mq-add-2-GiB-module-variants.patch \
           file://0089-board-tqma8mq-enable-spl-env-support-and-move-stuff-.patch \
           file://0090-cpu-imx8m-do-not-disable-HDMI-and-DCSS-in-dt_fixup-f.patch \
           file://0091-HACK-tqma8mq-add-video-support.patch \
           file://0092-board-tqma8mq-configure-alternative-mem-test.patch \
           file://0093-arm-dt-fsl-imx8qxp-tqma8qx-mba8qx-fix-RGMII-skew.patch \
           file://0094-net-fec_mxc-hack-gpio-framework-does-not-handle-acti.patch \
           file://0095-mtd-spi_flash-do-not-call-EN4B-for-4B-address-set.patch \
           file://0096-arm-dt-fsl-imx8qxp-tqma8qx-increase-QSPI-frequency.patch \
           file://0097-arm-dt-fsl-imx8qxp-tqma8qxs-mb-smarc-2-enable-qspi.patch \
           file://0098-arm-dt-fsl-imx8mq-tqma8mq-enable-QSPI.patch \
           file://0099-arm-dt-fsl-imx8qxp-tqma8qxs-move-qspi-from-baseboard.patch \
           file://0100-arm-dt-fsl-imx8qxp-tqma8qxs-cosmetic-sync-usdhc-with.patch \
           file://0101-imx8m-fix-video-for-i.MX8MQL.patch \
           file://0102-imx8m-show-also-USDHC2-clock.patch \
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
