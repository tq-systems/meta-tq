# Common definitions for TQ-Systems GmbH TQMa64xxL modules

# needed for easier compatible / preferred_provider
MACHINEOVERRIDES =. "tqma64xxl:"

# Default providers, may need to override for specific machines
# needs to be set before inclusion of am64xx.inc
PREFERRED_PROVIDER_virtual/kernel ?= "linux-ti-tq"
PREFERRED_PROVIDER_u-boot ?= "u-boot-ti-tq"
PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-ti-tq"

# From meta-ti-bsp: PREFERRED_PROVIDER and tuning
require conf/machine/include/am64xx.inc

# Machine specific options
#
MACHINE_EXTRA_RRECOMMENDS += "\
    kernel-devicetree \
    kernel-modules \
"

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc
KERNEL_IMAGETYPE = "Image"

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
MACHINE_FEATURES += "\
    apm \
    can \
    ethernet \
    ext2 \
    kernel26 \
    pci \
    ubi \
    usbgadget \
    usbhost \
    vfat \
"
