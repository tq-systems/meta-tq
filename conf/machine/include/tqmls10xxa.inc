# Common definitions for TQ-Group TQMLS10xxA modules

require conf/machine/include/arm/arch-arm64.inc

# Define the default image type
KERNEL_IMAGETYPE ?= "Image"

PREFERRED_PROVIDER_u-boot_tqmls10xxa = "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader_tqmls10xxa = "u-boot-tq"
PREFERRED_PROVIDER_virtual/kernel_tqmls10xxa = "linux-tq"

# Machine specific options
#
MACHINE_EXTRA_RRECOMMENDS += "kernel-modules kernel-devicetree udev-extraconf"

# provide info about machine's capabilities
MACHINE_FEATURES_append = " ext2 pci rtc serial usbhost vfat"

# Image Options
#
EXTRA_IMAGEDEPENDS += "virtual/bootloader"
EXTRA_IMAGEDEPENDS_append_fsl-lsch2 += "fm-ucode"
EXTRA_IMAGEDEPENDS_append_fsl-lsch3 += "rcw mc-utils management-complex"

IMAGE_FSTYPES ?= "tar.gz ext4 wic ubi"
IMAGE_FSTYPES_remove = " ext2.gz.u-boot ext2.gz"

# DISTRO_FEATURES_remove += "${@bb.utils.contains('IMAGE_FSTYPES', 'ubi', 'wayland weston x11', '', d)}"
IMAGE_FSTYPES_remove += "${@bb.utils.contains('DISTRO_FEATURES', 'vulkan', 'ubi', '', d)}"
IMAGE_FSTYPES_remove += "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'ubi', '', d)}"
IMAGE_FSTYPES_remove += "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'ubi', '', d)}"

# needed for Rootfs on QSPI
IMAGE_INSTALL_append = " mtd-utils-jffs2 mtd-utils-ubifs mtd-utils-misc"
IMAGE_INSTALL_append = " kernel-modules"

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

# Different RCW files for booting CPU from SD card and eMMC require
# setting the correct BOOTMODE:
# sd -> to boot from SD-Card
# emmc -> to boot from eMMC
BOOTMODE ?= "sd"
RCWIMG ?= "tqmls1088a-mbls10xxa/${@d.getVar('RCW' + d.getVar('BOOTMODE').upper())}"

WICVARS_append = " RCWIMG MC_DPL MC_DPC"

WKS_FILE_DEPENDS = "\
	mtools-native \
	dosfstools-native \
	parted-native \
	virtual/kernel \
	virtual/bootloader \
	"

# source the machine specific features for RCW, MC, multimedia etc. from
# meta-freescale
require conf/machine/include/qoriq-arm64.inc
