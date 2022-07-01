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

PREFERRED_PROVIDER_virtual/kernel ??= "linux-ti-tq"
KERNEL_IMAGETYPE ?= "zImage"

PREFERRED_PROVIDER_u-boot_tqma335x ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader_tqma335x ?= "u-boot-tq"

UBOOT_ARCH = "arm"
UBOOT_ENTRYPOINT = "0x80008000"
UBOOT_LOADADDRESS = "0x80008000"
UBOOT_SUFFIX ?= "img"
SPL_BINARY = "MLO"

# we still need this, since meta-ti sets KERNEL_IMAGETYPES to contain uImage
# and forces building this ancient image which forces us to provide this var
KERNEL_EXTRA_ARGS_prepend = "LOADADDR=${UBOOT_ENTRYPOINT} "

# Image Options
#
EXTRA_IMAGEDEPENDS += "virtual/bootloader"

# note: These settings are only usable with larger SPI NOR
# ubinize PEB_SIZE="65536"
# ubinize MINIMUM_IO_UNIT_SIZE="1"
# ubinize SUB_PAGE_SIZE="1"
# mkubifs VID_HEADER_OFFSET="64"
# mkubifs LEB_SIZE="65408"

MKUBIFS_ARGS = " -m 1 -e 65408 -c 896 "
UBINIZE_ARGS = " -p 65536 -m 1 -s 1 "
# needs to be set to have it consistent for kernel command line
UBI_VOLNAME = "root"

IMAGE_BOOT_FILES = "\
    MLO \
    u-boot.img \
"

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

# List common SoC features

MACHINE_FEATURES_append = "\
    alsa \
    apm \
    can \
    dsp \
    ethernet \
    ext2 \
    gpu \
    kernel26 \
    mmip \
    screen \
    sgx \
    ubi \
    usbgadget \
    usbhost \
    vfat \
"
