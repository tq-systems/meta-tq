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
    display \
    screen \
    touchscreen \
"

SERIAL_CONSOLES ?= "115200;ttyS4"

# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

WKS_FILE_DEPENDS += "\
    dosfstools-native \
    e2fsprogs-native \
    mtools-native \
    parted-native \
"

EXTRA_IMAGEDEPENDS += "\
    virtual/bootloader \
"

WKS_FILE = "tqmaxx-ti-sdimg.wks.in"

WIC_BOOTPART_SIZE = "4M"
