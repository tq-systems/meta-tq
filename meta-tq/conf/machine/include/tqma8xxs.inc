# Common definitions for TQ-Systems GmbH TQMa8XxS modules (C0 CPU step)

MACHINEOVERRIDES =. "tqma8xxs:"

require conf/machine/include/tqma8xx-common.inc

SC_FIRMWARE_NAME = "mx8qx-tqma8xqps-mb-smarc-2-scfw-tcm.bin"

ATF_IMX_DEBUG_UART ?= "1"

IMXBOOT_TARGETS ?= "\
    flash_linux_m4 \
    flash_spl \
    flash_spl_flexspi \
"

MACHINE_WKS_BOOTSTREAM ?= "imx-boot-${MACHINE}-sd.bin-flash_spl"

# We have to disable SERIAL_CONSOLE due to auto-serial-console
SERIAL_CONSOLES = "115200;ttyLP0"
