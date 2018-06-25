# Common definitions for TQ-Group TQMa8QX A0 modules

require conf/machine/include/arm/arch-arm64.inc

MACHINEOVERRIDES =. "mx8:mx8qxp:mx8qxpa0:"

# Don't include kernels in standard images
RDEPENDS_kernel-base = ""

LOADADDR = ""

# Machine specific options
#
MACHINE_EXTRA_RRECOMMENDS = " kernel-modules kernel-devicetree"
MACHINE_FEATURES_append = " pci alsa"

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

PREFERRED_PROVIDER_virtual/kernel_mx8 ?= "linux-tq"
KERNEL_IMAGETYPE = "Image"

PREFERRED_PROVIDER_u-boot_mx8 ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader_mx8 ?= "u-boot-tq"

UBOOT_MAKE_TARGET = "u-boot.bin"
UBOOT_SUFFIX = "bin"

# for sdcard image rule
BOOT_SPACE = "65536"
IMAGE_BOOTLOADER = "imx-boot"
# for sdcard image rule
IMX_BOOT_SEEK = "33"

# Image Options
#
EXTRA_IMAGEDEPENDS += "u-boot imx-boot imx-sc-firmware"
IMAGE_FSTYPES_append = " tar.gz ext4 wic"

# need to have the kernel modules in rootfs
IMAGE_INSTALL_append = " kernel-modules"

do_image_wic[depends] += " \
	mtools-native:do_populate_sysroot \
	dosfstools-native:do_populate_sysroot \
	parted-native:do_populate_sysroot \
	virtual/kernel:do_deploy \
	imx-boot:do_deploy \
	imx-sc-firmware:do_deploy \
	"

WKS_FILE ?= "tqma8x-sdimg.wks"

# optionally source the machine specific features for multimedia etc. from
# meta-freescale

include conf/machine/include/imx-base.inc
