# Common definitions for TQ-Systems GmbH TQMa335x modules on MBA335x board

require conf/machine/include/tq-machine.inc

KERNEL_DEVICETREE = "\
    ti/omap/am335x-mba335x.dtb \
    ti/omap/am335x-mba335x-dmb-ct44.dtb \
    ti/omap/am335x-mba335x-glyn-etm0700.dtb \
"

MACHINE_FEATURES:append = "\
    screen \
"

SERIAL_CONSOLES ?= "115200;ttyS4"

# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"
