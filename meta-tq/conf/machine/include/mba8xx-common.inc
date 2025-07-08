# shared definitions for MBa8Xx mainboard
require conf/machine/include/tq-machine.inc

# We have to disable SERIAL_CONSOLE due to auto-serial-console
SERIAL_CONSOLES = "115200;ttyLP1"

# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"

MACHINE_FEATURES += "\
    bluetooth \
    screen \
    wifi \
"

MACHINE_EXTRA_RRECOMMENDS += "${@bb.utils.contains('COMBINED_FEATURES', 'wifi', 'linux-firmware-ath10k', '', d)}"
MACHINE_EXTRA_RRECOMMENDS += "${@bb.utils.contains('COMBINED_FEATURES', 'bluetooth', 'linux-firmware-qca', '', d)}"

# TODO: handle optee as soon as supported
IMXBOOT_TARGETS ?= "\
    flash_linux_m4 \
    flash_spl \
    flash_spl_flexspi \
"
