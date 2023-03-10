# shared settings for TQMLS104[3,6]A on MBLS10xxA

# no default assignment here, since we overwrite defaults from qoriq-arm64.inc
SERIAL_CONSOLES = "115200;ttyS1"
SERIAL_CONSOLES_CHECK = "${SERIAL_CONSOLES}"
# disable virtual console support for this board and limit getty to
# SERIAL_CONSOLES only
USE_VT = "0"

# Install kernel and devicetree also under /boot inside the rootfs,
# needed for UBIFS use case to prevent extra partition(s) for kernel and dtb.
MACHINE_EXTRA_RRECOMMENDS += "\
    kernel-devicetree \
    kernel-image \
"

UBOOT_RCW_CONFIG ??= ""

# direct assignment to override empty default from meta-freescale
WKS_FILE = "tqmls104xa-sdimg.wks.in"

# EXTRA_IMAGEDEPENDS are applied to do_image_complete only. do_image_wic may run
# before and needs those packages as well
WKS_FILE_DEPENDS += "${EXTRA_IMAGEDEPENDS}"
