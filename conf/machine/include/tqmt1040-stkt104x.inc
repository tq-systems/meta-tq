# Common definitions for TQ-Group TQMT1040 modules on STKT104x

MACHINEOVERRIDES =. "t1:t1040:"

PREFERRED_VERSION_openssl = ""

## TQ u-boot and kernel
#

UBOOT_CONFIG ??= "sdcard nor"
UBOOT_CONFIG[nor] ?= "TQMT1040_defconfig"
UBOOT_CONFIG[sdcard] ?= "TQMT1040_SDCARD_defconfig"

FSL_RCW ?= "TQMT1040_SERDES66"

KERNEL_DEVICETREE ?= " \
	fsl/tqmt1040-stkt104x.dtb \
"

# Files are needed in IMAGE_BOOT_FILES because /boot is on an extra partition.
# If those files should be installed under /boot inside the rootfs, you can
# achieve this in your BSP layer adding them via
# IMAGE_INSTALL_append = " kernel-devicetree kernel-image-zimage"
#
IMAGE_BOOT_FILES ?= "\
    ${KERNEL_IMAGETYPE};linuximage \
    tqmt1040-stkt104x.dtb\
    "
