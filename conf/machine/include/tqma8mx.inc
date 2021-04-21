# Common definitions for TQ-Group TQMa8MX modules

MACHINEOVERRIDES =. "imx:mx8:mx8m:mx8mq:tqma8mq:"

# Don't include kernels in standard images
RDEPENDS_${KERNEL_PACKAGE_NAME}-base = ""

LOADADDR = ""

# Machine specific options
#
MACHINE_EXTRA_RRECOMMENDS += " kernel-modules kernel-devicetree"
MACHINE_FEATURES_append = " pci alsa"

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

PREFERRED_PROVIDER_virtual/kernel_tqma8mq ?= "linux-imx-tq"
KERNEL_IMAGETYPE = "Image"

PREFERRED_PROVIDER_u-boot_tqma8mq ?= "u-boot-imx-tq"
PREFERRED_PROVIDER_virtual/bootloader_tqma8mq ?= "u-boot-imx-tq"
SPL_BINARY = "spl/u-boot-spl.bin"

# Set DDR FIRMWARE
DDR_FIRMWARE_NAME = "\
  lpddr4_pmu_train_1d_imem.bin \
  lpddr4_pmu_train_1d_dmem.bin \
  lpddr4_pmu_train_2d_imem.bin \
  lpddr4_pmu_train_2d_dmem.bin \
"

# Set imx-mkimage boot target
IMXBOOT_TARGETS ??= "flash_spl_uboot"

LOADADDR = ""
UBOOT_SUFFIX = "bin"
UBOOT_MAKE_TARGET = ""

# for sdcard image rule
BOOT_SPACE = "65536"
IMAGE_BOOTLOADER ?= "imx-boot"

# for sdcard + wic image rule
IMX_BOOT_SEEK = "33"

# TODO:
# optee rule filters for matching machine names and expects at least imx for MX8
#MACHINE_FEATURES_append = " optee"
#OPTEE_BIN_EXT = "8mq"
DISTRO_FEATURES_remove = " optee"
MACHINE_FEATURES_remove = " optee"
IMAGE_INSTALL_remove = " packagegroup-fsl-optee-imx"

# Image Options
#
EXTRA_IMAGEDEPENDS += "u-boot imx-boot cortexm-demos"

# need to have the kernel modules in rootfs
MACHINE_EXTRA_RRECOMMENDS_append = " kernel-modules"

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

WKS_FILE ?= "tqma8-sdimg.wks.in"

WKS_FILE_DEPENDS = "\
	mtools-native \
	dosfstools-native \
	parted-native \
	virtual/kernel \
	virtual/bootloader \
	imx-boot \
	"

# optionally source the machine specific features for multimedia etc. from
# meta-freescale

include conf/machine/include/imx-base.inc
require conf/machine/include/tune-cortexa53.inc
