# shared definitions for MB-SMARC-2 mainboard

# We have to disable SERIAL_CONSOLE due to auto-serial-console
SERIAL_CONSOLES = "115200;ttyLP0"

# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"

# TODO: handle optee as soon as supported
IMXBOOT_TARGETS ?= "\
    flash_linux_m4 \
    flash_spl \
    flash_spl_flexspi \
"

#
# bootstream name, construction see imx-boot recipe, do_deploy
#
MACHINE_WKS_BOOTSTREAM ?= "imx-boot-${MACHINE}-sd.bin-flash_spl"

MACHINE_FEATURES += "\
    bluetooth \
    display \
    wifi \
"

MACHINE_EXTRA_RRECOMMENDS += "\
    linux-firmware-ath10k \
    linux-firmware-qca \
"
