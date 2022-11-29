# Common definitions for TQ-Systems GmbH TQMa57xx modules on MBa57xx

KERNEL_DEVICETREE ?= "\
    am571x-mba57xx.dtb \
    am572x-mba57xx.dtb \
    am574x-mba57xx.dtb \
"

MACHINE_EXTRA_RRECOMMENDS += "\
    kernel-devicetree \
    kernel-modules \
"

MACHINE_FEATURES:append = "\
    display \
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
