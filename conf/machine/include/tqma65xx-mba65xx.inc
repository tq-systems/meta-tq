# Common definitions for TQ-Group TQMa65xx modules on MBa65xx
# WKS_FILE ?= "tqma65xx-sdimg.wks"


KERNEL_DEVICETREE ?= " \
    tq/am654-mba65xx-p1.dtb \
    tq/am654-mba65xx-p2.dtb \
    tq/am654-mba65xx-eth-prg0.dtbo \
    tq/am654-mba65xx-eth-prg1.dtbo \
    tq/am654-mba65xx-audio.dtbo \
    tq/am654-mba65xx-lvds-display.dtbo \
    tq/am654-mba65xx-dmb-dc44.dtbo \
    tq/am654-mba65xx-dmb-fc21.dtbo \
"

# Files are needed in IMAGE_BOOT_FILES because /boot is on an extra partition.
# If those files should be installed under /boot inside the rootfs, you can
# achieve this in your BSP layer adding them via
# IMAGE_INSTALL_append = " kernel-devicetree kernel-image-image"
#
IMAGE_BOOT_FILES_append = "\
	${KERNEL_IMAGETYPE};linuximage \
	${KERNEL_DEVICETREE} \
	${KERNEL_IMAGETYPE}-initramfs-${MACHINE}.bin \
"

SERIAL_CONSOLES ?= "115200;ttyS2"
