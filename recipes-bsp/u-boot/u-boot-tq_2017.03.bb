
DESCRIPTION = "u-boot for TQ-Group NXP i.MX8xX based modules"

require recipes-bsp/u-boot/u-boot.inc

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

PROVIDES += "u-boot"
DEPENDS_append = " dtc-native"

SRCREV = "0776ee377dca253eaf13c8301d9bfdfe3cf04a07"
SRCBRANCH = "TQMa8xx-bringup-imx_v2017.03_4.9.88_imx8qxp_beta2"

SRC_URI = "\
  ${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://0001-mtd-ubi-allow-via-Kconfig.patch \
  file://0002-add-framework-for-tqma8mq-mba8mx.patch \
  file://0003-board-tqma8mx-update-timing-from-DDR-stess-tool.patch \
  file://0004-board-tqma8mq-mba8mx-include-order-in-spl.c.patch \
  file://0005-board-tqma8mx-debug-spl.patch \
  file://0006-arm-dt-fsl-imx8mq-tqma8mq-mba8mx-split-dt-into-modul.patch \
  file://0007-tqma8mx-split-config-header-in-module-and-baseboard-.patch \
  file://0008-tqma8mx-Kconfig-prepare-bareboard-support.patch \
  file://0009-board-tqma8mx-split-into-module-and-baseboard-code.patch \
  file://0010-dm-imx-serial-add-i.MX6UL-support.patch \
  file://0011-serial-mxc-Add-match-string-for-i.mx6-quad-dual-lite.patch \
  file://0012-board-tqma8qx-tqma8qxs-fix-KConfig-select-statements.patch \
  file://0013-boards-tqma8mx-Kconfig-select-some-sensible-defaults.patch \
  file://0014-mmc-fsl_esdhc-revert-usage-of-CONFIG_IS_ENABLED-DM_R.patch \
  file://0015-power-pmic-uclass-allow-selection-of-CONFIG_PMIC_CHI.patch \
  file://0016-boards-tqma8mx-prepare-config-header-for-gpio-port-e.patch \
  file://0017-board-tqma8mx-use-generic-board_late_mmc_env_init.patch \
  file://0018-boards-tqma8mx-defconfig-regenerate.patch \
  file://0019-boards-tqma8mx-remove-unused-usb-gadget-and-video-co.patch \
  file://0020-board-tqc-common-prepare-prototypes-for-split-spl-in.patch \
  file://0021-board-tqma8mx-split-spl-in-module-and-mainboard-part.patch \
  file://0022-board-tqma8mx-prepare-RGMII-FEC-interface.patch \
  file://0023-arm-dt-tqma8mq-mba8mx-add-ethernet.patch \
  file://0024-arm-dt-fsl-imx8mq-tqma8mq-add-i2c-eeproms.patch \
"

S = "${WORKDIR}/git"

BOOT_TOOLS = "imx-boot-tools"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "tqma8qx-mba8qx|tqma8qxs-mb-smarc-2"

UBOOT_NAME_mx8 = "u-boot-${MACHINE}.bin-${UBOOT_CONFIG}"
