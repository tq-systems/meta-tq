# Common definitions for all TQ-Systems GmbH TQMa8x modules

MACHINEOVERRIDES =. "imx:mx8:mx8qm:"

# Don't include kernels in standard images
RDEPENDS:${KERNEL_PACKAGE_NAME}-base = ""

LOADADDR = ""

# Machine specific options
#
MACHINE_FEATURES:append = " can pci"

PREFERRED_PROVIDER_virtual/imx-cortexm-demos = "tqma8-cortexm-demos"
PREFERRED_PROVIDER_imx-sc-firmware ?= "imx-sc-firmware-tq"

UBOOT_MAKE_TARGET ?= "all"
UBOOT_SUFFIX ?= "bin"

ATF_PLATFORM = "imx8qm"

# for sdcard image rule
BOOT_SPACE = "65536"
IMAGE_BOOTLOADER = "imx-boot"
# for sdcard + wic image rule
IMX_BOOT_SEEK = "32"
IMX_BOOT_SOC_TARGET = "iMX8QM"


# Image Options
#
EXTRA_IMAGEDEPENDS += "u-boot imx-boot imx-sc-firmware"

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

WKS_FILE_DEPENDS = "\
    mtools-native \
    dosfstools-native \
    parted-native \
    virtual/kernel \
    virtual/bootloader \
    imx-boot \
    imx-sc-firmware \
"

# optionally source the machine specific features for multimedia etc. from
# meta-freescale

include conf/machine/include/imx-base.inc
require conf/machine/include/arm/armv8a/tune-cortexa72-cortexa53.inc

# need to do after imx-base, not included in firmware-imx-8.3
MACHINE_FIRMWARE:remove = "firmware-imx-brcm"
