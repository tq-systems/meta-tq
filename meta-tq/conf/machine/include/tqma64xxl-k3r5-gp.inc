# Common definitions for R5 bootloader on TQMa64xxL (AM64xx GP)

require tqma64xxl-k3r5.inc

# Booting GP requires different SYSFW, the rest is handled at runtime

SOC_FAMILY:append = ":k3r5-gp"

SYSFW_SOC = "am64x"
SYSFW_SUFFIX = "gp"

UBOOT_IMAGE = "u-boot-r5spl-gp-${MAINMACHINE}-${PV}-${PR}.${UBOOT_SUFFIX}"
UBOOT_SYMLINK = "u-boot-r5spl-gp.${UBOOT_SUFFIX}"
