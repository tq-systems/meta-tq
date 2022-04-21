# Common definitions for TQ-Systems GmbH TQMLS1028a modules

require conf/machine/include/soc-family.inc

# source the machine specific features for RCW, multimedia etc. from
# meta-freescale
require conf/machine/include/qoriq-arm64.inc

MACHINEOVERRIDES =. "fsl-lsch3:ls1028a:tqmls1028a:"


require conf/machine/include/tune-cortexa72.inc

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

PREFERRED_PROVIDER_u-boot_tqmls1028a ?= "u-boot-lsdk-tq"
PREFERRED_PROVIDER_virtual/bootloader_tqmls1028a ?= "u-boot-lsdk-tq"
PREFERRED_PROVIDER_virtual/kernel ??= "linux-imx-tq"

# Define the default image type, overwrite default from qoriq-arm64.inc
KERNEL_IMAGETYPE = "Image.gz"

UBOOT_ENTRYPOINT_ls1028a = "0x80008000"

# Machine specific options
#

MACHINE_EXTRA_RRECOMMENDS += "kernel-modules kernel-devicetree udev-extraconf"

# provide info about machine's capabilities
# OP-TEE is currently untested, so we don't enable the feature for now
MACHINE_FEATURES_append = " alsa ext2 pci rtc serial usbhost vfat can ubi"

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

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

ATF_RCW_VARIANTS ?= " \
    default/rcw_1300_sd.bin \
    default/rcw_1300_spi_nor.bin \
    default/rcw_800_sd.bin \
    default/rcw_800_spi_nor.bin\
"
RCWSD ?= "default/rcw_1300_sd"
RCWXSPI ?= "default/rcw_1300_spi_nor"
