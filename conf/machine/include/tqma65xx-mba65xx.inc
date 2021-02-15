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
	${KERNEL_IMAGETYPE}-initramfs-${MACHINE}.bin \
	am654-mba65xx-p1.dtb \
	am654-mba65xx-p2.dtb \
	am654-mba65xx-eth-prg0.dtbo \
	am654-mba65xx-eth-prg1.dtbo \
	am654-mba65xx-audio.dtbo \
	am654-mba65xx-lvds-display.dtbo \
	am654-mba65xx-dmb-dc44.dtbo \
	am654-mba65xx-dmb-fc21.dtbo \
"

SERIAL_CONSOLES ?= "115200;ttyS2"

IMAGE_FSTYPES ?= "tar.gz ext4 wic"

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

WKS_FILE_DEPENDS = "\
	mtools-native \
	dosfstools-native \
	parted-native \
	virtual/kernel \
	virtual/bootloader \
	"

WKS_FILE ?= "tqma57xx-sdimg.wks"
