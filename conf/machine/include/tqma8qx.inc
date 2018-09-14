# Common definitions for TQ-Group TQMa8QX modules

require conf/machine/include/arm/arch-arm64.inc

MACHINEOVERRIDES =. "mx8:mx8qxp:"

# Don't include kernels in standard images
RDEPENDS_kernel-base = ""

LOADADDR = ""

# Machine specific options
#
MACHINE_EXTRA_RRECOMMENDS = " kernel-modules kernel-devicetree"
MACHINE_FEATURES_append = " pci alsa"

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

KERNEL_IMAGETYPE = "Image"

#
# TODO: layer needs to be placed at bottom of layer stack to make sure we win!
#

UBOOT_MAKE_TARGET = "u-boot.bin"
UBOOT_SUFFIX = "bin"

# for sdcard image rule
BOOT_SPACE = "65536"
IMAGE_BOOTLOADER = "imx-boot"
# for sdcard image rule
IMX_BOOT_SEEK = "32"

# optionally source the machine specific features for multimedia etc. from
# meta-freescale

include conf/machine/include/imx-base.inc
