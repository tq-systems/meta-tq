# Common definitions for TQ-Systems GmbH MBa91xxCA

# Machine specific options

require conf/machine/include/tq-machine.inc

MACHINE_FEATURES += "\
    bluetooth \
    screen \
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

# recipe imx-atf-tq: boot / debug UART.
# Settings for UART1 (default on MBa91xxCA)
ATF_IMX_BOOT_UART_BASE ?= "0x44380000"

IMXBOOT_TARGETS ?= "\
    flash_singleboot \
    flash_singleboot_flexspi \
"

MACHINE_EXTRA_RRECOMMENDS += "\
    linux-firmware-pcie8997 \
    linux-firmware-sd8997 \
    linux-firmware-usb8997 \
"
