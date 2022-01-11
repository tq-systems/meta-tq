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

#
# Options below this line are imported from meta-rzg2/docs/sample/conf/*/poky-gcc/local.conf
# and not modified.
#

# Enable Gfx Pkgs
MACHINE_FEATURES_append = " gsx"
MULTI_PROVIDER_WHITELIST += "virtual/libgl virtual/egl virtual/libgles1 virtual/libgles2"

# Enable Multimedia features
MACHINE_FEATURES_append = " multimedia"

# Configuration for USB 3.0
MACHINE_FEATURES_append = " usb3"

# Configuration for Docker
#MACHINE_FEATURES_append = " docker"

# Configuration for ECC
# Add ecc to MACHINE_FEATURES to configure DRAM for ECC usage,
# ECC_MODE : Full, Full Dual, Full Single, Partial
#  - Full : DRAM is configured for FULL ECC support, half of memory is reduced for storing ECC code
#           Default is Full Single for RZ/G2E, RZ/G2N, Full Dual for RZ/G2M(v1.3 & v3.0), RZ/G2H
#  - Full Dual : DRAM is configured for FULL ECC Dual channel support, half of memory is reduced for storing ECC code
#                Use only for RZ/G2M(v1.3 & v3.0) and RZ/G2H
#  - Full Single: DRAM is configured for FULL ECC Single channel support, half of memory is reduced for storing ECC code
#                 Use only for RZ/G2E, RZ/G2N, RZ/G2M(v3.0) and RZ/G2H
#  - Partial: Manual add/remove ECC area by u-boot command (Default mode)
# Example of setting:
# MACHINE_FEATURES_append = " ecc"
# ECC_MODE = "Partial"

