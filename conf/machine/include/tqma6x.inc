# Common definitions for TQ-Systems GmbH TQMa6 modules

# optionally source the machine specific features for multimedia etc. from
# meta-freescale

MACHINEOVERRIDES =. "imx:mx6:tqma6x:"

include conf/machine/include/imx-base.inc

DEFAULTTUNE:imx6 ?= "cortexa9thf-neon"
require conf/machine/include/arm/armv7a/tune-cortexa9.inc

# Machine specific options
#
MACHINE_EXTRA_RRECOMMENDS += "\
    firmware-imx-vpu-imx6d \
    firmware-imx-vpu-imx6q \
    kernel-devicetree \
    kernel-modules \
    linux-firmware-imx-sdma-imx6q \
"

MACHINE_FEATURES += "\
    alsa \
    can \
    ext2 \
    pci \
    rtc \
    serial \
    ubi \
    usbhost \
    vfat \
    vpu \
"

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

KERNEL_IMAGETYPE = "zImage"

PREFERRED_PROVIDER_u-boot:tqma6x ?= "u-boot-imx-tq"
PREFERRED_PROVIDER_virtual/bootloader:tqma6x ?= "u-boot-imx-tq"

UBOOT_SUFFIX:tqma6x ?= "imx"
UBOOT_MAKE_TARGET:tqma6x ?= "u-boot.imx"

# Image Options
#
EXTRA_IMAGEDEPENDS += "virtual/bootloader"

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

WKS_FILE_DEPENDS = "\
    mtools-native \
    dosfstools-native \
    parted-native \
    virtual/kernel \
    virtual/bootloader \
"
