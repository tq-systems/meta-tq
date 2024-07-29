# Common definitions for TQ-Systems GmbH TQMa6ULL SOM

# optionally source the machine specific features for multimedia etc. from
# meta-freescale

MACHINEOVERRIDES =. "imx:mx6ull:tqma6ullx:"

include conf/machine/include/imx-base.inc

require conf/machine/include/arm/armv7a/tune-cortexa7.inc

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

# Machine specific options

MACHINE_EXTRA_RRECOMMENDS += "\
    kernel-devicetree \
    kernel-modules \
    linux-firmware-imx-sdma-imx6q \
"

MACHINE_FEATURES += "\
    alsa \
    can \
    ext2 \
    rtc \
    serial \
    ubi \
    usbhost \
    vfat \
"

KERNEL_IMAGETYPE = "zImage"

PREFERRED_PROVIDER_u-boot:mx6ull-generic-bsp ?= "u-boot-imx-tq"
PREFERRED_PROVIDER_virtual/bootloader:mx6ull-generic-bsp ?= "u-boot-imx-tq"
UBOOT_MAKE_TARGET:mx6ull-generic-bsp ?= "u-boot.imx"
UBOOT_SUFFIX:mx6ull-generic-bsp = "imx"
UBOOT_SUFFIX:pn-u-boot-imx-tq = "imx"
IMX_DEFAULT_BOOTLOADER ?= "u-boot-imx-tq"

# Common image configuration
RAW_BOOT_START_OFFSET_KB = "1"
RAW_BOOT_END_OFFSET_KB = "1024"

require conf/machine/include/tq-image.inc
