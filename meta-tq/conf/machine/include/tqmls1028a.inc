# Common definitions for TQ-Systems GmbH TQMLS1028a modules

require conf/machine/include/soc-family.inc

MACHINEOVERRIDES =. "fsl-lsch3:ls1028a:tqmlsx:tqmls1028a:"

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

# Add packages we want to be deployed, even when they are not part of rootfs
EXTRA_IMAGEDEPENDS += "\
    dp-firmware-cadence \
    qoriq-atf \
    rcw \
"

# Verified values to generate UBI image:
# physical erase block size
UBI_PEB_SIZE = "65536"
UBI_MINIMUM_IO_UNIT_SIZE = "1"
UBI_SUB_PAGE_SIZE = "1"
# UBI_VID_HEADER_OFFSET="64"
# logical erase block size
UBI_LEB_SIZE = "65408"

# we have 64 MB flash, 8 MB for bootloader and env should be enough
# so use 1024 - 128 LEB
UBI_MAX_LEB_COUNT = "896"

MKUBIFS_ARGS = "\
    --min-io-size=${UBI_MINIMUM_IO_UNIT_SIZE} \
    --leb-size=${UBI_LEB_SIZE} \
    --max-leb-cnt=${UBI_MAX_LEB_COUNT} \
"

UBINIZE_ARGS = "\
    --peb-size=${UBI_PEB_SIZE} \
    --min-io-size=${UBI_MINIMUM_IO_UNIT_SIZE} \
    --sub-page-size=${UBI_SUB_PAGE_SIZE} \
"

# needs to be set to have it consistent for kernel command line
# Use the expected value of the ubifs filesystem's volume name in the kernel
# and u-boot.
UBI_VOLNAME = "root"

ATF_SECURE_SUFFIX ?= "${@bb.utils.contains('DISTRO_FEATURES', 'secure', '_sec', '', d)}"

# For the 4GB variant set
# BL2_IMAGE to 'bl2_auto${ATF_SECURE_SUFFIX}_tqmls1028a_4gb.pbl' and
# BL3_IMAGE to 'fip_uboot${ATF_SECURE_SUFFIX}_tqmls1028a_4gb.bin'
BL2_IMAGE ?= "bl2_auto${ATF_SECURE_SUFFIX}.pbl"
BL3_IMAGE ?= "fip_uboot${ATF_SECURE_SUFFIX}.bin"

# auto boottype can boot from both eMMC and SD card
BOOTTYPE ?= "auto flexspi_nor"

ATF_RCW_VARIANTS ?= " \
    default/rcw_800 \
    default/rcw_1000 \
    default/rcw_1300 \
"
RCWAUTO ?= "default/rcw_1300"
RCWXSPI ?= "default/rcw_1300"

# Common image configuration
require conf/machine/include/tq-image.inc
