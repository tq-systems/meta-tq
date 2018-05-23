# Common definitions for TQ-Group TQMa57xx modules on MBa57xx

KERNEL_DEVICETREE ?= " \
                        tqma572x-mba57xx.dtb \
			tqma571x-mba57xx.dtb \
"

# Files are needed in IMAGE_BOOT_FILES because /boot is on an extra partition.
# If those files should be installed under /boot inside the rootfs, you can
# achieve this in your BSP layer adding them via
# IMAGE_INSTALL_append = " kernel-devicetree kernel-image-zimage"
#
IMAGE_BOOT_FILES ?= "\
	${KERNEL_IMAGETYPE} \
	${KERNEL_IMAGETYPE}-tqma572x-mba57xx.dtb;tqma572x-mba57xx.dtb \
	${KERNEL_IMAGETYPE}-tqma571x-mba57xx.dtb;tqma571x-mba57xx.dtb \
	MLO u-boot.img"
SERIAL_CONSOLES ?= "115200;ttyO2"
