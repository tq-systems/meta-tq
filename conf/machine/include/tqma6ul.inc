# Common definitions for TQ-Group TQMa6UL modules

MACHINEOVERRIDES =. "mx6:mx6ul:tqma6ulx:"

DEFAULTTUNE ?= "cortexa7thf-neon"
require conf/machine/include/tune-cortexa7.inc

# Machine specific options
#
MACHINE_EXTRA_RRECOMMENDS = " kernel-modules kernel-devicetree"
MACHINE_FEATURES_append = " alsa ext2 rtc serial usbhost vfat"

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

PREFERRED_PROVIDER_virtual/kernel_tqma6ulx ?= "linux-tq"
KERNEL_IMAGETYPE = "zImage"

PREFERRED_PROVIDER_u-boot_tqma6ulx = "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader_tqma6ulx ?= "u-boot-tq"

UBOOT_SUFFIX ?= "imx"
UBOOT_MAKE_TARGET ?= "u-boot.imx"

UBOOT_CONFIG = "sd qspi"

# Image Options
#
EXTRA_IMAGEDEPENDS += "virtual/bootloader"
IMAGE_FSTYPES_append = " tar.gz ext4 wic ubi"
IMAGE_FSTYPES_remove = " tar.bz2 sdcard.bz2"

# Don't include kernels in standard images
RDEPENDS_${KERNEL_PACKAGE_NAME}-base = ""

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

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

do_image_wic[depends] += " \
	mtools-native:do_populate_sysroot \
	dosfstools-native:do_populate_sysroot \
	parted-native:do_populate_sysroot \
	virtual/kernel:do_deploy \
	u-boot:do_deploy \
	"

# optionally source the machine specific features for multimedia etc. from
# meta-freescale
include conf/machine/include/imx-base.inc
