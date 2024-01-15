# Common definitions for TQ-Systems GmbH TQMa335x modules on MBA335x board

KERNEL_DEVICETREE = "\
    am335x-mba335x.dtb \
    am335x-mba335x-dmb-ct44.dtb \
    am335x-mba335x-glyn-etm0700.dtb \
    am335x-mba335x-lvds-g104x1.dtb \
"

# install also under /boot inside the rootfs, needed for UBIFS use case
# and because kernel / DTB in extra FAT boot partition on SD / e-MMC shall be
# removed in future
IMAGE_INSTALL:append = "\
    kernel-devicetree \
    kernel-image \
"

MACHINE_FEATURES:append = "\
    screen \
    touchscreen \
"

SERIAL_CONSOLES ?= "115200;ttyS4"

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

WIC_BOOTPART_SIZE = "4M"
