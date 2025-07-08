# Common definitions for TQ-Systems GmbH TQMa91xxLA / TQMa91xxCA

MACHINEOVERRIDES =. "imx:nxp-ahab:mx9:mx91:tqma91xx:"

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

IMX_SOC_REV ?= "A0"

ATF_PLATFORM = "imx91"

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

python() {
    wks_type = ""

    if bb.utils.contains("DISTRO_FEATURES", "secure", True, False, d):
        wks_type = "-verity"
    if bb.utils.contains("DISTRO_FEATURES", "rauc", True, False, d):
        if not bb.utils.contains("DISTRO_FEATURES", "secure", True, False, d):
            wks_type = "-rauc-ab"
        else:
            bb.warn("DISTRO_FEATURES contains 'rauc' and 'secure', using WKS_FILE selected by distro feature 'secure'")

    d.setVar("WKS_FILE", "tqmaxx-imx-v8-sdimg%s.wks.in" % wks_type)
}

MACHINE_FEATURES += "\
    can \
    ext2 \
    ext3 \
    rtc \
    serial \
    ubi \
    vfat \
"

KERNEL_IMAGETYPE = "Image"

PREFERRED_PROVIDER_imx-boot ?= "imx-boot-tq"
PREFERRED_PROVIDER_virtual/kernel:mx91-nxp-bsp ?= "linux-imx-tq"
# At the moment we support only these forks:
PREFERRED_PROVIDER_u-boot:mx91-generic-bsp ?= "u-boot-imx-tq"
PREFERRED_PROVIDER_virtual/bootloader:mx91-generic-bsp ?= "u-boot-imx-tq"
UBOOT_SUFFIX:mx91-generic-bsp = "bin"
UBOOT_SUFFIX:pn-u-boot-imx-tq = "bin"
IMX_DEFAULT_BOOTLOADER ?= "u-boot-imx-tq"

# multiple U-Boot env locations
TQ_FW_ENV_CONFIGS = "mmc0 mmc1 spi"

# Common image configuration
require conf/machine/include/tq-image.inc

PREFERRED_VERSION_linux-imx-tq ?= "6.6.%"
