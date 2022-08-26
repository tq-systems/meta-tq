# Common definitions for TQ-Systems GmbH TQMLS1012AL modules on MBLS1012AL

MACHINE_FEATURES += "wifi"
MACHINE_EXTRA_RRECOMMENDS += "linux-firmware-ath10k"

# no default assignment here, since we overwrite defaults from qoriq-arm.inc
SERIAL_CONSOLES = "115200;ttyS0"
SERIAL_CONSOLES_CHECK = "${SERIAL_CONSOLES}"
# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"

KERNEL_DEVICETREE ?= " \
  freescale/fsl-ls1012a-mbls1012al.dtb \
"

# Files are needed in IMAGE_BOOT_FILES because /boot is on an extra partition.
# If those files should be installed under /boot inside the rootfs, you can
# achieve this in your BSP layer adding them via
# IMAGE_INSTALL:append = " kernel-devicetree kernel-image"
#
IMAGE_BOOT_FILES = "\
    ${KERNEL_IMAGETYPE} \
    fsl-ls1012a-mbls1012al.dtb \
"

# direct assignment to override empty default from meta-freescale
WKS_FILE = "tqmls1012al-sdimg.wks"

WKS_FILE_DEPENDS = "\
    mtools-native \
    dosfstools-native \
    parted-native \
"

UBOOT_CONFIG ?= "tfa"
UBOOT_CONFIG[tfa] = "tqmls1012al_mbls1012al_tfa_defconfig"
