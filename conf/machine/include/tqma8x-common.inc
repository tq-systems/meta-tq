# Common definitions for all TQ-Group TQMa8x modules

MACHINEOVERRIDES =. "mx8:mx8qm:"

# Don't include kernels in standard images
RDEPENDS_${KERNEL_PACKAGE_NAME}-base = ""

LOADADDR = ""

# Machine specific options
#
MACHINE_FEATURES_append = " can pci alsa rtc serial ext2 ext3 vfat usbhost"

PREFERRED_PROVIDER_virtual/imx-cortexm-demos = "tqma8-cortexm-demos"

UBOOT_MAKE_TARGET ?= "all"
UBOOT_SUFFIX ?= "bin"

ATF_PLATFORM = "imx8qm"

# for sdcard image rule
BOOT_SPACE = "65536"
IMAGE_BOOTLOADER = "imx-boot"
# for sdcard + wic image rule
IMX_BOOT_SEEK = "32"
IMX_BOOT_SOC_TARGET = "iMX8QM"


# Image Options
#
EXTRA_IMAGEDEPENDS += "u-boot imx-boot imx-sc-firmware"

WKS_FILE_DEPENDS = "\
	mtools-native \
	dosfstools-native \
	parted-native \
	virtual/kernel \
	virtual/bootloader \
	imx-boot \
	imx-sc-firmware \
	"

# optionally source the machine specific features for multimedia etc. from
# meta-freescale

include conf/machine/include/imx-base.inc
require conf/machine/include/tune-cortexa72-cortexa53.inc

# need to do after imx-base, not included in firmware-imx-8.3
MACHINE_FIRMWARE_remove = "firmware-imx-brcm"
