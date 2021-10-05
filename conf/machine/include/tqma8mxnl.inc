# Common definitions for TQ-Group TQMa8MxNL modules

MACHINEOVERRIDES =. "imx:mx8:mx8m:mx8mn:tqma8mxnl:"

# Don't include kernels in standard images
RDEPENDS_${KERNEL_PACKAGE_NAME}-base = ""

LOADADDR = ""

# Machine specific options
#
MACHINE_FEATURES_append = " pci alsa"

# Set DDR FIRMWARE
DDR_FIRMWARE_NAME = "\
    lpddr4_pmu_train_1d_imem.bin \
    lpddr4_pmu_train_1d_dmem.bin \
    lpddr4_pmu_train_2d_imem.bin \
    lpddr4_pmu_train_2d_dmem.bin \
"

ATF_PLATFORM = "imx8mn"

LOADADDR = ""
UBOOT_SUFFIX = "bin"
UBOOT_MAKE_TARGET = ""

# for sdcard image rule
BOOT_SPACE = "65536"
IMAGE_BOOTLOADER = "imx-boot"

# for sdcard + wic image rule
IMX_BOOT_SEEK = "32"
IMX_BOOT_SOC_TARGET = "iMX8MN"

ATF_PLATFORM = "imx8mn"
# TODO:
# optee rule filters for matching machine names and expects at least imx for MX8
#MACHINE_FEATURES_append = " optee"
#OPTEE_BIN_EXT = "8mn"
DISTRO_FEATURES_remove = " optee"
MACHINE_FEATURES_remove = " optee"
IMAGE_INSTALL_remove = " packagegroup-fsl-optee-imx"

# Image Options
#
EXTRA_IMAGEDEPENDS += "u-boot imx-boot"

# Verified values to generate UBI image:
# UBIFS_PEB_SIZE="65536"
# UBIFS_MINIMUM_IO_UNIT_SIZE="1"
# UBIFS_SUB_PAGE_SIZE="1"
# UBIFS_VID_HEADER_OFFSET="64"
# UBIFS_LEB_SIZE="65408"

MKUBIFS_ARGS = " -m 1 -e 65408 -c 896 "
UBINIZE_ARGS = " -p 65536 -m 1 -s 1 "
# needs to be set to have it consistent for kernel command line
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
require conf/machine/include/tune-cortexa53.inc
