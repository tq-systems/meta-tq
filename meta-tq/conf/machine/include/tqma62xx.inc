# Common definitions for TQ-Systems GmbH TQMa64xxL modules

# needed for easier compatible / preferred_provider
MACHINEOVERRIDES =. "tqma62xx:"

# Default providers, may need to override for specific machines
# needs to be set before inclusion of am62xx.inc
PREFERRED_PROVIDER_virtual/kernel ?= "linux-ti-tq"
PREFERRED_PROVIDER_u-boot ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-tq"

TQ_FW_ENV_CONFIGS = "mmc0 mmc1 spi"

# From meta-ti-bsp: PREFERRED_PROVIDER and tuning
require conf/machine/include/am62xx.inc

# Available variants: am62x-gp am62x-hs-fs am62x-hs
SYSFW_DEFAULT_VARIANT ?= "am62x-hs-fs"

KERNEL_CLASSES += "ti-secdev"

# All 3 tiboot3 variants are installed to the boot partition
# The default variant is additionally installed as tiboot3.bin
IMAGE_BOOT_FILES = " \
    u-boot.${UBOOT_SUFFIX} \
    tiboot3-am62x-gp-tqma62xx.bin \
    tiboot3-am62x-hs-fs-tqma62xx.bin \
    tiboot3-am62x-hs-tqma62xx.bin \
    tiboot3-${SYSFW_DEFAULT_VARIANT}-tqma62xx.bin;tiboot3.bin \
"

UBOOT_ENTRYPOINT = "0x81000000"
UBOOT_LOADADDRESS = "0x81000000"
UBOOT_DTB_LOADADDRESS = "0x83000000"
UBOOT_DTBO_LOADADDRESS = "0x83080000"
UBOOT_RD_LOADADDRESS = "0x84000000"
UBOOT_RD_ENTRYPOINT = "0x84000000"

# TQ u-boot and kernel

require conf/machine/include/tqmxx.inc

MACHINE_FEATURES:remove = "efi"
EFI_PROVIDER = ""

WIC_BOOTPART_SIZE = "12M"
WKS_FILE = "tqmaxx-ti-sdimg${@bb.utils.contains('DISTRO_FEATURES', 'rauc', '-rauc-ab', '', d)}.wks.in"

# List common SoC features
MACHINE_FEATURES += "\
    apm \
    can \
    ext2 \
    pci \
    ubi \
    usbgadget \
    usbhost \
    vfat \
"

# Common image configuration
require conf/machine/include/tq-image.inc

FIT_IMAGE += "linux-tq-fitimage"

# TI image configuration
require conf/machine/include/tq-image-ti.inc
