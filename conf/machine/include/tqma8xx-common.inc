# Common definitions for all TQ-Group TQMa8Xx / TQMa8XxS modules

MACHINEOVERRIDES =. "mx8:mx8x:mx8qxp:mx8qxpc0:"

# Don't include kernels in standard images
RDEPENDS_${KERNEL_PACKAGE_NAME}-base = ""

LOADADDR = ""

# Machine specific options
#
MACHINE_FEATURES_append = " can pci"

# TQ u-boot and kernel
#
PREFERRED_PROVIDER_imx-sc-firmware ?= "imx-sc-firmware-tq"

UBOOT_MAKE_TARGET ?= "all"
UBOOT_SUFFIX ?= "bin"

ATF_PLATFORM = "imx8qx"

# for sdcard image rule
BOOT_SPACE = "65536"
IMAGE_BOOTLOADER = "imx-boot"
# for sdcard + wic image rule
IMX_BOOT_SEEK = "32"
IMX_BOOT_SOC_TARGET = "iMX8QX"

# Image Options
#
EXTRA_IMAGEDEPENDS += "u-boot imx-boot imx-sc-firmware"

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
	imx-sc-firmware \
	"

# optionally source the machine specific features for multimedia etc. from
# meta-freescale

include conf/machine/include/imx-base.inc
require conf/machine/include/tune-cortexa35.inc

# need to do after imx-base, not included in firmware-imx-8.3
MACHINE_FIRMWARE_remove = "firmware-imx-brcm"
