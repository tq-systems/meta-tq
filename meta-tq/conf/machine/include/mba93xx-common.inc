# Common definitions for TQ-Systems GmbH MBa93xxCA / MBa93xxLA

# Machine specific options
require conf/machine/include/tq-machine.inc

python() {
    wks_type = ""

    if bb.utils.contains("DISTRO_FEATURES", "secure", True, False, d):
        wks_type = "-verity"
    if bb.utils.contains("DISTRO_FEATURES", "rauc", True, False, d):
        if not bb.utils.contains("DISTRO_FEATURES", "secure", True, False, d):
            wks_type = "-rauc-ab"
        else:
            bb.warn("DISTRO_FEATURES contains 'rauc' and 'secure', using WKS_FILE selected by distro feature 'secure'")

    d.setVar("WKS_FILE", "tqmaxx-imx-v8-sdimg%s.wks.in" % wks_type)
}

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

# recipe imx-atf-tq: boot / debug UART.
# Settings for UART1 (default on MBa93xx[CA/LA])
ATF_IMX_BOOT_UART_BASE ?= "0x44380000"

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
