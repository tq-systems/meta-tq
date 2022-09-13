# Common definitions for TQ-Systems GmbH TQMLS102xa modules

require conf/machine/include/soc-family.inc

# TQ-Systems U-Boot and kernel has to be set before qoriq-arm.inc
# to allow weak settings
#
require conf/machine/include/tqmxx.inc

PREFERRED_PROVIDER_u-boot ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/kernel ?= "linux-tq"


# optionally source the machine specific features for multimedia etc. from
# meta-freescale
include conf/machine/include/qoriq-arm.inc

MACHINEOVERRIDES =. "ls102xa:tqmls102xa:"

DEFAULTTUNE ?= "cortexa7thf-neon"
require conf/machine/include/arm/armv7a/tune-cortexa7.inc

# Define the default image type, we cannot use default assignment due to the
# default assignment in qoriq-arm.inc (meta-freescale).
KERNEL_IMAGETYPE = "zImage"

UBOOT_ENTRYPOINT:ls102xa = "0x80008000"

# Machine specific options
#

MACHINE_EXTRA_RRECOMMENDS += "\
    kernel-devicetree \
    kernel-modules \
    udev-extraconf \
"

# provide info about machine's capabilities
MACHINE_FEATURES:append = "\
    alsa \
    can \
    ext2 \
    pci \
    rtc \
    serial \
    ubi \
    usbhost \
    vfat \
"

# Image Options
#
EXTRA_IMAGEDEPENDS += "virtual/bootloader"

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

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"