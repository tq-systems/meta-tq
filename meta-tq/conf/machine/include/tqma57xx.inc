# Common definitions for TQ-Systems GmbH TQMa57xx modules

# needed for easier compatible / preferred_provider
MACHINEOVERRIDES =. "tqma57xx:"

# Default providers, may need to override for specific machines
# needs to be set before inclusion of am57xx.inc
PREFERRED_PROVIDER_virtual/kernel ?= "linux-ti-tq"
PREFERRED_VERSION_linux-ti-tq ?= "5.10%"
TI_SGX_DDK_KM_KERNVER ?= "5.10"

PREFERRED_PROVIDER_u-boot ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-tq"

# from meta-ti-bsp: will handle SOC_FAMILY:append = ":am57xx" needed for TI recipes
include conf/machine/include/am57xx.inc

# prevent inclusion of tune settings if meta-ti is present -
# otherwise we get nasty double inclusion warnings
DEFAULTTUNE ?= "${@bb.utils.contains("BBFILE_COLLECTIONS", "meta-ti-bsp", "", "armv7athf-neon", d)}"
require ${@bb.utils.contains("BBFILE_COLLECTIONS", "meta-ti-bsp", '', 'conf/machine/include/arm/armv7a/tune-cortexa15.inc', d)}

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

# needs to be set to have it consistent for kernel command line
# Use the expected value of the ubifs filesystem's volume name in the kernel
# and u-boot.
UBI_VOLNAME = "rootfs"

IMAGE_BOOT_FILES = "\
    MLO \
    u-boot.img \
"

# List common SoC features
MACHINE_FEATURES += "\
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
    usbgadget \
    usbhost \
    vfat \
"

# We have no sound support for now.
# Remove the alsa feature, which is added by default in meta-ti
MACHINE_FEATURES:remove = "alsa"
