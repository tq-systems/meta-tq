# Common definitions for TQ-Systems GmbH MBa6ULx baseboard for
# TQMa6ULx/TQMa6ULLx/TQMa6ULxL/TQMa6ULLxL SOM

require conf/machine/include/tq-machine.inc

SERIAL_CONSOLES = "115200;ttymxc0"

# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"

MACHINE_FEATURES += "\
    bluetooth \
    screen \
    wifi \
"

MACHINE_EXTRA_RRECOMMENDS += "${@bb.utils.contains('COMBINED_FEATURES', 'wifi', 'linux-firmware-ath10k', '', d)}"
MACHINE_EXTRA_RRECOMMENDS += "${@bb.utils.contains('COMBINED_FEATURES', 'bluetooth', 'linux-firmware-qca', '', d)}"
