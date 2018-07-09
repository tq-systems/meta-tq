# Common definitions for TQ-Group TQMLS1012AL modules

require conf/machine/include/soc-family.inc

MACHINEOVERRIDES =. "fsl-lsch2:ls1012a:"

DEFAULTTUNE ?= "aarch64"

require conf/machine/include/arm/arch-arm64.inc

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

PREFERRED_PROVIDER_u-boot_ls1012a ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/kernel_ls1012a ?= "linux-tq"

# Define the default image type
KERNEL_IMAGETYPE ?= "Image"
KERNEL_CLASSES  = " kernel-itbimage "
KERNEL_IMAGETYPES = "itbImage"

UBOOT_ENTRYPOINT = "0x80008000"

DTB_LOAD = "0x81600000"

# Machine specific options
#

MACHINE_EXTRA_RRECOMMENDS = " kernel-modules kernel-devicetree udev-extraconf"

# provide info about machine's capabilities
MACHINE_FEATURES_append = " ext2 pci rtc serial usbhost vfat"

# Image Options
#
EXTRA_IMAGEDEPENDS += "u-boot"
IMAGE_FSTYPES ?= "tar.gz ext4 ubi"

DISTRO_FEATURES_remove += "${@bb.utils.contains('IMAGE_FSTYPES', 'ubi', 'wayland weston x11', '', d)}"

# needed for Rootfs on QSPI
IMAGE_INSTALL_append = " mtd-utils-jffs2 mtd-utils-ubifs mtd-utils-misc"

# from PTXdist BSP:
# PTXCONF_IMAGE_ROOT_UBIFS_PEB_SIZE="65536"
# PTXCONF_IMAGE_ROOT_UBIFS_MINIMUM_IO_UNIT_SIZE="1"
# PTXCONF_IMAGE_ROOT_UBIFS_SUB_PAGE_SIZE="1"
# PTXCONF_IMAGE_ROOT_UBIFS_VID_HEADER_OFFSET="64"
# PTXCONF_IMAGE_ROOT_UBIFS_LEB_SIZE="65408"

MKUBIFS_ARGS = " -m 1 -e 65408 -c 896 "
UBINIZE_ARGS = " -p 65536 -m 1 -s 1 "
# needs to be set to have it consistent for kernel command line
UBI_VOLNAME = "root"

# optionally source the machine specific features for multimedia etc. from
# meta-freescale
require conf/machine/include/qoriq-arm64.inc
