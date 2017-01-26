# Common definitions for TQ-Group TQMa7x modules

include conf/machine/include/imx-base.inc
include conf/machine/include/tune-cortexa7.inc

# Machine specific options
#
MACHINE_EXTRA_RRECOMMENDS = " kernel-modules kernel-devicetree"
MACHINE_FEATURES_append = " pci wifi"
SERIAL_CONSOLE = "115200 ttymxc1"

# TQ u-boot and kernel
#
PREFERRED_PROVIDER_virtual/kernel ?= "linux-tq"
KERNEL_IMAGETYPE = "zImage"

PREFERRED_PROVIDER_u-boot = "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-tq"

# Image Options
#
EXTRA_IMAGEDEPENDS += "u-boot"
IMAGE_FSTYPES = "ext3 wic"
do_image_wic[depends] += "mtools-native:do_populate_sysroot dosfstools-native:do_populate_sysroot parted-native:do_populate_sysroot"
#IMAGE_INSTALL_append = " kernel-devicetree kernel-image-zimage"
