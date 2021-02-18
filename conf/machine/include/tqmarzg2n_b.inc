SOC_FAMILY = "rzg2:r8a774b1"
MACHINEOVERRIDES =. "tqmarzg2n_b:r8a774b1:"

DEFAULTTUNE ?= "cortexa57-cortexa53"
require conf/machine/include/arm/arch-armv8a.inc

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
