# Common definitions for TQ-Group TQMa57xx modules

include conf/machine/include/dra7xx.inc

SOC_FAMILY_append = ":dra7xx"

MACHINEOVERRIDES =. "tqma57xx:"

MACHINE_FEATURES += "pci"

DEFAULTTUNE ?= "armv7athf-neon"
require conf/machine/include/tune-cortexa15.inc

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

UBOOT_ARCH = "arm"
UBOOT_ENTRYPOINT = "0x80008000"
UBOOT_LOADADDRESS = "0x80008000"
UBOOT_SUFFIX ?= "img"

# Image Options
#
KERNEL_IMAGETYPE = "zImage"

EXTRA_IMAGEDEPENDS += "virtual/bootloader"

IMAGE_INSTALL_append = " kernel kernel-modules"
IMAGE_INSTALL_append = " kernel-devicetree kernel-image-zimage"

IMAGE_BOOT_FILES = "\
	MLO \
	u-boot.img \
	${KERNEL_IMAGETYPE} \
"

IMAGE_FSTYPES ?= "tar.gz ext4 wic"

WKS_FILE_DEPENDS = "\
	mtools-native \
	dosfstools-native \
	parted-native \
	virtual/kernel \
	virtual/bootloader \
	"

# Use the expected value of the ubifs filesystem's volume name in the kernel
# and u-boot.
UBI_VOLNAME = "rootfs"

# List common SoC features
MACHINE_FEATURES = "kernel26 apm usbgadget usbhost vfat ext2 screen alsa ethernet sgx mmip dsp"
