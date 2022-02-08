# Common definitions for TQ-Systems GmbH TQMa6UL SOM

# optionally source the machine specific features for multimedia etc. from
# meta-freescale

MACHINEOVERRIDES =. "imx:mx6:mx6ul:tqma6ulx:"

include conf/machine/include/imx-base.inc

DEFAULTTUNE ?= "cortexa7thf-neon"
require conf/machine/include/tune-cortexa7.inc

# Machine specific options
#
MACHINE_EXTRA_RRECOMMENDS += "kernel-modules kernel-devicetree"
MACHINE_EXTRA_RRECOMMENDS += " linux-firmware-imx-sdma-imx6q"
MACHINE_FEATURES_append = " alsa ext2 rtc serial usbhost vfat can ubi"

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

PREFERRED_PROVIDER_virtual/kernel ??= "linux-imx-tq"
PREFERRED_PROVIDER_virtual/kernel_use-mainline-bsp ??= "linux-tq"
KERNEL_IMAGETYPE = "zImage"

PREFERRED_PROVIDER_u-boot_tqma6ulx ?= "u-boot-imx-tq"
PREFERRED_PROVIDER_virtual/bootloader_tqma6ulx ?= "u-boot-imx-tq"

UBOOT_SUFFIX_${MACHINE} ?= "imx"
UBOOT_MAKE_TARGET_${MACHINE} ?= "u-boot.imx"

UBOOT_CONFIG ?= "sd qspi"


# Image Options
#
EXTRA_IMAGEDEPENDS += "virtual/bootloader"

# from PTXdist BSP:
# PTXCONF_IMAGE_ROOT_UBIFS_PEB_SIZE="65536"
# PTXCONF_IMAGE_ROOT_UBIFS_MINIMUM_IO_UNIT_SIZE="1"
# PTXCONF_IMAGE_ROOT_UBIFS_SUB_PAGE_SIZE="1"
# PTXCONF_IMAGE_ROOT_UBIFS_VID_HEADER_OFFSET="64"
# PTXCONF_IMAGE_ROOT_UBIFS_LEB_SIZE="65408"

MKUBIFS_ARGS = " -m 1 -e 65408 -c 896 "
UBINIZE_ARGS = " -p 65536 -m 1 -s 1 "
# needs to be set to have it consistent for kernel command line
UBI_VOLNAME = "root"

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

WKS_FILE_DEPENDS = "\
    mtools-native \
    dosfstools-native \
    parted-native \
    virtual/kernel \
    virtual/bootloader \
"

# TODO:
#IMAGE_INSTALL_append = " kernel-devicetree kernel-image-zimage"
