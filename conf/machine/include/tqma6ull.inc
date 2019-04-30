# Common definitions for TQ-Group TQMa6ULL modules

MACHINEOVERRIDES =. "mx6:mx6ull:tqma6ullx:"

DEFAULTTUNE ?= "cortexa7thf-neon"
require conf/machine/include/tune-cortexa7.inc

# Machine specific options
#
MACHINE_EXTRA_RRECOMMENDS = " kernel-modules kernel-devicetree"
MACHINE_FEATURES_append = " alsa ext2 rtc serial usbhost vfat"

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

PREFERRED_PROVIDER_virtual/kernel = "linux-tq"
KERNEL_IMAGETYPE = "zImage"

PREFERRED_PROVIDER_u-boot = "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader = "u-boot-tq"

UBOOT_SUFFIX ?= "imx"
UBOOT_MAKE_TARGET ?= "u-boot.imx"

UBOOT_CONFIG = "sd qspi"

# Image Options
#
EXTRA_IMAGEDEPENDS += "virtual/bootloader"
IMAGE_FSTYPES ?= "tar.gz ext4 wic ubi"

DISTRO_FEATURES_remove += "${@bb.utils.contains('IMAGE_FSTYPES', 'ubi', 'wayland weston x11', '', d)}"

# needed for Rootfs on QSPI
IMAGE_INSTALL_append = " mtd-utils-jffs2 mtd-utils-ubifs mtd-utils-misc"
# need to have the kernel modules in rootfs
IMAGE_INSTALL_append = " kernel-modules"

# from PTXdist BSP:
# PTXCONF_IMAGE_ROOT_UBIFS_PEB_SIZE="65536"
# PTXCONF_IMAGE_ROOT_UBIFS_MINIMUM_IO_UNIT_SIZE="1"
# PTXCONF_IMAGE_ROOT_UBIFS_SUB_PAGE_SIZE="1"
# PTXCONF_IMAGE_ROOT_UBIFS_VID_HEADER_OFFSET="64"
# PTXCONF_IMAGE_ROOT_UBIFS_LEB_SIZE="65408"

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
	"

# TODO:
#IMAGE_INSTALL_append = " kernel-devicetree kernel-image-zimage"

# optionally source the machine specific features for multimedia etc. from
# meta-freescale
include conf/machine/include/imx-base.inc
