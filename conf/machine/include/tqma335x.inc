# Common definitions for TQ-Systems GmbH TQMa335x modules
# optional from meta-ti
# will handle SOC_FAMILY_append ?= ":ti33x" needed for TI recipes
include conf/machine/include/ti33x.inc

# needed for easier compatible / preferred_provider
MACHINEOVERRIDES =. "tqma335x:"

# prevent inclusion if meta-ti is present - otherwise we get nasty double inclusion warnings
DEFAULTTUNE ?= "${@bb.utils.contains("BBFILE_COLLECTIONS", "meta-ti", "", "armv7athf-neon", d)}"
require ${@bb.utils.contains("BBFILE_COLLECTIONS", "meta-ti", '', 'conf/machine/include/tune-cortexa8.inc', d)}

# Machine specific options
#
MACHINE_EXTRA_RRECOMMENDS += "kernel-modules kernel-devicetree"

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

PREFERRED_PROVIDER_virtual/kernel_tqma335x ?= "linux-ti-tq"
KERNEL_IMAGETYPE ?= "zImage"

PREFERRED_PROVIDER_u-boot_tqma335x ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader_tqma335x ?= "u-boot-tq"

UBOOT_ARCH = "arm"
UBOOT_ENTRYPOINT = "0x80008000"
UBOOT_LOADADDRESS = "0x80008000"
UBOOT_SUFFIX ?= "img"

# we still need this, since meta-ti sets KERNEL_IMAGETYPES to contain uImage
# and forces building this ancient image which forces us to provide this var
KERNEL_EXTRA_ARGS_prepend = "LOADADDR=${UBOOT_ENTRYPOINT} "

# Image Options
#
EXTRA_IMAGEDEPENDS += "virtual/bootloader"

IMAGE_BOOT_FILES = "\
    MLO \
    u-boot-dtb.img;u-boot.img \
"

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

WKS_FILE_DEPENDS = "\
    mtools-native \
    dosfstools-native \
    parted-native \
    virtual/kernel \
    virtual/bootloader \
    "

# Use the expected value of the ubifs filesystem's volume name in the kernel
# and u-boot.
UBI_VOLNAME = "rootfs"

# List common SoC features

MACHINE_FEATURES_append = " kernel26 apm usbgadget usbhost vfat ext2 screen alsa ethernet sgx gpu mmip dsp can"
