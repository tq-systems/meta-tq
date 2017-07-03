# Common definitions for TQ-Group TQMLS102xa modules

require conf/machine/include/soc-family.inc

MACHINEOVERRIDES =. "ls102xa:"

DEFAULTTUNE ?= "cortexa7thf-neon"
require conf/machine/include/tune-cortexa7.inc

# TQ u-boot and kernel
#
PREFERRED_PROVIDER_u-boot_ls102xa ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader_ls102xa ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/kernel_ls102xa ?= "linux-tq"

# Define the default image type
IMAGE_CLASSES ?= "image_types_uboot"
KERNEL_IMAGETYPE ?= "zImage"

UBOOT_ENTRYPOINT_ls102xa = "0x80008000"

# Machine specific options
#

MACHINE_FEATURES = "pci ext2 ext3 ext4 serial"

MACHINE_EXTRA_RRECOMMENDS = " kernel-modules kernel-devicetree udev-extraconf"
MACHINE_FEATURES_append = " alsa"

# Image Options
#
EXTRA_IMAGEDEPENDS += "u-boot"
IMAGE_FSTYPES ?= "ext4 wic"

do_image_wic[depends] += "\
	mtools-native:do_populate_sysroot \
	dosfstools-native:do_populate_sysroot \
	parted-native:do_populate_sysroot \
	"

