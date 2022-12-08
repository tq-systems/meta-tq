# Common definitions for R5 bootloader on TQMa64xxL (AM64xx SR2.0 HS-FS)

require tqma64xxl-k3r5.inc

# Booting SR2.0 HS-FS requires different SYSFW, the rest is handled at runtime

SOC_FAMILY:append = ":k3r5-sr2-hs-fs"

SYSFW_SOC = "am64x_sr2"
SYSFW_SUFFIX = "hs-fs"

UBOOT_IMAGE = "u-boot-r5spl-sr2-hs-fs-${MAINMACHINE}-${PV}-${PR}.${UBOOT_SUFFIX}"
UBOOT_SYMLINK = "u-boot-r5spl-sf2-hs-fs.${UBOOT_SUFFIX}"
