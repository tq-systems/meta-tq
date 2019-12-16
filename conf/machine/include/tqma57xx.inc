# Common definitions for TQ-Group TQMa57xx modules
# optional from meta-ti
# will handle SOC_FAMILY_append ?= ":dra7xx" needed for TI recipes
include conf/machine/include/dra7xx.inc

# needed for easier compatible / preferred_provider
MACHINEOVERRIDES =. "tqma57xx:"

MACHINE_FEATURES += "pci"

# prevent inclusion if meta-ti is present - otherwise we get nasty double inclusion warnings
DEFAULTTUNE ?= "${@bb.utils.contains("BBFILE_COLLECTIONS", "meta-ti", "", "armv7athf-neon", d)}"
require ${@bb.utils.contains("BBFILE_COLLECTIONS", "meta-ti", '', 'conf/machine/include/tune-cortexa15.inc', d)}

# Machine specific options
#
MACHINE_EXTRA_RRECOMMENDS += "kernel-modules kernel-devicetree"

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

PREFERRED_PROVIDER_virtual/kernel_tqma57xx ?= "linux-tq"
KERNEL_IMAGETYPE ?= "zImage"

PREFERRED_PROVIDER_u-boot_tqma57xx ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader_tqma57xx ?= "u-boot-tq"

UBOOT_ARCH = "arm"
UBOOT_ENTRYPOINT = "0x80008000"
UBOOT_LOADADDRESS = "0x80008000"
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

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

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
