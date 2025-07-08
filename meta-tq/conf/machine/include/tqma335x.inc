# Common definitions for TQ-Systems GmbH TQMa335x modules

# needed for easier compatible / preferred_provider
MACHINEOVERRIDES =. "tqma335x:"

# Default providers, may need to override for specific machines
# needs to be set before inclusion of ti33x.inc
PREFERRED_PROVIDER_virtual/kernel ?= "linux-tq"
PREFERRED_PROVIDER_u-boot ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-tq"

# from meta-ti-bsp: will handle SOC_FAMILY:append ?= ":ti33x" needed for TI recipes
include conf/machine/include/ti33x.inc

# prevent inclusion of tune settings if meta-ti is present -
# otherwise we get nasty double inclusion warnings
DEFAULTTUNE ?= "${@bb.utils.contains("BBFILE_COLLECTIONS", "meta-ti-bsp", "", "armv7athf-neon", d)}"
require ${@bb.utils.contains("BBFILE_COLLECTIONS", "meta-ti-bsp", '', 'conf/machine/include/arm/armv7a/tune-cortexa8.inc', d)}

# Machine specific options
#

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

IMAGE_BOOT_FILES = "\
    MLO \
    u-boot.img \
"

WIC_BOOTPART_SIZE = "4M"
WKS_FILE = "tqmaxx-ti-sdimg.wks.in"

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

# TI image configuration
require conf/machine/include/tq-image-ti.inc
