# Common definitions for TQ-Systems GmbH TQMLS1012AL modules

require conf/machine/include/soc-family.inc

# source the machine specific features for RCW, multimedia etc. from
# meta-freescale
require conf/machine/include/qoriq-arm64.inc

MACHINEOVERRIDES =. "tqmls1012al:ls1012a:fsl-lsch2:"


require conf/machine/include/tune-cortexa53.inc

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

PREFERRED_PROVIDER_u-boot_tqmls1012al ?= "u-boot-lsdk-tq"
PREFERRED_PROVIDER_virtual/bootloader_tqmls1012al ?= "u-boot-lsdk-tq"
PREFERRED_PROVIDER_virtual/kernel_tqmls1012al ?= "linux-imx-tq"

# Define the default image type
KERNEL_IMAGETYPE ?= "Image.gz"

UBOOT_ENTRYPOINT_ls1012a = "0x80008000"

# Machine specific options
#

MACHINE_EXTRA_RRECOMMENDS += "kernel-modules kernel-devicetree udev-extraconf"

# provide info about machine's capabilities
# OP-TEE is currently untested, so we don't enable the feature for now
MACHINE_FEATURES_append = " ext2 pci rtc serial usbhost vfat ubi"

# Image Options
#
EXTRA_IMAGEDEPENDS += "virtual/bootloader"
EXTRA_IMAGEDEPENDS += "rcw qoriq-atf ppfe-firmware"

# Image Options
#
MKUBIFS_ARGS = " -m 1 -e 65408 -c 896 "
UBINIZE_ARGS = " -p 65536 -m 1 -s 1 "

# needs to be set to have it consistent for kernel command line
UBI_VOLNAME = "root"

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

# default RCW with 1 GHz core frequency
RCWQSPI ?= "default/rcw_1000_sd"

WKS_FILE ?= "tqmls1012al-sdimg.wks"

WKS_FILE_DEPENDS = "\
    mtools-native \
    dosfstools-native \
    parted-native \
    virtual/kernel \
"
