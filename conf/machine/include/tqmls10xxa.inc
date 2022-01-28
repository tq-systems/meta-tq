# Common definitions for TQ-Systems GmbH TQMLS10xxA modules

require conf/machine/include/arm/arch-arm64.inc

# source the machine specific features for RCW, MC, multimedia etc. from
# meta-freescale
require conf/machine/include/qoriq-arm64.inc

# Define the default image type
KERNEL_IMAGETYPE ?= "Image"

PREFERRED_PROVIDER_u-boot_tqmls10xxa ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader_tqmls10xxa ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/kernel ?= "linux-tq"

# Machine specific options
#
MACHINE_EXTRA_RRECOMMENDS += "kernel-modules kernel-devicetree udev-extraconf"

# provide info about machine's capabilities
MACHINE_FEATURES_append = " ext2 pci rtc serial usbhost vfat ubi"

# Image Options
#
EXTRA_IMAGEDEPENDS += "virtual/bootloader"
EXTRA_IMAGEDEPENDS_append_fsl-lsch2 += "fm-ucode"
EXTRA_IMAGEDEPENDS_append_fsl-lsch3 += "rcw mc-utils management-complex"


# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

# Different RCW files for booting CPU from SD card and eMMC require
# setting the correct BOOTMODE:
# sd -> to boot from SD-Card
# emmc -> to boot from eMMC
BOOTMODE ?= "sd"
RCWIMG ?= "tqmls1088a-mbls10xxa/${@d.getVar('RCW' + d.getVar('BOOTMODE').upper())}"

WICVARS_append = " RCWIMG MC_DPL MC_DPC"

WKS_FILE_DEPENDS = "\
    mtools-native \
    dosfstools-native \
    parted-native \
    virtual/kernel \
    virtual/bootloader \
"
