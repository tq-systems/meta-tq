# Common definitions for TQ-Group TQMa64xx modules

# From meta-ti
require conf/machine/include/am64xx.inc

# needed for easier compatible / preferred_provider
MACHINEOVERRIDES =. "tqma64xxl:"

# Machine specific options
#
MACHINE_EXTRA_RRECOMMENDS += "kernel-modules kernel-devicetree"

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

PREFERRED_PROVIDER_virtual/kernel_tqma64xxl ?= "linux-ti-tq"
KERNEL_IMAGETYPES = "Image"

PREFERRED_PROVIDER_u-boot_tqma64xxl ?= "u-boot-ti-tq"
PREFERRED_PROVIDER_virtual/bootloader_tqma64xxl ?= "u-boot-ti-tq"

# Image Options
#
EXTRA_IMAGEDEPENDS += "virtual/bootloader"

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
MACHINE_FEATURES = "kernel26 apm usbgadget usbhost vfat ext2 ethernet pci ubi can"
