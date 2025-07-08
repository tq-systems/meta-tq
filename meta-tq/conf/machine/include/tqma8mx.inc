# Common definitions for TQ-Systems GmbH TQMa8MX modules

MACHINEOVERRIDES =. "imx:nxp-hab4:mx8mq:tqma8mq:"

# optionally source the machine specific features for multimedia etc. from
# meta-freescale

include conf/machine/include/imx-base.inc
require conf/machine/include/arm/armv8a/tune-cortexa53.inc

require conf/machine/include/tqma8-common.inc

LOADADDR = ""

# Machine specific options

MACHINE_FEATURES:append = " pci"

# Set DDR FIRMWARE
DDR_FIRMWARE_NAME = "\
    lpddr4_pmu_train_1d_imem.bin \
    lpddr4_pmu_train_1d_dmem.bin \
    lpddr4_pmu_train_2d_imem.bin \
    lpddr4_pmu_train_2d_dmem.bin \
"

LOADADDR = ""
UBOOT_SUFFIX = "bin"
UBOOT_MAKE_TARGET = ""

# multiple U-Boot env locations
TQ_FW_ENV_CONFIGS = "mmc0 mmc1"

ATF_PLATFORM = "imx8mq"

# for sdcard + wic image rule
RAW_BOOT_START_OFFSET_KB = "33"
RAW_BOOT_END_OFFSET_KB = "4096"
# compatibility with meta-freescale
IMX_BOOT_SEEK = "${RAW_BOOT_START_OFFSET_KB}"

# TODO:
# optee rule filters for matching machine names and expects at least imx for MX8
#MACHINE_FEATURES:append = " optee"
#OPTEE_BIN_EXT = "8mq"
DISTRO_FEATURES:remove = "optee"
MACHINE_FEATURES:remove = "optee"
IMAGE_INSTALL:remove = "packagegroup-fsl-optee-imx"

# Image Options

EXTRA_IMAGEDEPENDS += "\
    imx-boot \
"

PREFERRED_VERSION_linux-imx-tq ?= "6.6.%"

#
# bootstream name, construction see imx-boot recipe, do_deploy
#
MACHINE_WKS_BOOTSTREAM ?= "imx-boot-${MACHINE}-sd.bin-${IMXBOOT_SDMMC_HDMI_TARGET}"
