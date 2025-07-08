# Common definitions for TQ-Systems GmbH TQMLS102xA modules

require conf/machine/include/soc-family.inc

# TQ-Systems U-Boot and kernel has to be set before qoriq-arm.inc
# to allow weak settings
#
require conf/machine/include/tqmxx.inc

PREFERRED_PROVIDER_u-boot ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/kernel ?= "linux-tq"

MACHINEOVERRIDES =. "ls102xa:tqmls102xa:"

# optionally source the machine specific features for multimedia etc. from
# meta-freescale
include conf/machine/include/qoriq-arm.inc

DEFAULTTUNE ?= "cortexa7thf-neon"
require conf/machine/include/arm/armv7a/tune-cortexa7.inc

# Define the default image type, we cannot use default assignment due to the
# default assignment in qoriq-arm.inc (meta-freescale).
KERNEL_IMAGETYPE = "zImage"

UBOOT_ENTRYPOINT:ls102xa = "0x80008000"
UBOOT_SUFFIX = "bin"

# Machine specific options
#

MACHINE_EXTRA_RRECOMMENDS += "\
    udev-extraconf \
"

# provide info about machine's capabilities
MACHINE_FEATURES:append = "\
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

# Common image configuration
RAW_BOOT_START_OFFSET_KB = "4"
RAW_BOOT_END_OFFSET_KB = "1024"

# allow to easy override for different rcw
MACHINE_WKS_BOOTSTREAM ?= "u-boot-${MACHINE}.bin"

WKS_FILE = "tqmaxx-imx-v7-sdimg.wks.in"

# Common image configuration
require conf/machine/include/tq-image.inc
