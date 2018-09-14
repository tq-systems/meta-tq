
DESCRIPTION = "u-boot for TQ-Group NXP i.MX8x based modules"

require recipes-bsp/u-boot/u-boot.inc

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

PROVIDES += "u-boot"
DEPENDS_append = " dtc-native"

SRCREV = "03977f85d7b399833b681a86118b3c85b0c3ae31"
SRCBRANCH = "TQMa8xX-v2017.03-rel_imx_4.9.88_2.2.0_8qxp_beta2"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

S = "${WORKDIR}/git"

BOOT_TOOLS = "imx-boot-tools"

PACKAGE_ARCH = "${MACHINE_ARCH}"

# COMPATIBLE_MACHINE = "(tqma8qx-mba8qx)"

UBOOT_NAME_mx8 = "u-boot-${MACHINE}.bin-${UBOOT_CONFIG}"
