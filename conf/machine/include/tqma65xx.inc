# Common definitions for TQ-Group TQMa65xx modules

# From meta-ti
require conf/machine/include/am65xx.inc

# needed for easier compatible / preferred_provider
MACHINEOVERRIDES =. "tqma65xx:"

# Machine specific options
#
MACHINE_EXTRA_RRECOMMENDS += "kernel-modules kernel-devicetree"

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

PREFERRED_PROVIDER_virtual/kernel_tqma65xx ?= "linux-ti-tq"
KERNEL_IMAGETYPES = "Image"

PREFERRED_PROVIDER_u-boot_tqma65xx ?= "u-boot-ti-tq"
PREFERRED_PROVIDER_virtual/bootloader_tqma65xx ?= "u-boot-ti-tq"

UBOOT_ARCH = "arm"
UBOOT_ENTRYPOINT = "0x80008000"
UBOOT_LOADADDRESS = "0x80008000"
UBOOT_SUFFIX = "img"

# we still need this, since meta-ti sets KERNEL_IMAGETYPES to contain uImage
# and forces building this ancient image which forces us to provide this var
KERNEL_EXTRA_ARGS_prepend = "LOADADDR=${UBOOT_ENTRYPOINT} "

# Image Options
#
EXTRA_IMAGEDEPENDS += "virtual/bootloader"

IMAGE_BOOT_FILES = "\
    sysfw.itb \
    tiboot3.bin \
    tispl.bin \
    u-boot.img \
"

# Verified values to generate UBI image:
# UBIFS_PEB_SIZE="65536"
# UBIFS_MINIMUM_IO_UNIT_SIZE="1"
# UBIFS_SUB_PAGE_SIZE="1"
# UBIFS_VID_HEADER_OFFSET="64"
# UBIFS_LEB_SIZE="65408"

MKUBIFS_ARGS = " -m 1 -e 65408 -c 896 "
UBINIZE_ARGS = " -p 65536 -m 1 -s 1 "
# needs to be set to have it consistent for kernel command line
# Use the expected value of the ubifs filesystem's volume name in the kernel
# and u-boot.
UBI_VOLNAME = "rootfs"

# List common SoC features
MACHINE_FEATURES = "kernel26 apm usbgadget usbhost vfat ext2 screen alsa ethernet sgx mmip dsp pci ubi can"