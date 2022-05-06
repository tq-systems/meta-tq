# shared definitions for MBa8Xx mainboard

# We have to disable SERIAL_CONSOLE due to auto-serial-console
SERIAL_CONSOLES = "115200;ttyLP1"

# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"

MACHINE_FEATURES:append = " wifi"
MACHINE_EXTRA_RRECOMMENDS:append = " linux-firmware-ath10k"

# TODO: handle optee as soon as supported
IMXBOOT_TARGETS ?= "flash_spl flash_spl_flexspi flash_linux_m4"
