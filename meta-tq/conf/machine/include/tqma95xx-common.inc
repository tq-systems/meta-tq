# Common definitions for all TQ-Systems GmbH TQMa95xxLA / TQMa95xxSA modules

MACHINEOVERRIDES =. "imx:mx9:mx95:tqma95xx:"

# optionally source the machine specific features for multimedia etc. from
# meta-freescale

include conf/machine/include/imx-base.inc
require conf/machine/include/arm/armv8-2a/tune-cortexa55.inc

require conf/machine/include/tqmxx.inc

LOADADDR = ""

# Set DDR FIRMWARE
DDR_FIRMWARE_NAME = " \
    lpddr5_dmem_v202409.bin \
    lpddr5_dmem_qb_v202409.bin \
    lpddr5_imem_v202409.bin \
    lpddr5_imem_qb_v202409.bin \
"

IMX_SOC_REV:${MACHINE} ?= "B0"
OEI_DDRCONFIG ?= "TQMa95xxSA.DDR-Timing.${OEI_RAM_SIZE}GB.V25.06.0006"

ATF_PLATFORM = "imx95"
OEI_CONFIGS = "ddr tcm"
OEI_CORE   = "m33"
OEI_SOC    = "mx95"
OEI_BOARD  ?= "tqma95xx"
DDR_TYPE   = "lpddr5"

SYSTEM_MANAGER_FIRMWARE_BASENAME = "m33_image"

PREFERRED_PROVIDER_virtual/imx-system-manager = "imx-system-manager-tq"
PREFERRED_PROVIDER_virtual/imx-oei = "imx-oei-tq"

UBOOT_SUFFIX = "bin"
UBOOT_MAKE_TARGET = ""
SPL_BINARY = "spl/u-boot-spl.bin"

# Image Options

IMXBOOT_TARGETS ?= "\
    flash_a55 \
    flash_a55_flexspi \
"
MACHINE_WKS_BOOTSTREAM = "imx-boot-${MACHINE}-sd.bin-flash_a55"

WKS_FILE = "${@imx_v8_wks_from_distro_features(d)}"

# Add packages we want to be deployed, even when they are not part of rootfs
EXTRA_IMAGEDEPENDS += "\
    imx-boot \
"

# for sdcard + wic image rule
RAW_BOOT_START_OFFSET_KB = "32"
RAW_BOOT_END_OFFSET_KB = "4096"
# compatibility with meta-freescale
IMX_BOOT_SEEK = "${RAW_BOOT_START_OFFSET_KB}"

MACHINE_EXTRA_RRECOMMENDS += "\
    mali-imx-libgles1 \
"

MACHINE_FEATURES += "\
    can \
    ext2 \
    ext3 \
    npu \
    rtc \
    pci \
    serial \
    ubi \
    vfat \
    vpu \
"

KERNEL_IMAGETYPE = "Image"

PREFERRED_PROVIDER_imx-boot ?= "imx-boot-tq"
PREFERRED_PROVIDER_virtual/kernel:mx95-nxp-bsp ?= "linux-imx-tq"
# At the moment we support only these forks:
PREFERRED_PROVIDER_u-boot:mx95-generic-bsp ?= "u-boot-imx-tq"
PREFERRED_PROVIDER_virtual/bootloader:mx95-generic-bsp ?= "u-boot-imx-tq"
UBOOT_SUFFIX:mx95-generic-bsp = "bin"
UBOOT_SUFFIX:pn-u-boot-imx-tq = "bin"
IMX_DEFAULT_BOOTLOADER ?= "u-boot-imx-tq"

# Common image configuration
require conf/machine/include/tq-image.inc
