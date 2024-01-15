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

PREFERRED_PROVIDER_u-boot:mx7-generic-bsp ?= "u-boot-imx-tq"
PREFERRED_PROVIDER_virtual/bootloader:mx7-generic-bsp ?= "u-boot-imx-tq"
UBOOT_MAKE_TARGET:mx7-generic-bsp ?= "u-boot.imx"
UBOOT_SUFFIX:mx7-generic-bsp = "imx"
UBOOT_SUFFIX:pn-u-boot-imx-tq = "imx"
IMX_DEFAULT_BOOTLOADER ?= "u-boot-imx-tq"

# Image Options
#

# Add packages we want to be deployed, even when they are not part of rootfs
# We need these here to support UBI / NFS boot case
EXTRA_IMAGEDEPENDS += "\
    virtual/bootloader \
    virtual/kernel \
"

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

# Common image configuration
require conf/machine/include/tq-image.inc
