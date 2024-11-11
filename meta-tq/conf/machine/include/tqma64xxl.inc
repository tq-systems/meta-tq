# Common definitions for TQ-Systems GmbH TQMa64xxL modules

# needed for easier compatible / preferred_provider
MACHINEOVERRIDES =. "tqma64xxl:"

# Default providers, may need to override for specific machines
# needs to be set before inclusion of am64xx.inc
PREFERRED_PROVIDER_virtual/kernel ?= "linux-ti-tq"
PREFERRED_PROVIDER_u-boot ?= "u-boot-ti-tq"
PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-ti-tq"

# am64x-gp and am64x_sr2-hs variants are added by am64xx.inc
IMAGE_BOOT_FILES ?= "${SPL_BINARYNAME} u-boot.${UBOOT_SUFFIX} tiboot3-am64x_sr2-hs-fs-evm.bin"

# From meta-ti-bsp: PREFERRED_PROVIDER and tuning
require conf/machine/include/am64xx.inc

# be more specific then arch-arm64 used from meta-ti
require conf/machine/include/arm/arch-armv8a.inc

# Available variants: am64x-gp am64x_sr2-hs-fs am64x_sr2-hs
SYSFW_DEFAULT_VARIANT ?= "am64x_sr2-hs-fs"

KERNEL_CLASSES += "ti-secdev"

# All 3 variants are installed to the boot partition
# The default variant is additionally installed as tiboot3.bin
IMAGE_BOOT_FILES += "tiboot3-${SYSFW_DEFAULT_VARIANT}-evm.bin;tiboot3.bin"

# Machine specific options

MACHINE_EXTRA_RRECOMMENDS += "\
    kernel-devicetree \
    kernel-modules \
"

# TQ u-boot and kernel

require conf/machine/include/tqmxx.inc

MACHINE_FEATURES:remove = "efi"
EFI_PROVIDER = ""

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

# TI image configuration
require conf/machine/include/tq-image-ti.inc
