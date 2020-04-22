# Common definitions for all TQ-Group TQMa8Xx / TQMa8XxS modules

MACHINEOVERRIDES =. "mx8:mx8qxp:"

require conf/machine/include/arm/arch-arm64.inc

# Don't include kernels in standard images
RDEPENDS_${KERNEL_PACKAGE_NAME}-base = ""

LOADADDR = ""

# Machine specific options
#
MACHINE_EXTRA_RRECOMMENDS += " kernel-modules kernel-devicetree"
MACHINE_FEATURES_append = " can pci alsa rtc serial ext2 ext3 vfat usbhost"

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

PREFERRED_PROVIDER_virtual/kernel_mx8 ?= "linux-tq-imx"
KERNEL_IMAGETYPE = "Image"

PREFERRED_PROVIDER_u-boot_mx8 ?= "u-boot-tq-imx"
PREFERRED_PROVIDER_virtual/bootloader_mx8 ?= "u-boot-tq-imx"

UBOOT_MAKE_TARGET = "u-boot.bin"
UBOOT_SUFFIX = "bin"

# for sdcard image rule
BOOT_SPACE = "65536"
IMAGE_BOOTLOADER = "imx-boot"
# for sdcard + wic image rule
IMX_BOOT_SEEK = "32"

# Image Options
#
EXTRA_IMAGEDEPENDS += "u-boot imx-boot imx-sc-firmware"
IMAGE_FSTYPES_append = " tar.gz ext4 wic"
IMAGE_FSTYPES_remove = " tar.bz2 sdcard.bz2"

# need to have the kernel modules in rootfs
IMAGE_INSTALL_append = " kernel-modules"

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

WKS_FILE ?= "tqma8-sdimg.wks.in"

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

# need to do after imx-base, not included in firmware-imx-8.3
MACHINE_FIRMWARE_remove = "firmware-imx-brcm"