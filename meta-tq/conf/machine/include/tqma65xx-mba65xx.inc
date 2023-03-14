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

SERIAL_CONSOLES = "115200;ttyS2"

# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"

# Add packages we want to be deployed, even when they are not part of rootfs
# We need these here to support UBI / NFS boot case
EXTRA_IMAGEDEPENDS += "\
    virtual/bootloader \
"

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

# EXTRA_IMAGEDEPENDS are applied to do_image_complete only. do_image_wic may run
# before and needs those packages as well
WKS_FILE_DEPENDS += "${EXTRA_IMAGEDEPENDS}"

MACHINE_FEATURES += "\
    screen \
    touchscreen \
"

WKS_FILE = "tqmaxx-ti-sdimg.wks.in"

WIC_BOOTPART_SIZE = "32M"
