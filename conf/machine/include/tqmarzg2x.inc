# Common definitions for TQ-Systems GmbH TQMaRZ/G2 modules

MACHINEOVERRIDES =. "tqmarzg2x:"

# 32BIT package install (default is disable)
# This variables can be used only in multilib.
USE_32BIT_PKGS ?= "0"
USE_32BIT_WAYLAND ?= "0"
USE_32BIT_MMP ?= "0"

# Machine specific options
# 
MACHINE_FEATURES = "ext2 pci rtc serial usb3 usbhost vfat can"

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

# Configuration for kernel
PREFERRED_PROVIDER_virtual/kernel = "linux-renesas"
KERNEL_IMAGETYPE = "Image"

# u-boot
PREFERRED_VERSION_u-boot = "2018.09%"
PREFERRED_PROVIDER_u-boot_tqmarzg2x ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader_tqmarzg2x ?= "u-boot-tq"

# Image Options
#
EXTRA_IMAGEDEPENDS += "virtual/bootloader"

IMAGE_FSTYPES_append = " tar.bz2 ext4 wic"
IMAGE_FSTYPES_remove = " wic.gz"

# Configuration for ARM Trusted Firmware
EXTRA_IMAGEDEPENDS += " arm-trusted-firmware"

# libdrm
PREFERRED_VERSION_libdrm = "2.4.85"

# Add variable to Build Configuration in build log
BUILDCFG_VARS_append = " SOC_FAMILY"

# Command using to build kernel-module-gles
HOSTTOOLS += "sync"

# flash writer
EXTRA_IMAGEDEPENDS += " flash-writer"

# need to have the kernel modules in rootfs
IMAGE_INSTALL_append = " kernel-modules"

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

WKS_FILE_DEPENDS = "\
	mtools-native \
	dosfstools-native \
	parted-native \
	virtual/kernel \
	virtual/bootloader \
"