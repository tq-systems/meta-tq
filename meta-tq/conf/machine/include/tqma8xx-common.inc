# Common definitions for all TQ-Systems GmbH TQMa8Xx / TQMa8XxS modules

MACHINEOVERRIDES =. "imx:nxp-ahab:mx8qxp:mx8qxpc0:"

# optionally source the machine specific features for multimedia etc. from
# meta-freescale

include conf/machine/include/imx-base.inc
require conf/machine/include/arm/armv8a/tune-cortexa35.inc

require conf/machine/include/tqma8-common.inc

LOADADDR = ""

# Machine specific options

MACHINE_FEATURES:append = " can pci"

# TQ u-boot and kernel

PREFERRED_PROVIDER_imx-sc-firmware ?= "imx-sc-firmware-tq"

UBOOT_MAKE_TARGET ?= "all"
UBOOT_SUFFIX ?= "bin"

# multiple U-Boot env locations
TQ_FW_ENV_CONFIGS = "mmc0 mmc1 spi"

ATF_PLATFORM = "imx8qx"

# for sdcard + wic image rule
RAW_BOOT_START_OFFSET_KB = "32"
RAW_BOOT_END_OFFSET_KB = "4096"
# compatibility with meta-freescale
IMX_BOOT_SEEK = "${RAW_BOOT_START_OFFSET_KB}"

# Image Options

# Add packages we want to be deployed, even when they are not part of rootfs
EXTRA_IMAGEDEPENDS += "\
    imx-boot \
    imx-sc-firmware \
"

PREFERRED_VERSION_linux-imx-tq ?= "6.6.%"
