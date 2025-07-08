# Common definitions for TQ-Systems GmbH TQMa7x modules

MACHINEOVERRIDES =. "imx:mx7:mx7d:tqma7x:"

# optionally source the machine specific features for multimedia etc. from
# meta-freescale
include conf/machine/include/imx-base.inc

# Override incompatible defaults from imx-base.inc.
IMAGE_BOOT_FILES = "u-boot-initial-env-sd"

require conf/machine/include/arm/armv7a/tune-cortexa7.inc

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

# Machine specific options
#
MACHINE_EXTRA_RRECOMMENDS += "\
    linux-firmware-imx-sdma-imx7d \
"

MACHINE_FEATURES += "\
    alsa \
    can \
    ext2 \
    pci \
    rtc \
    serial \
    ubi \
    usbhost \
    vfat \
"

KERNEL_IMAGETYPE = "zImage"

PREFERRED_PROVIDER_u-boot:mx7-generic-bsp ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader:mx7-generic-bsp ?= "u-boot-tq"
UBOOT_SUFFIX:mx7-generic-bsp = "imx"
UBOOT_BINARY_BASENAME:mx7-generic-bsp = "u-boot-dtb"
UBOOT_MAKE_TARGET:mx7-generic-bsp ?= "${UBOOT_BINARY_BASENAME}.${UBOOT_SUFFIX}"
UBOOT_BINARY:mx7-generic-bsp = "${UBOOT_MAKE_TARGET}"
IMX_DEFAULT_BOOTLOADER ?= "u-boot-tq"

# multiple U-Boot env locations
TQ_FW_ENV_CONFIGS = "mmc0 mmc1 spi"

# allow to easy override for different configuration
MACHINE_WKS_BOOTSTREAM ?= "${UBOOT_BINARY_BASENAME}-${MACHINE}.${UBOOT_SUFFIX}-sd"

WKS_FILE = "tqmaxx-imx-v7-sdimg.wks.in"

# Common image configuration
RAW_BOOT_START_OFFSET_KB = "1"
RAW_BOOT_END_OFFSET_KB = "1024"

require conf/machine/include/tq-image.inc
