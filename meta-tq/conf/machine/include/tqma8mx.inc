# Common definitions for TQ-Systems GmbH TQMa8MX modules

MACHINEOVERRIDES =. "imx:mx8mq:tqma8mq:"

# Don't include kernels in standard images
RDEPENDS:${KERNEL_PACKAGE_NAME}-base = ""

LOADADDR = ""

# Machine specific options
#
MACHINE_FEATURES:append = " pci"

# Set DDR FIRMWARE
DDR_FIRMWARE_NAME = "\
    lpddr4_pmu_train_1d_imem.bin \
    lpddr4_pmu_train_1d_dmem.bin \
    lpddr4_pmu_train_2d_imem.bin \
    lpddr4_pmu_train_2d_dmem.bin \
"

LOADADDR = ""
UBOOT_SUFFIX = "bin"
UBOOT_MAKE_TARGET = ""

# for sdcard image rule
BOOT_SPACE = "65536"
IMAGE_BOOTLOADER ?= "imx-boot"

ATF_PLATFORM = "imx8mq"

# for sdcard + wic image rule
IMX_BOOT_SEEK = "33"
IMX_BOOT_SOC_TARGET = "iMX8M"

# TODO:
# optee rule filters for matching machine names and expects at least imx for MX8
#MACHINE_FEATURES:append = " optee"
#OPTEE_BIN_EXT = "8mq"
DISTRO_FEATURES:remove = "optee"
MACHINE_FEATURES:remove = "optee"
IMAGE_INSTALL:remove = "packagegroup-fsl-optee-imx"

# Image Options
#
EXTRA_IMAGEDEPENDS += "\
    imx-boot \
    u-boot \
"

# Verified values to generate UBI image:
# physical erase block size
UBI_PEB_SIZE = "65536"
UBI_MINIMUM_IO_UNIT_SIZE = "1"
UBI_SUB_PAGE_SIZE = "1"
# UBI_VID_HEADER_OFFSET="64"
# logical erase block size
UBI_LEB_SIZE = "65408"

# we have 64 MB flash, 8 MB for env and spare should be enough (CPU has no support
# for QSPI Boot
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
"

# optionally source the machine specific features for multimedia etc. from
# meta-freescale

include conf/machine/include/imx-base.inc
require conf/machine/include/arm/armv8a/tune-cortexa53.inc

PREFERRED_VERSION_linux-imx-tq = "5.15%"
