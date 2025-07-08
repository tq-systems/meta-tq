# Common definitions for all TQ-Systems GmbH TQMa8x modules (B0 CPU step)

MACHINEOVERRIDES =. "imx:nxp-ahab:mx8:mx8qm:tqma8x:"

# optionally source the machine specific features for multimedia etc. from
# meta-freescale

include conf/machine/include/imx-base.inc
require conf/machine/include/arm/armv8a/tune-cortexa72-cortexa53.inc

require conf/machine/include/tqma8-common.inc

LOADADDR = ""

# Machine specific options

MACHINE_FEATURES:append = " can pci"

PREFERRED_PROVIDER_virtual/imx-cortexm-demos = "tqma8-cortexm-demos"
PREFERRED_PROVIDER_imx-sc-firmware ?= "imx-sc-firmware-tq"

UBOOT_MAKE_TARGET ?= "all"
UBOOT_SUFFIX ?= "bin"

# multiple U-Boot env locations
TQ_FW_ENV_CONFIGS = "mmc0 mmc1 spi"

ATF_PLATFORM = "imx8qm"

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

# Debug UART index. To activate 'ATF_IMX_DEBUG_CONSOLE' must be set to '1'
ATF_IMX_DEBUG_UART ?= "0"

#
# bootstream name, construction see imx-boot recipe, do_deploy
#
MACHINE_WKS_BOOTSTREAM ?= "imx-boot-${MACHINE}-sd.bin-flash_spl"
