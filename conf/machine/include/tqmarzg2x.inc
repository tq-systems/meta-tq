# Common definitions for TQ-Systems GmbH TQMaRZG2x modules

MACHINEOVERRIDES =. "tqmarzg2x:"

require conf/machine/include/cip-core.inc
require conf/machine/include/rzg2h-common.inc

EXT_GFX_BACKEND = "${@oe.utils.ifelse((d.getVar('RZ_FEATURE') == 'True') and (d.getVar('USE_RENESAS_GLES') == '1'), '1', '0')}"

# 32BIT package install (default is disable)
# This variables can be used only in multilib.
USE_32BIT_PKGS ?= "0"
USE_32BIT_WAYLAND ?= "0"
USE_32BIT_MMP ?= "0"

# Machine specific options
#
MACHINE_FEATURES = "ext2 pci rtc serial usb3 usbhost vfat can"

require conf/machine/include/tqmxx.inc

# Configuration for kernel
PREFERRED_PROVIDER_virtual/kernel = "linux-renesas"
KERNEL_IMAGETYPE = "Image"

# u-boot
PREFERRED_VERSION_u-boot = "2021.10"
PREFERRED_PROVIDER_u-boot_tqmarzg2x ?= "u-boot-renesas-tq"
PREFERRED_PROVIDER_virtual/bootloader_tqmarzg2x ?= "u-boot-renesas-tq"

# Image Options
#
EXTRA_IMAGEDEPENDS += "virtual/bootloader"

IMAGE_FSTYPES_append = " tar.bz2 ext4 wic"
IMAGE_FSTYPES_remove = " wic.gz"

# Add variable to Build Configuration in build log
BUILDCFG_VARS += "SOC_FAMILY"

# Command using to build kernel-module-gles
HOSTTOOLS += "sync"

# flash writer
EXTRA_IMAGEDEPENDS += "flash-writer"

# need to have the kernel modules in rootfs
MACHINE_EXTRA_RRECOMMENDS_append = " kernel-modules"

# provide dt name handling to prevent a lot of copy and paste
# in kernel prefix where to find dtb
KERNEL_DEVICETREE_PATH_PREFIX = "renesas/"

python () {
    dt_list = d.getVar("BOARD_DEVICETREE_FILES").split()
    dt_prefix = d.getVar("KERNEL_DEVICETREE_PATH_PREFIX")
    dt_prefix += "% s"
    dt_list =  [dt_prefix % i for i in dt_list]
    d.setVar("KERNEL_DEVICETREE", ' '.join(dt_list))
}

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

WKS_FILE_DEPENDS = "\
	mtools-native \
	dosfstools-native \
	parted-native \
	virtual/kernel \
	virtual/bootloader \
"

#
# Options below this line are imported from meta-rzg2/docs/sample/conf/*/poky-gcc/local.conf
# and not modified.
#

MACHINE_FEATURES += "gsx multimedia usb3"
MULTI_PROVIDER_WHITELIST += "virtual/libgl virtual/egl virtual/libgles1 virtual/libgles2"

#
# End of imported options
#
