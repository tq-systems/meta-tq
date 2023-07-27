# Common definitions for TQ-Systems GmbH TQMLS10xxA modules

require conf/machine/include/tqmxx.inc

require conf/machine/include/arm/arch-arm64.inc

# source the machine specific features for RCW, MC, multimedia etc. from
# meta-freescale
require conf/machine/include/qoriq-arm64.inc

PREFERRED_PROVIDER_u-boot ?= "u-boot-lsdk-tq"
PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-lsdk-tq"
PREFERRED_PROVIDER_virtual/kernel ?= "linux-ls-tq"

# Define the default image type
KERNEL_IMAGETYPE ?= "Image"

# Machine specific options
#
MACHINE_EXTRA_RRECOMMENDS += "\
    kernel-devicetree \
    kernel-modules \
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
# We need these here to support UBI / NFS boot case
EXTRA_IMAGEDEPENDS += "\
    rcw \
    virtual/bootloader \
    virtual/kernel \
    qoriq-atf \
"

EXTRA_IMAGEDEPENDS:append:fsl-lsch2 = "\
    fm-ucode \
"

EXTRA_IMAGEDEPENDS:append:fsl-lsch3 = "\
    management-complex \
    mc-utils \
"

# Verified values to generate UBI image:
# physical erase block size
UBI_PEB_SIZE = "65536"
UBI_MINIMUM_IO_UNIT_SIZE = "1"
UBI_SUB_PAGE_SIZE = "1"
# UBI_VID_HEADER_OFFSET="64"
# logical erase block size
UBI_LEB_SIZE = "65408"

UBI_MAX_LEB_COUNT = "640"

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
