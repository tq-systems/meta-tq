# Common definitions for TQ-Group TQMa8MxNL modules

MACHINEOVERRIDES =. "imx:mx8:mx8m:mx8mn:tqma8mxnl:"

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

PREFERRED_PROVIDER_virtual/kernel_tqma8mxnl ?= "linux-imx-tq"
KERNEL_IMAGETYPE = "Image"

PREFERRED_PROVIDER_u-boot_tqma8mxnl ?= "u-boot-imx-tq"
PREFERRED_PROVIDER_virtual/bootloader_tqma8mxnl ?= "u-boot-imx-tq"
PREFERRED_PROVIDER_u-boot-mfgtool = "u-boot-imx-tq-mfgtool"
SPL_BINARY = "spl/u-boot-spl.bin"

PREFERRED_VERSION_u-boot-imx-tq_${MACHINE} = "2019.04"
PREFERRED_VERSION_imx-atf_${MACHINE} = "2.0"

# Set DDR FIRMWARE
DDR_FIRMWARE_NAME = "lpddr4_pmu_train_1d_imem.bin lpddr4_pmu_train_1d_dmem.bin lpddr4_pmu_train_2d_imem.bin lpddr4_pmu_train_2d_dmem.bin"

# Set imx-mkimage boot target
IMXBOOT_TARGETS ??= "flash_spl_uboot"

LOADADDR = ""
UBOOT_SUFFIX = "bin"
UBOOT_MAKE_TARGET = ""

# for sdcard image rule
BOOT_SPACE = "65536"
IMAGE_BOOTLOADER = "imx-boot"

# for sdcard + wic image rule
IMX_BOOT_SEEK = "32"

# TODO:
# optee rule filters for matching machine names and expects at least imx for MX8
#MACHINE_FEATURES_append = " optee"
#OPTEE_BIN_EXT = "8mn"
DISTRO_FEATURES_remove = " optee"
MACHINE_FEATURES_remove = " optee"
IMAGE_INSTALL_remove = " packagegroup-fsl-optee-imx"

# Image Options
#
EXTRA_IMAGEDEPENDS += "u-boot imx-boot cortexm-demos"

# need to have the kernel modules in rootfs
IMAGE_INSTALL_append = " kernel-modules"

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
