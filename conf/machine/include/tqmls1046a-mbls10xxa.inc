# Common definitions for TQ-Systems GmbH TQMLS1046A modules on MBLS10xxA

require conf/machine/include/tqmls1046a.inc

# no default assignment here, since we overwrite defaults from qoriq-arm64.inc
SERIAL_CONSOLES = "115200;ttyS1"
SERIAL_CONSOLES_CHECK = "${SERIAL_CONSOLES}"
# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"

KERNEL_DEVICETREE ?= " \
    freescale/fsl-tqmls1046a-mbls10xxa.dtb \
"

# Files are needed in IMAGE_BOOT_FILES because /boot is on an extra partition.
# If those files should be installed under /boot inside the rootfs, you can
# achieve this in your BSP layer adding them via
# IMAGE_INSTALL_append = " kernel-devicetree kernel-image-zimage"
#
IMAGE_BOOT_FILES = "\
    ${KERNEL_IMAGETYPE};linuximage \
    fsl-tqmls1046a-mbls10xxa.dtb \
"

UBOOT_RCW_CONFIG ??= ""
