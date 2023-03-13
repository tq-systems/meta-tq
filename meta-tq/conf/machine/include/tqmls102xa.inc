# Common definitions for TQ-Systems GmbH TQMLS102xa modules

require conf/machine/include/soc-family.inc

# TQ-Systems U-Boot and kernel has to be set before qoriq-arm.inc
# to allow weak settings
#
require conf/machine/include/tqmxx.inc

PREFERRED_PROVIDER_u-boot ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/kernel ?= "linux-tq"


# optionally source the machine specific features for multimedia etc. from
# meta-freescale
include conf/machine/include/qoriq-arm.inc

MACHINEOVERRIDES =. "ls102xa:tqmls102xa:"

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
    kernel-devicetree \
    kernel-modules \
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

# Image Options
#
EXTRA_IMAGEDEPENDS += "virtual/bootloader"

# Verified values to generate UBI image:
# physical erase block size
UBI_PEB_SIZE = "65536"
UBI_MINIMUM_IO_UNIT_SIZE = "1"
UBI_SUB_PAGE_SIZE = "1"
# UBI_VID_HEADER_OFFSET="64"
# logical erase block size
UBI_LEB_SIZE = "65408"

# we have 64 MB flash, 8 MB for bootloader and env should be enough
# so use 1024 - 128 LEB
UBI_MAX_LEB_COUNT = "896"

MKUBIFS_ARGS = "\
    --min-io-size=${UBI_MINIMUM_IO_UNIT_SIZE} \
    --leb-size=${UBI_LEB_SIZE} \
    --max-leb-cnt=${UBI_MAX_LEB_COUNT} \
"

UBINIZE_ARGS = "\
    --peb-size=${UBI_PEB_SIZE} \
    --min-io-size=${UBI_MINIMUM_IO_UNIT_SIZE} \
    --sub-page-size=${UBI_SUB_PAGE_SIZE} \
"

# needs to be set to have it consistent for kernel command line
# Use the expected value of the ubifs filesystem's volume name in the kernel
# and u-boot.
UBI_VOLNAME = "root"

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"
