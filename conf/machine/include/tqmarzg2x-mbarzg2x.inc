# Common definitions for TQ-Systems GmbH TQMaRZG2x modules on MBaRZG2x

SERIAL_CONSOLE = "115200 ttySC0"

BOOTTYPE ?= "emmc"

WKS_FILE ?= "tqmarzg2x-sdimg.wks"

EXTRA_IMAGEDEPENDS_remove = "optee-os"
