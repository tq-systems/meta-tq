# Common definitions for TQ-Group TQMa57xx modules on MBa57xx

KERNEL_DEVICETREE ?= " \
	am572x-mba57xx.dtb \
	am572x-mba57xx-lcd.dtb \
	am572x-mba57xx-lvds-tm070jvhg33.dtb \
	am571x-mba57xx.dtb \
	am571x-mba57xx-lcd.dtb \
	am571x-mba57xx-lvds-tm070jvhg33.dtb \
	am574x-mba57xx.dtb \
	am574x-mba57xx-lcd.dtb \
	am574x-mba57xx-lvds-tm070jvhg33.dtb \
"

# Files are needed in IMAGE_BOOT_FILES because /boot is on an extra partition.
# If those files should be installed under /boot inside the rootfs, you can
# achieve this in your BSP layer adding them via
# IMAGE_INSTALL_append = " kernel-devicetree kernel-image-zimage"
#
IMAGE_BOOT_FILES_append = "\
	${KERNEL_IMAGETYPE} \
	${KERNEL_DEVICETREE} \
	MLO \
	u-boot.img \
"

SERIAL_CONSOLES ?= "115200;ttyS2"

WKS_FILE ?= "tqma57xx-sdimg.wks"
