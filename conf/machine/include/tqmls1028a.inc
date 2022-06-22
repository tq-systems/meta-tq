# Common definitions for TQ-Systems GmbH TQMLS1028a modules

require conf/machine/include/soc-family.inc

MACHINEOVERRIDES =. "fsl-lsch3:ls1028a:tqmls1028a:"

# TQ-Systems U-Boot and kernel has to be set before qoriq-arm64.inc
# to allow weak settings
#
require conf/machine/include/tqmxx.inc

PREFERRED_PROVIDER_u-boot:tqmls1028a ?= "u-boot-lsdk-tq"
PREFERRED_PROVIDER_virtual/bootloader:tqmls1028a ?= "u-boot-lsdk-tq"
PREFERRED_PROVIDER_virtual/kernel ??= "linux-imx-tq"

require conf/machine/include/qoriq-arm64.inc

require conf/machine/include/arm/armv8a/tune-cortexa72.inc

# Define the default image type, overwrite default from qoriq-arm64.inc
KERNEL_IMAGETYPE = "Image.gz"

UBOOT_ENTRYPOINT:ls1028a = "0x80008000"

# Machine specific options
#

MACHINE_EXTRA_RRECOMMENDS += "\
    kernel-devicetree \
    kernel-modules \
    udev-extraconf \
"

# provide info about machine's capabilities
# OP-TEE is currently untested, so we don't enable the feature for now
MACHINE_FEATURES:append = "\
    alsa \
    can \
    ext2 \
    pci \
    rtc \
    serial \
    ubi \
    usbhost \
    vfat \
"

# Image Options
#
EXTRA_IMAGEDEPENDS += "virtual/bootloader"
EXTRA_IMAGEDEPENDS += "rcw qoriq-atf dp-firmware-cadence"

# Image Options
#
MKUBIFS_ARGS = " -m 1 -e 65408 -c 896 "
UBINIZE_ARGS = " -p 65536 -m 1 -s 1 "

# needs to be set to have it consistent for kernel command line
UBI_VOLNAME = "root"

# For the 4GB variant set BL2_IMAGE to 'bl2_sd_tqmls1028a_4gb.pbl'
# and BL3_IMAGE to 'fip_uboot_tqmls1028a_4gb.bin'
BL2_IMAGE ?= "bl2_sd.pbl"
BL3_IMAGE ?= "fip_uboot.bin"

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

ATF_RCW_VARIANTS ?= " \
    default/rcw_800_sd.bin \
    default/rcw_1000_sd.bin \
    default/rcw_1000_spi_nor.bin \
    default/rcw_1300_sd.bin \
    default/rcw_1300_spi_nor.bin \
"
RCWSD ?= "default/rcw_1300_sd"
RCWXSPI ?= "default/rcw_1300_spi_nor"
