# Common definitions for TQ-Systems GmbH TQMa6x modules on MBa6x

SERIAL_CONSOLES = "115200;ttymxc1"

# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"

UBOOT_CONFIG ??= "sd spinor"

WKS_FILE = "tqmaxx-sdimg.wks"

MACHINE_FEATURES_append = " wifi"
MACHINE_EXTRA_RRECOMMENDS += "linux-firmware-ath10k"
