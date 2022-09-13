# Common definitions for TQ-Systems GmbH TQMa6UL SOM

# optionally source the machine specific features for multimedia etc. from
# meta-freescale

MACHINEOVERRIDES =. "imx:mx6ul:tqma6ulx:"

include conf/machine/include/imx-base.inc

DEFAULTTUNE:mx6ul ?= "cortexa7thf-neon"
require conf/machine/include/arm/armv7a/tune-cortexa7.inc

# Machine specific options
#
MACHINE_EXTRA_RRECOMMENDS += "\
    kernel-devicetree \
    kernel-modules \
    linux-firmware-imx-sdma-imx6q \
"

MACHINE_FEATURES:append = "\
    alsa \
    can \
    ext2 \
    rtc \
    serial \
    usbhost \
    vfat \
    ubi \
"

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

KERNEL_IMAGETYPE = "zImage"

PREFERRED_PROVIDER_u-boot:tqma6ulx ?= "u-boot-imx-tq"
PREFERRED_PROVIDER_virtual/bootloader:tqma6ulx ?= "u-boot-imx-tq"

UBOOT_SUFFIX:${MACHINE} ?= "imx"
UBOOT_MAKE_TARGET:${MACHINE} ?= "u-boot.imx"

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
