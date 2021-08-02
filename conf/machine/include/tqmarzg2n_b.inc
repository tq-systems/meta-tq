SOC_FAMILY = "rzg2:r8a774b1"
MACHINEOVERRIDES =. "tqmarzg2n_b:r8a774b1:"

DEFAULTTUNE ?= "cortexa57-cortexa53"
require conf/machine/include/arm/arch-armv8a.inc

PREFERRED_PROVIDER_u-boot_tqmarzg2n_b ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader_tqmarzg2n_b ?= "u-boot-tq"

TUNEVALID[cortexa57-cortexa53] = "Enable big.LITTLE Cortex-A57.Cortex-A53 specific processor optimizations"
TUNECONFLICTS[aarch64] = "armv4 armv5 armv6 armv7 armv7a"

TUNE_CCARGS .= "${@bb.utils.contains("TUNE_FEATURES", "aarch64", " -march=armv8-a", "" ,d)}"

MACHINEOVERRIDES =. "${@bb.utils.contains("TUNE_FEATURES", "cortexa57-cortexa53", "cortexa57-cortexa53:", "" ,d)}"

TUNE_CCARGS .= "${@bb.utils.contains("TUNE_FEATURES", "cortexa57-cortexa53", " -mtune=cortex-a57.cortex-a53", "", d)}"

# Little Endian base configs
AVAILTUNES += "cortexa57-cortexa53"
ARMPKGARCH_tune-cortexa57-cortexa53 = "cortexa57-cortexa53"
TUNE_FEATURES_tune-cortexa57-cortexa53 = "${TUNE_FEATURES_tune-aarch64} cortexa57-cortexa53"
PACKAGE_EXTRA_ARCHS_tune-cortexa57-cortexa53 = "${PACKAGE_EXTRA_ARCHS_tune-aarch64} cortexa57-cortexa53"
BASE_LIB_tune-cortexa57-cortexa53 = "lib64"

# 32BIT package install (default is disable)
# This variables can be used only in multilib.
USE_32BIT_PKGS ?= "0"
USE_32BIT_WAYLAND ?= "0"
USE_32BIT_MMP ?= "0"

# Configuration for ARM Trusted Firmware
EXTRA_IMAGEDEPENDS += " arm-trusted-firmware"

# libdrm
PREFERRED_VERSION_libdrm = "2.4.85"

# Add variable to Build Configuration in build log
BUILDCFG_VARS_append = " SOC_FAMILY"

# Command using to build kernel-module-gles
HOSTTOOLS += "sync"

IMAGE_FSTYPES_append = " wic"
IMAGE_FSTYPES_remove = " wic.gz"

# need to have the kernel modules in rootfs
IMAGE_INSTALL_append = " kernel-modules"

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

WKS_FILE ?= "tqmarzg2x-sdimg.wks"

WKS_FILE_DEPENDS = "\
        mtools-native \
        dosfstools-native \
        parted-native \
        virtual/kernel \
        virtual/bootloader \
        "

# Files are needed in IMAGE_BOOT_FILES because /boot is on an extra partition.
# If those files should be installed under /boot inside the rootfs, you can
# achieve this in your BSP layer adding them via
# IMAGE_INSTALL_append = " kernel-devicetree kernel-image-zimage"
#
IMAGE_BOOT_FILES ?= "\
        ${KERNEL_IMAGETYPE} \
        r8a774a1-tqmarzg2m_e-mbarzg2x.dtb \
        r8a774a1-tqmarzg2m_e-mbarzg2x-lvds-tm070jvhg33.dtb \
        "
