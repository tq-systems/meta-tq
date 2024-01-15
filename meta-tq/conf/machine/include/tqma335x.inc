# Common definitions for TQ-Systems GmbH TQMa335x modules

# needed for easier compatible / preferred_provider
MACHINEOVERRIDES =. "tqma335x:"

# Default providers, may need to override for specific machines
# needs to be set before inclusion of ti33x.inc
PREFERRED_PROVIDER_virtual/kernel ?= "linux-ti-tq"
PREFERRED_PROVIDER_u-boot ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-tq"
TI_SGX_DDK_KM_KERNVER ?= "5.4"

# from meta-ti-bsp: will handle SOC_FAMILY:append ?= ":ti33x" needed for TI recipes
include conf/machine/include/ti33x.inc

# prevent inclusion of tune settings if meta-ti is present -
# otherwise we get nasty double inclusion warnings
DEFAULTTUNE ?= "${@bb.utils.contains("BBFILE_COLLECTIONS", "meta-ti-bsp", "", "armv7athf-neon", d)}"
require ${@bb.utils.contains("BBFILE_COLLECTIONS", "meta-ti-bsp", '', 'conf/machine/include/arm/armv7a/tune-cortexa8.inc', d)}

# Machine specific options
#
MACHINE_EXTRA_RRECOMMENDS += "\
    kernel-devicetree \
    kernel-modules \
"

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

KERNEL_IMAGETYPE ?= "zImage"

UBOOT_ARCH = "arm"
UBOOT_ENTRYPOINT = "0x80008000"
UBOOT_LOADADDRESS = "0x80008000"
SPL_BINARY = "MLO"
UBOOT_SUFFIX = "img"

# we still need this, since meta-ti sets KERNEL_IMAGETYPES to contain uImage
# and forces building this ancient image which forces us to provide this var
KERNEL_EXTRA_ARGS:prepend = "LOADADDR=${UBOOT_ENTRYPOINT} "

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
# Use the expected value of the ubifs filesystem's volume name in the kernel
# and u-boot.
UBI_VOLNAME = "root"

IMAGE_BOOT_FILES = "\
    MLO \
    u-boot.img \
"

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

# List common SoC features
MACHINE_FEATURES += "\
    alsa \
    apm \
    can \
    dsp \
    ext2 \
    gpu \
    mmip \
    screen \
    ubi \
    usbgadget \
    usbhost \
    vfat \
"

# Common image configuration
require conf/machine/include/tq-image.inc
