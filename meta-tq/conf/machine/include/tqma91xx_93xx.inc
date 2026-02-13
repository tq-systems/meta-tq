# Common definitions for TQ-Systems GmbH TQMa9[1,3]xxLA / TQMa9[1,3]xxCA

# optionally source the machine specific features for multimedia etc. from
# meta-freescale

include conf/machine/include/imx-base.inc

# Override incompatible defaults from imx-base.inc.
IMAGE_BOOT_FILES = "u-boot-initial-env-sd"

require conf/machine/include/arm/armv8-2a/tune-cortexa55.inc

require conf/machine/include/tqmxx.inc

LOADADDR = ""

# Set DDR FIRMWARE
DDR_FIRMWARE_NAME = "\
    lpddr4_dmem_1d_v202201.bin \
    lpddr4_dmem_2d_v202201.bin \
    lpddr4_imem_1d_v202201.bin \
    lpddr4_imem_2d_v202201.bin \
"

LOADADDR = ""
UBOOT_SUFFIX = "bin"
UBOOT_MAKE_TARGET = ""
SPL_BINARY = "spl/u-boot-spl.bin"

# Image Options

# Add packages we want to be deployed, even when they are not part of rootfs
EXTRA_IMAGEDEPENDS += "\
    imx-boot \
"

# for sdcard + wic image rule
RAW_BOOT_START_OFFSET_KB = "32"
RAW_BOOT_END_OFFSET_KB = "4096"
# compatibility with meta-freescale
IMX_BOOT_SEEK = "${RAW_BOOT_START_OFFSET_KB}"

#
# bootstream name, construction see imx-boot-tq recipe, do_deploy()
#
MACHINE_WKS_BOOTSTREAM ?= "imx-boot-${MACHINE}-sd.bin-flash_singleboot"

WKS_FILE = "${@imx_v8_wks_from_distro_features(d)}"

MACHINE_FEATURES += "\
    can \
    ext2 \
    ext3 \
    rtc \
    serial \
    ubi \
    vfat \
"

KERNEL_IMAGETYPE = "fitImage"
# override default from conf/machine/include/imx-base.inc
KERNEL_IMAGETYPE:mx9-generic-bsp = "fitImage"
KERNEL_IMAGETYPES = "${KERNEL_IMAGETYPE} Image"

PREFERRED_PROVIDER_imx-boot ?= "imx-boot-tq"
UBOOT_SUFFIX:pn-u-boot-imx-tq = "bin"
IMX_DEFAULT_BOOTLOADER ?= "u-boot-imx-tq"

# multiple U-Boot env locations
TQ_FW_ENV_CONFIGS = "mmc0 mmc1 spi"

# Common image configuration
require conf/machine/include/tq-image.inc

PREFERRED_VERSION_linux-imx-tq ?= "6.6.%"
