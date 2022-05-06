# shared definitions for MB-SMARC-2 mainboard

# We have to disable SERIAL_CONSOLE due to auto-serial-console
SERIAL_CONSOLES = "115200;ttyLP0"

# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"

# TODO: handle optee as soon as supported
IMXBOOT_TARGETS ?= "flash_spl flash_spl_flexspi flash_linux_m4"

MACHINE_FEATURES:append = " wifi"
MACHINE_EXTRA_RRECOMMENDS:append = " linux-firmware-ath10k"
