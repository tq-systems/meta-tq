# Common definitions for TQ-Systems GmbH TQMa7x modules

MACHINEOVERRIDES =. "imx:mx7:mx7d:tqma7x:"

# optionally source the machine specific features for multimedia etc. from
# meta-freescale
include conf/machine/include/imx-base.inc

require conf/machine/include/arm/armv7a/tune-cortexa7.inc

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

# Machine specific options
#
MACHINE_EXTRA_RRECOMMENDS += "\
    kernel-devicetree \
    kernel-modules \
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
UBOOT_MAKE_TARGET:mx7-generic-bsp ?= "u-boot.imx"
UBOOT_SUFFIX:mx7-generic-bsp = "imx"
IMX_DEFAULT_BOOTLOADER ?= "u-boot-tq"

# multiple U-Boot env locations
TQ_FW_ENV_CONFIGS = "mmc0 mmc1 spi"

# Common image configuration
RAW_BOOT_START_OFFSET_KB = "1"
RAW_BOOT_END_OFFSET_KB = "1024"

require conf/machine/include/tq-image.inc
