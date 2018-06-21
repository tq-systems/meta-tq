# Common definitions for TQ-Group TQMa57xx modules

require conf/machine/include/dra7xx.inc

# Machine specific options
#
MACHINE_EXTRA_RRECOMMENDS = " kernel-modules kernel-devicetree"

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

PREFERRED_PROVIDER_virtual/kernel = "linux-tqma57xx-ti-staging-rt"
KERNEL_IMAGETYPE = "zImage"

PREFERRED_PROVIDER_u-boot = "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader = "u-boot-tq"

UBOOT_SUFFIX ?= "img"

# Image Options
#
EXTRA_IMAGEDEPENDS += "virtual/bootloader"

IMAGE_INSTALL_append = " kernel kernel-modules"
IMAGE_INSTALL_append = " kernel-devicetree kernel-image-zimage"

IMAGE_BOOT_FILES = "\
	MLO \
	u-boot.img \
	${KERNEL_IMAGETYPE} \
"

IMAGE_FSTYPES ?= "tar.gz ext4 wic"

do_image_wic[depends] += "\
	mtools-native:do_populate_sysroot \
	dosfstools-native:do_populate_sysroot \
	parted-native:do_populate_sysroot \
	virtual/kernel:do_deploy \
	u-boot-tq:do_deploy \
	"
