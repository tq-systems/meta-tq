# Common definitions for TQ-Systems GmbH TQMa335x modules on MBA335x board

KERNEL_DEVICETREE = "\
    am335x-mba335x.dtb \
    am335x-mba335x-lvds-g104x1.dtb \
    am335x-mba335x-glyn-etm0700.dtb \
    am335x-mba335x-dmb-ct44.dtb \
"

MACHINE_FEATURES_append = "\
    display \
"

MACHINE_EXTRA_RRECOMMENDS += "${@bb.utils.contains('BBFILE_COLLECTIONS', 'meta-ti', 'ti-sgx-ddk-km', '', d)}"

SERIAL_CONSOLES ?= "115200;ttyS4"

# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"

WKS_FILE = "tqmaxx-ti-sdimg.wks"
