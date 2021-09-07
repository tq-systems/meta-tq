# Common definitions for TQ-Group TQMLX2160A modules

require conf/machine/include/soc-family.inc

MACHINEOVERRIDES =. "tqmlx2160a:lx2160a:fsl-lsch3:"

require conf/machine/include/tune-cortexa72.inc

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

PREFERRED_PROVIDER_u-boot_tqmlx2160a ?= "u-boot-lsdk-tq"
PREFERRED_PROVIDER_virtual/bootloader_tqmlx2160a ?= "u-boot-lsdk-tq"
PREFERRED_PROVIDER_virtual/kernel_tqmlx2160a ?= "linux-lsdk-tq"
PREFERRED_VERSION_qoriq-atf = "1.5%"
# Define the default image type
KERNEL_IMAGETYPE ?= "Image"

# Machine specific options

MACHINE_EXTRA_RRECOMMENDS += "kernel-modules kernel-devicetree udev-extraconf"

# provide info about machine's capabilities
MACHINE_FEATURES_append = "  ext2 pci rtc serial usbhost vfat can"

# Image Options
#
EXTRA_IMAGEDEPENDS += "virtual/bootloader"
EXTRA_IMAGEDEPENDS += "rcw qoriq-atf management-complex mc-utils"

# DPAA2 manament utility
IMAGE_INSTALL_append = " restool"

IMAGE_FSTYPES_append = " tar.gz ext4 wic ubi"
IMAGE_FSTYPES_remove = " tar.bz2 sdcard.bz2"

DISTRO_FEATURES_remove += "${@bb.utils.contains('IMAGE_FSTYPES', 'ubi', 'wayland weston x11', '', d)}"

# needed for Rootfs on QSPI
IMAGE_INSTALL_append = " mtd-utils-jffs2 mtd-utils-ubifs mtd-utils-misc"
IMAGE_INSTALL_append = " kernel-modules"

# Image Options
#
MKUBIFS_ARGS = " -m 1 -e 65408 -c 1280 "
UBINIZE_ARGS = " -p 65536 -m 1 -s 1 "

# needs to be set to have it consistent for kernel command line
UBI_VOLNAME = "root"

WICVARS_append = " BL2_IMAGE"

BL2_IMAGE ?= "bl2_sd.pbl"

WKS_FILE_${MACHINE} ?=  "tqmlx2160a.wks"

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

# source the machine specific features for RCW, multimedia etc. from
# meta-freescale
require conf/machine/include/qoriq-arm64.inc
