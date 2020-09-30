# TI K3 Armv7 profile for Cortex-R5F cores

require conf/machine/include/ti-soc.inc
SOC_FAMILY_append = ":k3r5"

# The closest of existing tunes for Cortex-R5F
DEFAULTTUNE = "armv7athf"
require conf/machine/include/tune-cortexa8.inc

# R5 runs early bootloader and loads SYSFW
PREFERRED_PROVIDER_virtual/kernel = "linux-dummy"
PREFERRED_PROVIDER_virtual/bootloader = "u-boot-tq"
PREFERRED_PROVIDER_u-boot = "u-boot-tq"

SPL_BINARY = ""
UBOOT_SUFFIX = "bin"
UBOOT_BINARY = "tiboot3.${UBOOT_SUFFIX}"
UBOOT_IMAGE = "tiboot3-${MAINMACHINE}-${PV}-${PR}.${UBOOT_SUFFIX}"
UBOOT_SYMLINK = "tiboot3-${MAINMACHINE}.${UBOOT_SUFFIX}"

PACKAGECONFIG_pn-u-boot-tq = ""
