# Common definitions for TQ-Systems GmbH TQMa93xxLA / TQMa93xxCA

MACHINEOVERRIDES =. "imx:mx9:mx93:tqma93xx:"

# optionally source the machine specific features for multimedia etc. from
# meta-freescale

include conf/machine/include/imx-base.inc
require conf/machine/include/arm/armv8-2a/tune-cortexa55.inc

require conf/machine/include/tqmxx.inc

LOADADDR = ""

# Set DDR FIRMWARE
DDR_FIRMWARE_NAME = "\
    lpddr4_dmem_1d_v202201.bin \
    lpddr4_dmem_2d_v202201.bin \
    lpddr4_imem_1d_v202201.bin \
    lpddr4_imem_2d_v202201.bin \
"

IMX_SOC_REV ?= "A1"

ATF_PLATFORM = "imx93"

LOADADDR = ""
UBOOT_SUFFIX = "bin"
UBOOT_MAKE_TARGET = ""
SPL_BINARY = "spl/u-boot-spl.bin"

# Image Options

# Add packages we want to be deployed, even when they are not part of rootfs
# We need these here to support UBI / NFS boot case
EXTRA_IMAGEDEPENDS += "\
    imx-boot \
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

# for sdcard + wic image rule
RAW_BOOT_START_OFFSET_KB = "32"
RAW_BOOT_END_OFFSET_KB = "4096"
# compatibility with meta-freescale
IMX_BOOT_SEEK = "${RAW_BOOT_START_OFFSET_KB}"
# imx-mkimage[-tq]
IMX_BOOT_SOC_TARGET = "iMX9"

MACHINE_EXTRA_RRECOMMENDS += "\
    kernel-devicetree \
    kernel-modules \
"

MACHINE_FEATURES += "\
    can \
    ext2 \
    ext3 \
    rtc \
    serial \
    ubi \
    vfat \
"

KERNEL_IMAGETYPE = "Image"

PREFERRED_PROVIDER_imx-boot ?= "imx-boot-tq"
PREFERRED_PROVIDER_virtual/kernel:mx93-nxp-bsp ?= "linux-imx-tq"
# At the moment we support only these forks:
PREFERRED_PROVIDER_imx-atf:mx93-generic-bsp = "imx-atf-tq"
PREFERRED_PROVIDER_u-boot:mx93-generic-bsp ?= "u-boot-imx-tq"
PREFERRED_PROVIDER_virtual/bootloader:mx93-generic-bsp ?= "u-boot-imx-tq"
UBOOT_SUFFIX:mx93-generic-bsp = "bin"
UBOOT_SUFFIX:pn-u-boot-imx-tq = "bin"
IMX_DEFAULT_BOOTLOADER ?= "u-boot-imx-tq"

