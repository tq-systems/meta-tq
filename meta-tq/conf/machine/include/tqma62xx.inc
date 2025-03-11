# Common definitions for TQ-Systems GmbH TQMa64xxL modules

# needed for easier compatible / preferred_provider
MACHINEOVERRIDES =. "tqma62xx:"

# Default providers, may need to override for specific machines
# needs to be set before inclusion of am64xx.inc
PREFERRED_PROVIDER_virtual/kernel ?= "linux-ti-tq"
PREFERRED_PROVIDER_u-boot ?= "u-boot-ti-tq"
PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-ti-tq"

TQ_FW_ENV_CONFIGS = "mmc0 mmc1 spi"

# Copied from arch-armv8a.inc
DEFAULTTUNE ?= "armv8a-crc"

TUNEVALID[armv8a] = "Enable instructions for ARMv8-a"
TUNE_CCARGS_MARCH .= "${@bb.utils.contains('TUNE_FEATURES', 'armv8a', ' -march=armv8-a', '', d)}"
MACHINEOVERRIDES =. "${@bb.utils.contains('TUNE_FEATURES', 'armv8a', 'armv8a:', '', d)}"

# From meta-ti-bsp: PREFERRED_PROVIDER and tuning
require conf/machine/include/am62xx.inc

# Copied from arch-armv8a.inc
require conf/machine/include/arm/feature-arm-crc.inc
require conf/machine/include/arm/feature-arm-crypto.inc

# Little Endian base configs
AVAILTUNES += "armv8a armv8a-crc armv8a-crc-crypto armv8a-crypto"
ARMPKGARCH:tune-armv8a                    ?= "armv8a"
ARMPKGARCH:tune-armv8a-crc                ?= "armv8a"
ARMPKGARCH:tune-armv8a-crypto             ?= "armv8a"
ARMPKGARCH:tune-armv8a-crc-crypto         ?= "armv8a"
TUNE_FEATURES:tune-armv8a                  = "aarch64 armv8a"
TUNE_FEATURES:tune-armv8a-crc              = "${TUNE_FEATURES:tune-armv8a} crc"
TUNE_FEATURES:tune-armv8a-crypto           = "${TUNE_FEATURES:tune-armv8a} crypto"
TUNE_FEATURES:tune-armv8a-crc-crypto       = "${TUNE_FEATURES:tune-armv8a-crc} crypto"
PACKAGE_EXTRA_ARCHS:tune-armv8a            = "aarch64 armv8a"
PACKAGE_EXTRA_ARCHS:tune-armv8a-crc        = "${PACKAGE_EXTRA_ARCHS:tune-armv8a} armv8a-crc"
PACKAGE_EXTRA_ARCHS:tune-armv8a-crypto     = "${PACKAGE_EXTRA_ARCHS:tune-armv8a} armv8a-crypto"
PACKAGE_EXTRA_ARCHS:tune-armv8a-crc-crypto = "${PACKAGE_EXTRA_ARCHS:tune-armv8a-crc} armv8a-crypto armv8a-crc-crypto"
BASE_LIB:tune-armv8a                       = "lib64"
BASE_LIB:tune-armv8a-crc                   = "lib64"
BASE_LIB:tune-armv8a-crypto                = "lib64"
BASE_LIB:tune-armv8a-crc-crypto            = "lib64"

# Available variants: am62x-gp am62x-hs-fs am62x-hs
SYSFW_DEFAULT_VARIANT ?= "am62x-hs-fs"

KERNEL_CLASSES += "ti-secdev"

IMAGE_BOOT_FILES:remove = "ti_logo_414x97_32bpp.bmp.gz"
# All 3 variants are installed to the boot partition
# The default variant is additionally installed as tiboot3.bin
IMAGE_BOOT_FILES += "tiboot3-${SYSFW_DEFAULT_VARIANT}-evm.bin;tiboot3.bin"

# Machine specific options

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
