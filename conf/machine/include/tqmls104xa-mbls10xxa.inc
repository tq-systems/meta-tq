# shared settings for TQMLS104[3,6]A on MBLS10xxA

# no default assignment here, since we overwrite defaults from qoriq-arm64.inc
SERIAL_CONSOLES = "115200;ttyS1"
SERIAL_CONSOLES_CHECK = "${SERIAL_CONSOLES}"
# disable virtual console support for this board and limit getty to
# SERIAL_CONSOLES only
USE_VT = "0"

# These things are also still in IMAGE_BOOT_FILES because /boot is on an extra
# partition. We will use them in future also under /boot inside the rootfs, so
# we enable this also:
IMAGE_INSTALL:append += "\
	kernel-devicetree \
	kernel-image \
"

UBOOT_RCW_CONFIG ??= ""

# direct assignment to override empty default from meta-freescale
WKS_FILE = "tqmls10xxa-sdimg.wks.in"

WKS_FILE_DEPENDS = "\
    mtools-native \
    dosfstools-native \
    parted-native \
    virtual/kernel \
    virtual/bootloader \
"
