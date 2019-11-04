# Common definitions for TQ-Group TQMT104x modules on STKT104x

MACHINEOVERRIDES =. "tqmt104x:stkt104x:"

## TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

PREFERRED_PROVIDER_u-boot_tqmt104x ?= "u-boot-tqmt"
PREFERRED_PROVIDER_virtual/bootloader_tqmt104x ?= "u-boot-tqmt"

PREFERRED_PROVIDER_virtual/kernel_tqmt104x ?= "linux-tqmt"

PREFERRED_VERSION_openssl = ""

## TQ BSP default settings
#
SDCARD_ROOTFS ?= "${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.ext4"
NOR_ROOTFS ?= "${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.jffs2"

JFFS2_ERASEBLOCK ?= "0x20000"

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

WKS_FILE_DEPENDS = "\
	mtools-native \
	dosfstools-native \
	parted-native \
	virtual/kernel \
	virtual/bootloader \
	"

WKS_FILE ?= "tqmt104x-sdimg.wks"

# fman_ucode is hardcoded in tqmt104x-sdimg.wks
FSL_FMAN_UCODE ?= "fsl_fman_ucode_t1040_r1.1_107_4_2.bin"

# Image Options
#
EXTRA_IMAGEDEPENDS += "virtual/bootloader fm-ucode"
IMAGE_FSTYPES_append = " tar.gz ext4 wic jffs2"

KERNEL_IMAGETYPE = "uImage"
SERIAL_CONSOLES ?= "115200;ttyS0 115200;ttyS1 115200;ttyEHV0"
SERIAL_CONSOLES_CHECK ?= "${SERIAL_CONSOLES}"
MACHINEOVERRIDES =. "qoriq-ppc:qoriq:"

SOC_DEFAULT_IMAGE_FSTYPES = "tar.gz ext4"
IMAGE_FSTYPES ?= "${SOC_DEFAULT_IMAGE_FSTYPES}"

MACHINE_FEATURES ?= "pci ext4 serial"
MACHINE_EXTRA_RRECOMMENDS += "udev-extraconf kernel-modules"

# Sub-architecture support
MACHINE_SOCARCH_SUFFIX ?= ""
MACHINE_SOCARCH_SUFFIX_qoriq = "-qoriq"

MACHINE_ARCH_FILTER = "virtual/kernel"
