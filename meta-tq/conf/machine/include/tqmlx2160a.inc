# Common definitions for TQ-Systems GmbH TQMLX2160A modules

require conf/machine/include/soc-family.inc

MACHINEOVERRIDES =. "fsl-lsch3:lx2160a:tqmlsx:tqmlx2160a:"

# TQ-Systems U-Boot and kernel has to be set before qoriq-arm64.inc
# to allow weak settings
#
require conf/machine/include/tqmxx.inc

PREFERRED_PROVIDER_u-boot ?= "u-boot-lsdk-tq"
PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-lsdk-tq"
PREFERRED_PROVIDER_virtual/kernel ?= "linux-tq"

# source the machine specific features for RCW, multimedia etc. from
# meta-freescale
require conf/machine/include/qoriq-arm64.inc

require conf/machine/include/arm/armv8a/tune-cortexa72.inc

# Define the default image type
KERNEL_IMAGETYPE ?= "Image"

UBOOT_ENTRYPOINT:lx2160a = "0x81000000"

# Machine specific options

MACHINE_EXTRA_RRECOMMENDS += "\
    mc-utils-image \
    restool \
    udev-extraconf \
"

# provide info about machine's capabilities
MACHINE_FEATURES:append = "\
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

# Add packages we want to be deployed, even when they are not part of rootfs
EXTRA_IMAGEDEPENDS += "\
    ddr-phy \
    management-complex \
    mc-utils \
    qoriq-atf \
    rcw \
"

# The default SPI-NOR assembled on this SoM is larger than the default
# so we can safely increase count of usable LEBs
UBI_MAX_LEB_COUNT = "1280"

ATF_SECURE_SUFFIX ?= "${@bb.utils.contains('DISTRO_FEATURES', 'secure', '_sec', '', d)}"

BL2_IMAGE ?= "bl2_auto${ATF_SECURE_SUFFIX}.pbl"
BL3_IMAGE ?= "fip_uboot${ATF_SECURE_SUFFIX}.bin"
DDR_IMAGE ?= "ddr_fip${ATF_SECURE_SUFFIX}.bin"

RCW_FOLDER:${MACHINE} = "tqmlx2160a"
PLATFORM:${MACHINE} = "tqmlx2160a_32gb"
PLATFORM_ADDITIONAL_TARGET:${MACHINE} = "tqmlx2160a_16gb"

# Common image configuration
require conf/machine/include/tq-image.inc

# Add bootloader and boot firmware only SD/e-MMC image generation including
# support for different SoM variants.
# These images are stripped down versions of the normal .wic, consisting
# only of partition table, boot loader and VFAT containing firmware images.
IMAGE_CLASSES += "image_type_bootonly"
IMAGE_CLASSES += "image_type_pblvariant"

IMAGE_FSTYPES += "wic.bootonly"
IMAGE_FSTYPES += "wic.bootonly.pblvariant"
