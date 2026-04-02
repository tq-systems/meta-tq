# Common definitions for TQ-Systems GmbH TQMa8MP modules

MACHINEOVERRIDES =. "imx:nxp-hab4:mx8mp:"

# optionally source the machine specific features for multimedia etc. from
# meta-freescale

include conf/machine/include/imx-base.inc
require conf/machine/include/arm/armv8a/tune-cortexa53.inc

require conf/machine/include/tqma8-common.inc

# Machine specific options

MACHINE_FEATURES += "\
    can \
    npu \
    pci \
"

# Set LPDDR4 FIRMWARE
DDR_FIRMWARE_NAME = "\
    lpddr4_pmu_train_1d_dmem_202006.bin \
    lpddr4_pmu_train_1d_imem_202006.bin \
    lpddr4_pmu_train_2d_dmem_202006.bin \
    lpddr4_pmu_train_2d_imem_202006.bin \
"

LOADADDR = ""
UBOOT_SUFFIX = "bin"
UBOOT_MAKE_TARGET = ""

# multiple U-Boot env locations
TQ_FW_ENV_CONFIGS = "mmc0 mmc1 spi"

# for sdcard + wic image rule
RAW_BOOT_START_OFFSET_KB = "32"
RAW_BOOT_END_OFFSET_KB = "4096"
# compatibility with meta-freescale
IMX_BOOT_SEEK = "${RAW_BOOT_START_OFFSET_KB}"

ATF_PLATFORM = "imx8mp"

# TODO:
# optee rule filters for matching machine names and expects at least imx for MX8
#MACHINE_FEATURES:append = " optee"
#OPTEE_BIN_EXT = "8mp"
DISTRO_FEATURES_OPTED_OUT += "optee"
MACHINE_FEATURES_OPTED_OUT += "optee"
IMAGE_INSTALL:remove = "packagegroup-fsl-optee-imx"

# Image Options

# Add packages we want to be deployed, even when they are not part of rootfs
EXTRA_IMAGEDEPENDS += "\
    imx-boot \
"

# SD boot image and FlexSPI boot image can be built using same U-Boot config
IMXBOOT_TARGETS ?= "\
    flash_evk_flexspi \
    flash_spl_uboot \
"

#
# bootstream name, construction see imx-boot recipe, do_deploy
#
MACHINE_WKS_BOOTSTREAM ?= "imx-boot-${MACHINE}-sd.bin-flash_spl_uboot"

FIT_IMAGE += "linux-tq-fitimage"

#
# TODO: revisit memory layout for optee support
#
# fitImage Settings:
# Kernel image offset in RAM:               64 MiB
# reserved space for uncompressed kernel:   48 MiB
# fdt/dtb offset in RAM:                    112 MiB
# dtbo offset in RAM:                       113 MiB
# init(rd) offset in RAM:                   128 MiB
UBOOT_ENTRYPOINT:mx8m-generic-bsp = "0x44000000"
UBOOT_LOADADDRESS:mx8m-generic-bsp = "0x44000000"
UBOOT_DTB_LOADADDRESS:mx8m-generic-bsp = "0x47000000"
UBOOT_DTBO_LOADADDRESS:mx8m-generic-bsp = "0x47100000"
UBOOT_RD_LOADADDRESS:mx8m-generic-bsp = "0x48000000"
UBOOT_RD_ENTRYPOINT:mx8m-generic-bsp = "0x48000000"
