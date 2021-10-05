# Common definitions for TQ-Group TQMa8MPxL modules

MACHINEOVERRIDES =. "imx:mx8:mx8m:mx8mp:tqma8mpxl:"

# Don't include kernels in standard images
RDEPENDS_${KERNEL_PACKAGE_NAME}-base = ""

LOADADDR = ""

# Machine specific options
#
MACHINE_FEATURES_append = " pci alsa usbhost can rtc serial ext2 ext3 vfat"

# Set DDR FIRMWARE
DDR_FIRMWARE_NAME = " \
  lpddr4_pmu_train_1d_dmem_202006.bin \
  lpddr4_pmu_train_1d_imem_202006.bin \
  lpddr4_pmu_train_2d_dmem_202006.bin \
  lpddr4_pmu_train_2d_imem_202006.bin \
"

LOADADDR = ""
UBOOT_SUFFIX = "bin"
UBOOT_MAKE_TARGET = ""

# for sdcard image rule
BOOT_SPACE = "65536"
IMAGE_BOOTLOADER = "imx-boot"

# for sdcard + wic image rule
IMX_BOOT_SEEK = "32"
IMX_BOOT_SOC_TARGET = "iMX8MP"

ATF_PLATFORM = "imx8mp"

# TODO:
# optee rule filters for matching machine names and expects at least imx for MX8
#MACHINE_FEATURES_append = " optee"
#OPTEE_BIN_EXT = "8mn"
DISTRO_FEATURES_remove = " optee"
MACHINE_FEATURES_remove = " optee"
IMAGE_INSTALL_remove = " packagegroup-fsl-optee-imx"

# Image Options
#
EXTRA_IMAGEDEPENDS += "u-boot imx-boot"

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
