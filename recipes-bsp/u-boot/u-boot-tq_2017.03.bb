
DESCRIPTION = "u-boot for TQ-Group NXP i.MX8xX based modules"

require recipes-bsp/u-boot/u-boot.inc

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

PROVIDES += "u-boot"
DEPENDS_append = " dtc-native bc-native"

SRCREV = "0776ee377dca253eaf13c8301d9bfdfe3cf04a07"
SRCBRANCH = "TQMa8xx-bringup-imx_v2017.03_4.9.88_imx8qxp_beta2"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
           file://0001-mtd-ubi-allow-via-Kconfig.patch \
           file://0002-dm-imx-serial-add-i.MX6UL-support.patch \
           file://0003-serial-mxc-Add-match-string-for-i.mx6-quad-dual-lite.patch \
           file://0004-mmc-fsl_esdhc-revert-usage-of-CONFIG_IS_ENABLED-DM_R.patch \
           file://0005-power-pmic-uclass-allow-selection-of-CONFIG_PMIC_CHI.patch \
           file://0006-net-Kconfig-mark-FEC_MXC-compatible-for-MX7-MX8MQ-MX.patch \
           file://0007-imx8-tqma8qx-tqma8qxs-fix-Kconfig-default-selects.patch \
           file://0008-add-i.MX8MQ-modul-tqma8mq-with-base-board-mba8mx.patch \
           file://0009-board-tqc-common-prepare-prototypes-for-split-spl-in.patch \
           file://0010-board-tqma8mx-split-spl-in-module-and-mainboard-part.patch \
           file://0011-board-tqma8mx-debug-spl.patch \
           file://0012-boards-tqma8mx-prepare-config-header-for-gpio-port-e.patch \
           file://0013-arm-dt-fsl-imx8mq-tqma8mq-add-i2c-eeproms.patch \
           file://0014-arm-dt-fsl-imx8mq-tqma8mq-mba8mx-add-i2c-port-expand.patch \
           file://0015-arm-dt-tqma8mq-mba8mx-add-ethernet-support.patch \
           file://0016-board-tqma8mx-prepare-RGMII-FEC-interface.patch \
           file://0017-boards-tqma8mx-mba8mx-enable-net-support-per-config.patch \
           file://0018-arm-dt-fsl-imx8mq-tqma8mq-add-regulators-for-e-MMC-d.patch \
           file://0019-arm-dt-fsl-imx8mq-tqma8mq-mba8mx-regulators-dont-nee.patch \
           file://0020-arm-dt-fsl-imx8mq-tqma8mq-mba8mx-fixup-SD-Card.patch \
           file://0021-arm-dt-fsl-imx8mq-tqma8mq-mba8mx-fixup-ethernet.patch \
           file://0022-tqma8mq_mba8mx-improve-config.patch \
           file://0023-board-tqma8mx-mark-USB0-as-not-supported.patch \
           file://0024-board-tqma8mx-fix-dt-name-in-env.patch \
           file://0025-board-tqma8mx-env-add-update-scripts.patch \
           file://0026-MLK-19433-1-imx-Add-macro-for-chip-rev-2.1.patch \
           file://0027-arch-imx8m-add-defines-for-ROM-Version.patch \
           file://0028-MLK-19526-1-imx8mq-Add-CPU-ID-for-iMX8MD-and-iMX8MQL.patch \
           file://0029-tqma8mql-add-2-GiB-variant.patch \
           file://0030-tqc-common-eeprom-fix-checkpatch-warnings-and-checks.patch \
           file://0031-tq-common-eeprom-update-header.patch \
           file://0032-tqc-tqmaxx_eeprom-Add-option-to-read-from-other-star.patch \
           file://0033-board-tqma8mx-select-TQC_EEPROM.patch \
           file://0034-tqc-tqc_eeprom-remove-CONFIG_SYS_I2C_EEPROM_ADDR_LEN.patch \
           file://0035-tqc-tqc_eeprom-add-spl-support.patch \
           file://0036-board-tqma8mq-support-second-ram-config.patch \
           file://0037-arm-dt-fsl-imx8mq-tqma8mq-fix-eeprom-node-name.patch \
           "

S = "${WORKDIR}/git"

BOOT_TOOLS = "imx-boot-tools"

do_deploy_append_mx8mq () {
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
                    install -m 0777 ${B}/${config}/u-boot-nodtb.bin  ${DEPLOYDIR}/${BOOT_TOOLS}
                fi
            done
            unset  j
        done
        unset  i
    fi

}

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "tqma8qx-mba8qx|tqma8qxs-mb-smarc-2"
COMPATIBLE_MACHINE .= "|tqma8mq-mba8mx"

UBOOT_NAME_mx8 = "u-boot-${MACHINE}.bin-${UBOOT_CONFIG}"
