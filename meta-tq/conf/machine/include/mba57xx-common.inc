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
    screen \
    touchscreen \
"

SERIAL_CONSOLES ?= "115200;ttyS2"

# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"

# Add packages we want to be deployed, even when they are not part of rootfs
# We need these here to support UBI / NFS boot case
EXTRA_IMAGEDEPENDS += "\
    virtual/bootloader \
"

WKS_FILE = "tqmaxx-ti-sdimg.wks.in"

# EXTRA_IMAGEDEPENDS are applied to do_image_complete only. do_image_wic may run
# before and needs those packages as well
WKS_FILE_DEPENDS += "${EXTRA_IMAGEDEPENDS}"

WIC_BOOTPART_SIZE = "32M"
