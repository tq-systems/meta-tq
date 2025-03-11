# Common definitions for TQ-Systems GmbH TQMLS10xxA modules

require conf/machine/include/tqmxx.inc

# source the machine specific features for RCW, MC, multimedia etc. from
# meta-freescale
require conf/machine/include/qoriq-arm64.inc

require conf/machine/include/arm/arch-armv8a.inc

PREFERRED_PROVIDER_u-boot ?= "u-boot-lsdk-tq"
PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-lsdk-tq"
PREFERRED_PROVIDER_virtual/kernel ?= "linux-tq"

# Define the default image type
KERNEL_IMAGETYPE ?= "Image"

# Machine specific options
#
MACHINE_EXTRA_RRECOMMENDS += "\
    udev-extraconf \
"

# provide info about machine's capabilities
MACHINE_FEATURES:append = "\
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

# Add packages we want to be deployed, even when they are not part of rootfs
EXTRA_IMAGEDEPENDS += "\
    rcw \
    qoriq-atf \
"

EXTRA_IMAGEDEPENDS:append:fsl-lsch2 = "\
    fm-ucode \
"

EXTRA_IMAGEDEPENDS:append:fsl-lsch3 = "\
    management-complex \
    mc-utils \
"

# bootloader and firmware need more space, so there is less than default LEBs available
UBI_MAX_LEB_COUNT = "865"

# Composite Image Layout (in KBytes)
IMAGE_OFFSET_PBL ?= "4"
IMAGE_OFFSET_FIP ?= "1024"
IMAGE_OFFSET_FMAN_UCODE ?= "9216"
IMAGE_MAXSIZE_PBL ?= "1020"

BL2_IMAGE:tqmls104xa ?= "bl2_sd.pbl"
BL2_IMAGE:tqmls1088a ?= "bl2_auto.pbl"
BL3_IMAGE ?= "fip_uboot.bin"

BOOTTYPE:tqmls104xa ?= "\
    sd \
    qspi \
"
BOOTTYPE:tqmls1088a ?= "\
    auto \
    qspi \
"

# Common image configuration
require conf/machine/include/tq-image.inc

# Add bootloader and boot firmware only SD/e-MMC image generation including
# support for different SoM variants.
# These images are stripped down versions of the normal .wic, consisting
# only of partition table, boot loader and VFAT containing firmware images.
IMAGE_CLASSES += "image_type_bootonly"

IMAGE_FSTYPES += "wic.bootonly"
