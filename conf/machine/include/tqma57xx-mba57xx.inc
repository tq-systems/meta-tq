# Common definitions for TQ-Systems GmbH TQMa57xx modules on MBa57xx

KERNEL_DEVICETREE ?= "\
    am571x-mba57xx.dtb \
    am571x-mba57xx-cdtech-dc44.dtb \
    am571x-mba57xx-edt-etm0700g0edh6.dtb \
    am571x-mba57xx-tianma-tm070jvhg33.dtb \
    am572x-mba57xx.dtb \
    am572x-mba57xx-cdtech-dc44.dtb \
    am572x-mba57xx-edt-etm0700g0edh6.dtb \
    am572x-mba57xx-tianma-tm070jvhg33.dtb \
    am574x-mba57xx.dtb \
    am574x-mba57xx-cdtech-dc44.dtb \
    am574x-mba57xx-edt-etm0700g0edh6.dtb \
    am574x-mba57xx-tianma-tm070jvhg33.dtb \
"

# install also under /boot inside the rootfs, needed for UBIFS use case
# and because kernel / DTB in extra FAT boot partition on SD / e-MMC shall be
# removed in future
IMAGE_INSTALL:append = "\
    kernel-devicetree \
    kernel-image-zimage \
"

IMAGE_BOOT_FILES:append = "\
    ${KERNEL_IMAGETYPE};linuximage \
    ${KERNEL_DEVICETREE} \
"

SERIAL_CONSOLES ?= "115200;ttyS2"

# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"

WKS_FILE ?= "tqmaxx-ti-sdimg.wks"
