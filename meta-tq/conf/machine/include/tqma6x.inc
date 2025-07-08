# Common definitions for TQ-Systems GmbH TQMa6 SOM

# optionally source the machine specific features for multimedia etc. from
# meta-freescale

MACHINEOVERRIDES =. "imx:mx6:tqma6x:"

include conf/machine/include/imx-base.inc

# Override incompatible defaults from imx-base.inc.
IMAGE_BOOT_FILES = "u-boot-initial-env-sd"

require conf/machine/include/arm/armv7a/tune-cortexa9.inc

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

# Machine specific options

MACHINE_EXTRA_RRECOMMENDS += "\
    firmware-imx-vpu-imx6d \
    firmware-imx-vpu-imx6q \
    linux-firmware-imx-sdma-imx6q \
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
    vpu \
"

KERNEL_IMAGETYPE = "zImage"

PREFERRED_PROVIDER_u-boot:mx6-generic-bsp ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader:mx6-generic-bsp ?= "u-boot-tq"
UBOOT_SUFFIX:mx6-generic-bsp = "imx"
UBOOT_BINARY_BASENAME:mx6-generic-bsp = "u-boot-with-spl"
UBOOT_MAKE_TARGET:mx6-generic-bsp = "${UBOOT_BINARY_BASENAME}.${UBOOT_SUFFIX}"
UBOOT_BINARY:mx6-generic-bsp = "${UBOOT_MAKE_TARGET}"
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
