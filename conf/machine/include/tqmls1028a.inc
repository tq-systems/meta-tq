# Common definitions for TQ-Group TQMLS1028a modules

require conf/machine/include/soc-family.inc

MACHINEOVERRIDES =. "tqmls1028a:ls1028a:fsl-lsch3:"


require conf/machine/include/tune-cortexa72.inc

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

PREFERRED_PROVIDER_u-boot_tqmls1028a ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader_tqmls1028a ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/kernel_tqmls1028a ?= "linux-lsdk-tq"

# Define the default image type
KERNEL_IMAGETYPE ?= "Image.gz"

UBOOT_ENTRYPOINT_ls1028a = "0x80008000"

# Machine specific options
#

MACHINE_EXTRA_RRECOMMENDS += "kernel-modules kernel-devicetree udev-extraconf"

# provide info about machine's capabilities
MACHINE_FEATURES_append = " alsa ext2 pci rtc serial usbhost vfat can"

# Image Options
#
EXTRA_IMAGEDEPENDS += "virtual/bootloader"
EXTRA_IMAGEDEPENDS += "rcw ppa dp-firmware-cadence"

IMAGE_FSTYPES_append = " tar.gz ext4 wic"
IMAGE_FSTYPES_remove = " tar.bz2 sdcard.bz2"

DISTRO_FEATURES_remove += "${@bb.utils.contains('IMAGE_FSTYPES', 'ubi', 'wayland weston x11', '', d)}"

# needed for Rootfs on QSPI
IMAGE_INSTALL_append = " mtd-utils-jffs2 mtd-utils-ubifs mtd-utils-misc"
IMAGE_INSTALL_append = " kernel-modules"

# Image Options
#
MKUBIFS_ARGS = " -m 1 -e 65408 -c 896 "
UBINIZE_ARGS = " -p 65536 -m 1 -s 1 "

# needs to be set to have it consistent for kernel command line
UBI_VOLNAME = "root"

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

# Different RCW files for booting CPU from SD card and eMMC require
# setting the correct BOOTMODE:
# sd -> to boot from SD-Card
# emmc -> to boot from eMMC
BOOTMODE ?= "sd"
RCWIMG ?= "tqc/${@d.getVar('RCW' + d.getVar('BOOTMODE').upper())}"

WKS_FILE ?= "tqmls1028a-sdimg.wks.in"

WICVARS_append = " BOOTMODE RCWIMG"

WKS_FILE_DEPENDS = "\
	mtools-native \
	dosfstools-native \
	parted-native \
	virtual/kernel \
	virtual/bootloader \
	"

# source the machine specific features for RCW, multimedia etc. from
# meta-freescale
require conf/machine/include/qoriq-arm64.inc