# Common definitions for TQ-Group TQMa57xx modules on MBa57xx

KERNEL_DEVICETREE ?= " \
	am571x-mba57xx.dtb \
	am571x-mba57xx-dmb-ct44.dtb \
	am571x-mba57xx-glyn-etm0700g0dh6.dtb \
	am571x-mba57xx-lvds-tm070jvhg33.dtb \
	am572x-mba57xx.dtb \
	am572x-mba57xx-dmb-ct44.dtb \
	am572x-mba57xx-glyn-etm0700g0dh6.dtb \
	am572x-mba57xx-lvds-tm070jvhg33.dtb \
	am574x-mba57xx.dtb \
	am574x-mba57xx-dmb-ct44.dtb \
	am574x-mba57xx-glyn-etm0700g0dh6.dtb \
	am574x-mba57xx-lvds-tm070jvhg33.dtb \
"

IMAGE_BOOT_FILES_append = "\
	${KERNEL_IMAGETYPE};linuximage \
	${KERNEL_DEVICETREE} \
	MLO \
	u-boot.img \
"

SERIAL_CONSOLES ?= "115200;ttyS2"

WKS_FILE ?= "tqma57xx-sdimg.wks"
