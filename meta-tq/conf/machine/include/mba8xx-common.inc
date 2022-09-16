# shared definitions for MBa8Xx mainboard

# We have to disable SERIAL_CONSOLE due to auto-serial-console
SERIAL_CONSOLES = "115200;ttyLP1"

# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"

MACHINE_FEATURES += "\
    bluetooth \
    display \
    wifi \
"

MACHINE_EXTRA_RRECOMMENDS += "\
    linux-firmware-ath10k \
    linux-firmware-qca \
"

# TODO: handle optee as soon as supported
IMXBOOT_TARGETS ?= "flash_spl flash_spl_flexspi flash_linux_m4"