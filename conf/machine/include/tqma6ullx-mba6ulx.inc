
SERIAL_CONSOLES = "115200;ttymxc0"

KERNEL_DEVICETREE = " \
	imx6ull-mba6ulx.dtb \
	imx6ull-mba6ulx-lvds.dtb \
	imx6ull-mba6ulx-dmb-ct21.dtb \
	imx6ull-mba6ulx-dmb-ct44.dtb \
	imx6ull-mba6ulx-glyn-etm0700g0edh6.dtb \
"

IMAGE_BOOT_FILES = "\
	${KERNEL_IMAGETYPE};linuximage \
	${KERNEL_DEVICETREE} \
"

WKS_FILE = "tqmaxx-sdimg.wks"
