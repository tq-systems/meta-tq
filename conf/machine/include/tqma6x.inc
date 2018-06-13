# Common definitions for TQ-Group TQMa6 modules

DEFAULTTUNE ?= "cortexa9thf-neon"
require conf/machine/include/tune-cortexa9.inc

# Machine specific options
#
MACHINE_EXTRA_RRECOMMENDS = " kernel-modules kernel-devicetree"
MACHINE_FEATURES_append = " alsa ext2 pci rtc serial usbhost vfat"

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

PREFERRED_PROVIDER_virtual/kernel ?= "linux-tq"
KERNEL_IMAGETYPE = "zImage"

PREFERRED_PROVIDER_u-boot = "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-tq"

UBOOT_SUFFIX ?= "imx"
UBOOT_MAKE_TARGET ?= "u-boot.imx"

# Image Options
#
EXTRA_IMAGEDEPENDS += "u-boot"

IMAGE_FSTYPES ?= "tar.gz ext4 wic"

WKS_FILE_DEPENDS = "\
	mtools-native \
	dosfstools-native \
	parted-native \
	virtual/kernel \
	u-boot \
	"

# optionally source the machine specific features for multimedia etc. from
# meta-freescale
include conf/machine/include/imx-base.inc
