# shared definitions for MB-SMARC-2 mainboard

# We have to disable SERIAL_CONSOLE due to auto-serial-console
SERIAL_CONSOLES = "115200;ttyLP0"

# we do not want to have getty running on tty1 as we run
# auto-serial-console there
USE_VT = "0"

# TODO: handle optee as soon as supported
IMXBOOT_TARGETS ?= "flash_spl flash_spl_flexspi flash_linux_m4"

MACHINE_FEATURES_append = " wifi"
MACHINE_EXTRA_RRECOMMENDS_append = " linux-firmware-ath10k"
