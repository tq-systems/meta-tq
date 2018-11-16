
DESCRIPTION = "u-boot for TQ-Group NXP i.MX8xX based modules"

require recipes-bsp/u-boot/u-boot.inc

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

PROVIDES += "u-boot"
DEPENDS_append = " dtc-native"

SRCREV = "35762d3707833f1510b918473aedcd3ccb385d98"
SRCBRANCH = "TQMa8xx-bringup-imx_v2017.03_4.9.88_imx8qxp_beta2"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
           file://0001-tqma8qx-fix-ram-size.patch \
           file://0002-tqma8qx-mba8qx-dt-fixes-for-usb-otg1.patch \
           file://0003-arm-dt-fsl-imx8qxp-tqma8qx-mba8qx-remove-unused-lpua.patch \
           file://0004-tqma8qx-remove-unused-pinmux-defines.patch \
           file://0005-tqma8qx-move-board_quiesce_devices-to-mainboard-file.patch \
           file://0006-tqma8qx-fix-header-guard-in-board-config-header.patch \
           file://0007-tqma8qx-mba8qx-use-gpio-pad-ctrl-define-for-enet-res.patch \
           file://0008-add-tqma8qxs-with-mb-smarc-2.patch \
           file://0009-tqma8qx-default-DT-to-B0-Stepping.patch \
           file://0010-tqma8qxs-default-fdt_file-to-BO-stepping-CPU.patch \
           "

S = "${WORKDIR}/git"

BOOT_TOOLS = "imx-boot-tools"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "tqma8qx-mba8qx|tqma8qxa0-mba8qx|tqma8qxs-mb-smarc-2"

UBOOT_NAME_mx8 = "u-boot-${MACHINE}.bin-${UBOOT_CONFIG}"
