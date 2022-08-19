# Common definitions for TQ-Systems GmbH TQMa65xx modules

# needed for easier compatible / preferred_provider
MACHINEOVERRIDES =. "tqma65xx:"

# Default providers, may need to override for specific machines
# needs to be set before inclusion of am65xx.inc
PREFERRED_PROVIDER_virtual/kernel ?= "linux-ti-tq"
PREFERRED_PROVIDER_u-boot ?= "u-boot-ti-tq"
PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-ti-tq"

# From meta-ti-bsp: PREFERRED_PROVIDER and tuning
require conf/machine/include/am65xx.inc

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
# TI enforces also fitimage and vmlinux.gz
# this will enlarge our rootfs
KERNEL_IMAGETYPES = "Image"

UBOOT_ARCH = "arm"
UBOOT_ENTRYPOINT = "0x80008000"
UBOOT_LOADADDRESS = "0x80008000"
SPL_BINARY = "tispl.bin"
UBOOT_SUFFIX = "img"

# we still need this, since meta-ti sets KERNEL_IMAGETYPES to contain uImage
# and forces building this ancient image which forces us to provide this var
KERNEL_EXTRA_ARGS:prepend = "LOADADDR=${UBOOT_ENTRYPOINT} "

# Image Options
#
EXTRA_IMAGEDEPENDS += "virtual/bootloader"

#
# dropped in dunfell branch of meta-ti after the kirkstone fork
# see https://git.yoctoproject.org/meta-ti
# commit 1eb6e71f "wic: revert grub-efi patch"
#
MACHINE_FEATURES:remove = "efi"
EFI_PROVIDER = ""

# Verified values to generate UBI image:
# physical erase block size
UBI_PEB_SIZE = "65536"
UBI_MINIMUM_IO_UNIT_SIZE = "1"
UBI_SUB_PAGE_SIZE = "1"
# UBI_VID_HEADER_OFFSET="64"
# logical erase block size
UBI_LEB_SIZE = "65408"

# we have 64 MB flash, 8 MB for bootloader and env should be enough
# so use 1024 - 128 LEB
UBI_MAX_LEB_COUNT = "896"

MKUBIFS_ARGS = "\
    --min-io-size=${UBI_MINIMUM_IO_UNIT_SIZE} \
    --leb-size=${UBI_LEB_SIZE} \
    --max-leb-cnt=${UBI_MAX_LEB_COUNT} \
"

UBINIZE_ARGS = "\
    --peb-size=${UBI_PEB_SIZE} \
    --min-io-size=${UBI_MINIMUM_IO_UNIT_SIZE} \
    --sub-page-size=${UBI_SUB_PAGE_SIZE} \
"

# needs to be set to have it consistent for kernel command line
# Use the expected value of the ubifs filesystem's volume name in the kernel
# and u-boot.
UBI_VOLNAME = "rootfs"

# List common SoC features
MACHINE_FEATURES += "\
    alsa \
    apm \
    can \
    dsp \
    ethernet \
    ext2 \
    gpu \
    kernel26 \
    mmip \
    pci \
    screen \
    sgx \
    ubi \
    usbgadget \
    usbhost \
    vfat \
"
