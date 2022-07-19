# Common definitions for TQ-Systems GmbH TQMLX2160A modules

require conf/machine/include/soc-family.inc

MACHINEOVERRIDES =. "fsl-lsch3:lx2160a:tqmlx2160a:"

# TQ-Systems U-Boot and kernel has to be set before qoriq-arm64.inc
# to allow weak settings
#
require conf/machine/include/tqmxx.inc

PREFERRED_PROVIDER_u-boot ?= "u-boot-lsdk-tq"
PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-lsdk-tq"
PREFERRED_PROVIDER_virtual/kernel ?= "linux-lsdk-tq"

# source the machine specific features for RCW, multimedia etc. from
# meta-freescale
require conf/machine/include/qoriq-arm64.inc

require conf/machine/include/arm/armv8a/tune-cortexa72.inc

# Define the default image type
KERNEL_IMAGETYPE ?= "Image"

# Machine specific options

MACHINE_EXTRA_RRECOMMENDS += "\
    kernel-devicetree \
    kernel-modules \
    udev-extraconf \
"

# provide info about machine's capabilities
MACHINE_FEATURES:append = "\
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
EXTRA_IMAGEDEPENDS += "\
    management-complex \
    mc-utils \
    qoriq-atf \
    rcw \
    virtual/bootloader \
"

# DPAA2 manament utility
IMAGE_INSTALL:append = " restool"

# Image Options
#
MKUBIFS_ARGS = " -m 1 -e 65408 -c 1280 "
UBINIZE_ARGS = " -p 65536 -m 1 -s 1 "

# needs to be set to have it consistent for kernel command line
UBI_VOLNAME = "root"

WICVARS:append = " BL2_IMAGE"

BL2_IMAGE ?= "bl2_sd.pbl"
