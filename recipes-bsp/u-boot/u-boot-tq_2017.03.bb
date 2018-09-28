
DESCRIPTION = "u-boot for TQ-Group NXP i.MX8x based modules"

require recipes-bsp/u-boot/u-boot.inc

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

PROVIDES += "u-boot"
DEPENDS_append = " dtc-native"

SRCREV = "a4a18ed146ec41f71d7710eed439da086b2474ec"
SRCBRANCH = "TQMa8xx-bringup-imx_v2017.03_4.9.88_imx8qxp_beta2"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

S = "${WORKDIR}/git"

BOOT_TOOLS = "imx-boot-tools"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "tqma8qx-mba8qx|tqma8qxa0-mba8qx"

UBOOT_NAME_mx8 = "u-boot-${MACHINE}.bin-${UBOOT_CONFIG}"
