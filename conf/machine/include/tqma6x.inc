# Common definitions for TQ-Group TQMa6 modules

# optionally source the machine specific features for multimedia etc. from
# meta-freescale

MACHINE_HAS_VIVANTE_KERNEL_DRIVER_SUPPORT = "1"
MACHINE_USES_VIVANTE_KERNEL_DRIVER_MODULE = "0"

MACHINEOVERRIDES =. "tqma6x:"
MACHINEOVERRIDES =. "mx6:"

PREFERRED_VERSION_weston_mx6_imx ?= "8.0.0.imx"
PREFERRED_VERSION_wayland-protocols_mx6 ?= "1.18.imx"
PREFERRED_VERSION_libdrm_mx6 ?= "2.4.99.imx"

include conf/machine/include/imx-base.inc

DEFAULTTUNE ?= "cortexa9thf-neon"
require conf/machine/include/tune-cortexa9.inc

# Machine specific options
#
MACHINE_EXTRA_RRECOMMENDS += "kernel-modules kernel-devicetree"
MACHINE_EXTRA_RRECOMMENDS += " linux-firmware-imx-sdma-imx6q"
MACHINE_FEATURES_append = " alsa ext2 pci rtc serial usbhost vfat can"

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

PREFERRED_PROVIDER_virtual/kernel_tqma6x ?= "linux-imx-tq"
PREFERRED_PROVIDER_virtual/kernel_tqma6x_use-mainline-bsp ?= "linux-tq"
KERNEL_IMAGETYPE = "zImage"

PREFERRED_PROVIDER_u-boot_tqma6x ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader_tqma6x ?= "u-boot-tq"

UBOOT_SUFFIX ?= "imx"
UBOOT_MAKE_TARGET ?= "u-boot.imx"

# Image Options
#
EXTRA_IMAGEDEPENDS += "virtual/bootloader"

IMAGE_FSTYPES_append = " tar.gz ext4 wic"
IMAGE_FSTYPES_remove = " tar.bz2 sdcard.bz2 wic.gz"

# need to have the kernel modules in rootfs
IMAGE_INSTALL_append = " kernel-modules"

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

WKS_FILE_DEPENDS = "\
	mtools-native \
	dosfstools-native \
	parted-native \
	virtual/kernel \
	virtual/bootloader \
	"
