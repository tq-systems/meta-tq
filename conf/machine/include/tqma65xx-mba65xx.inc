# Common definitions for TQ-Group TQMa65xx modules on MBa65xx

KERNEL_DEVICETREE = " \
    ti/k3-am654-tqma65xx-mba65xx.dtb \
    ti/k3-am65-tqma65xx-mba65xx-eth-prg0.dtbo \
    ti/k3-am65-tqma65xx-mba65xx-eth-prg1.dtbo \
    ti/k3-am65-tqma65xx-mba65xx-audio.dtbo \
    ti/k3-am65-tqma65xx-mba65xx-lvds-display.dtbo \
    ti/k3-am65-tqma65xx-mba65xx-dmb-dc44.dtbo \
    ti/k3-am65-tqma65xx-mba65xx-dmb-fc21.dtbo \
"

SERIAL_CONSOLES ?= "115200;ttyS2"

# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

WKS_FILE_DEPENDS = "\
    mtools-native \
    dosfstools-native \
    parted-native \
    virtual/bootloader \
"

WKS_FILE = "tqmaxx-ti-sdimg.wks.in"

WIC_BOOTPART_SIZE = "32M"
