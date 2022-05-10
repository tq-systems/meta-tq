# Common definitions for TQ-Systems GmbH TQMLS10xxA modules

require conf/machine/include/arm/arch-arm64.inc

# source the machine specific features for RCW, MC, multimedia etc. from
# meta-freescale
require conf/machine/include/qoriq-arm64.inc

# Define the default image type
KERNEL_IMAGETYPE ?= "Image"

PREFERRED_PROVIDER_u-boot:tqmls10xxa ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader:tqmls10xxa ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/kernel ??= "linux-tq"

# Machine specific options
#
MACHINE_EXTRA_RRECOMMENDS += "\
    kernel-devicetree \
    kernel-modules \
    udev-extraconf \
"

# provide info about machine's capabilities
MACHINE_FEATURES:append = "\
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
EXTRA_IMAGEDEPENDS:append:fsl-lsch2 = " fm-ucode"
EXTRA_IMAGEDEPENDS:append:fsl-lsch3 = "\
    management-complex \
    mc-utils \
    rcw \
"

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

# Different RCW files for booting CPU from SD card and eMMC require
# setting the correct BOOTMODE:
# sd -> to boot from SD-Card
# emmc -> to boot from eMMC
BOOTMODE ?= "sd"
