# Common definitions for TQ-Group TQMa65xx modules on MBa65xx
# WKS_FILE ?= "tqma65xx-sdimg.wks"


KERNEL_DEVICETREE ?= " \
    tq/am654-mba65xx.dtb \
    tq/am654-mba65xx-interfaces.dtbo \
    tq/am654-mba65xx-jailhouse.dtbo \
"

# Files are needed in IMAGE_BOOT_FILES because /boot is on an extra partition.
# If those files should be installed under /boot inside the rootfs, you can
# achieve this in your BSP layer adding them via
# IMAGE_INSTALL_append = " kernel-devicetree kernel-image-image"
#
IMAGE_BOOT_FILES_append = "\
	${KERNEL_IMAGETYPE};linuximage \
	${KERNEL_DEVICETREE} \
"

SERIAL_CONSOLES ?= "115200;ttyS2"
