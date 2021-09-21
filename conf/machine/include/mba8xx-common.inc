# shared definitions for MBa8Xx mainboard

# We have to disable SERIAL_CONSOLE due to auto-serial-console
SERIAL_CONSOLES = "115200;ttyLP1"

# we do not want to have getty running on tty1 as we run
# auto-serial-console there
USE_VT = "0"

MACHINE_FEATURES_append = " wifi"
MACHINE_EXTRA_RRECOMMENDS_append = " linux-firmware-ath10k"