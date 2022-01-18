# Common definitions for TQ-Systems GmbH TQMa6 modules

# optionally source the machine specific features for multimedia etc. from
# meta-freescale

MACHINEOVERRIDES =. "tqma6x:"
MACHINEOVERRIDES =. "mx6:"

include conf/machine/include/imx-base.inc

DEFAULTTUNE ?= "cortexa9thf-neon"
require conf/machine/include/tune-cortexa9.inc

# Machine specific options
#
MACHINE_EXTRA_RRECOMMENDS += "kernel-modules kernel-devicetree"
MACHINE_EXTRA_RRECOMMENDS += "linux-firmware-imx-sdma-imx6q"
MACHINE_FEATURES += "alsa ext2 pci rtc serial usbhost vfat can vpu"

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

PREFERRED_PROVIDER_virtual/kernel_tqma6x ?= "linux-imx-tq"
PREFERRED_PROVIDER_virtual/kernel_tqma6x_use-mainline-bsp ?= "linux-tq"
KERNEL_IMAGETYPE = "zImage"

PREFERRED_PROVIDER_u-boot_tqma6x ?= "u-boot-imx-tq"
PREFERRED_PROVIDER_virtual/bootloader_tqma6x ?= "u-boot-imx-tq"

UBOOT_SUFFIX_${MACHINE} ?= "imx"
UBOOT_MAKE_TARGET_${MACHINE} ?= "u-boot.imx"

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
