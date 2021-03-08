# Common definitions for TQ-Group TQMT1042 modules on STKT104x

MACHINEOVERRIDES =. "t1:t1042:"

PREFERRED_VERSION_openssl = ""

## TQ u-boot and kernel
#

#use u-boot config of tqmt1042
UBOOT_CONFIG ??= "sdcard nor"
UBOOT_CONFIG[nor] ?= "TQMT1022_defconfig"
UBOOT_CONFIG[sdcard] ?= "TQMT1022_SDCARD_defconfig"

FSL_RCW ?= "TQMT1042_SERDES86"

KERNEL_DEVICETREE ?= " \
	fsl/tqmt1022-stkt104x.dtb \
"

IMAGE_BOOT_FILES ?= "\
    ${KERNEL_IMAGETYPE};linuximage \
    tqmt1022-stkt104x.dtb\
    "
