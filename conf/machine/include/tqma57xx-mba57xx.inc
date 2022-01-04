# Common definitions for TQ-Systems GmbH TQMa57xx modules on MBa57xx

KERNEL_DEVICETREE ?= " \
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

# Files are needed in IMAGE_BOOT_FILES because /boot is on an extra partition.
# If those files should be installed under /boot inside the rootfs, you can
# achieve this in your BSP layer adding them via
# IMAGE_INSTALL_append = " kernel-devicetree kernel-image-zimage"
#
IMAGE_BOOT_FILES_append = "\
    ${KERNEL_IMAGETYPE};linuximage \
    ${KERNEL_DEVICETREE} \
"

SERIAL_CONSOLES ?= "115200;ttyS2"

WKS_FILE ?= "tqma57xx_tqma335x-sdimg.wks"
