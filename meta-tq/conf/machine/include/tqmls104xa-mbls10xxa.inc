# shared settings for TQMLS104[3,6]A on MBLS10xxA

# no default assignment here, since we overwrite defaults from qoriq-arm64.inc
SERIAL_CONSOLES = "115200;ttyS1"
SERIAL_CONSOLES_CHECK = "${SERIAL_CONSOLES}"
# disable virtual console support for this board and limit getty to
# SERIAL_CONSOLES only
USE_VT = "0"

UBOOT_RCW_CONFIG ??= ""

# direct assignment to override empty default from meta-freescale
WKS_FILE = "tqmls104xa-sdimg.wks.in"

WKS_FILE_DEPENDS = "\
    dosfstools-native \
    e2fsprogs-native \
    mtools-native \
    parted-native \
"
