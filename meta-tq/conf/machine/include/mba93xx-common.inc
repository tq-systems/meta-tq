# Common definitions for TQ-Systems GmbH MBa93xxCA / MBa93xxLA

require conf/machine/include/tqma93xx-common.inc

# Machine specific options

WKS_FILE = "tqmaxx-imx-v8-sdimg.wks.in"

# EXTRA_IMAGEDEPENDS are applied to do_image_complete only. do_image_wic may run
# before and needs those packages as well
WKS_FILE_DEPENDS += "${EXTRA_IMAGEDEPENDS}"

MACHINE_FEATURES += "\
    bluetooth \
    screen \
    touchscreen \
    usbhost \
    wifi \
"

# we have no audio interface on our board.
MACHINE_FEATURES:remove = "alsa"

# direct assign to SERIAL_CONSOLES to prevent wrong defaults and not using
# deprecated SERIAL_CONSOLE
SERIAL_CONSOLES = "115200;ttyLP0"

# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"

IMXBOOT_TARGETS ?= "\
    flash_singleboot \
    flash_singleboot_flexspi \
"

#
# bootstream name, construction see imx-boot-tq recipe, do_deploy()
#
MACHINE_WKS_BOOTSTREAM ?= "imx-boot-${MACHINE}-sd.bin-flash_singleboot"

MACHINE_EXTRA_RRECOMMENDS += "\
    linux-firmware-pcie8997 \
    linux-firmware-sd8997 \
    linux-firmware-usb8997 \
"
