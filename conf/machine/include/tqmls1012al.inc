# Common definitions for TQ-Group TQMLS1012AL modules

MACHINEOVERRIDES =. "tqmls1012al:"
MACHINEOVERRIDES =. "fsl-lsch2:ls1012a:"

DEFAULTTUNE ?= "aarch64"

# optionally source the machine specific features for multimedia etc. from
# meta-freescale
include conf/machine/include/qoriq-arm64.inc
require conf/machine/include/arm/arch-arm64.inc

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

PREFERRED_PROVIDER_u-boot_tqmls1012al ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader_tqmls1012al ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/kernel_tqmls1012al ?= "linux-tq"

# Define the default image type
KERNEL_IMAGETYPE ?= "Image"
KERNEL_CLASSES  = " kernel-itbimage "
KERNEL_IMAGETYPES = "itbImage"

CORE_IMAGE_EXTRA_INSTALL_append_qoriq = " ppfe-firmware"

UBOOT_ENTRYPOINT = "0x80008000"

DTB_LOAD = "0x81600000"

# Machine specific options
#

MACHINE_EXTRA_RRECOMMENDS = " kernel-modules kernel-devicetree udev-extraconf"

# provide info about machine's capabilities
MACHINE_FEATURES_append = " ext2 pci rtc serial usbhost vfat"

# Image Options
#
EXTRA_IMAGEDEPENDS += "u-boot ppa ppfe-firmware"
IMAGE_FSTYPES_append = " ubi tar.gz ext4 wic "
IMAGE_FSTYPES_remove = " jffs2 ext2.gz ext2.gz.u-boot "

DISTRO_FEATURES_remove = " wayland weston x11 "
MACHINE_FEATURES_append = " usbhost pci ext2 serial vfat "
DISTRO_FEATURES_BACKFILL_CONSIDERED = " pulseaudio bluez5 gobject-introspection-data "
DISTRO_FEATURES_remove = " alsa bluetooth irda pcmcia usbgadget pulseaudio "

# needed for Rootfs on QSPI
IMAGE_INSTALL_append = " mtd-utils-jffs2 mtd-utils-ubifs mtd-utils-misc"
# need to have the kernel modules in rootfs
IMAGE_INSTALL_append = " kernel-modules"

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

WKS_FILE_DEPENDS = "\
        mtools-native \
        dosfstools-native \
        parted-native \
        virtual/kernel \
        "