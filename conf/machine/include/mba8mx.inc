# Common definitions for TQ-Group MBa8Mx baseboard for TQMa8Mx modules

# Set u-boot DTB
UBOOT_DTB_NAME = "fsl-imx8mq-mba8mx.dtb"

BOARD_DEVICETREE_FILES = "\
    imx8mq-mba8mx.dtb \
    imx8mq-mba8mx-dcss-lvds-tm070jvhg33.dtb \
    imx8mq-mba8mx-lcdif-lvds-tm070jvhg33.dtb \
"

####
#	imx8mq-mba8mx-hdmi.dtb \
#	imx8mq-mba8mx-dcss-lvds-g150xne-l01.dtb \
#	imx8mq-mba8mx-dcss-lvds-g150xne-l01-18bit.dtb \
#	imx8mq-mba8mx-mikrobus-rtc5.dtb \
###


KERNEL_DEVICETREE = ""

require conf/machine/include/tqma8-common.inc

# Set Serial console
SERIAL_CONSOLES = "115200;ttymxc2"

# Files are needed in IMAGE_BOOT_FILES because /boot is on an extra partition.
# If those files should be installed under /boot inside the rootfs, you can
# achieve this in your BSP layer adding them via
# IMAGE_INSTALL_append = " kernel-devicetree kernel-image-zimage"
#
IMAGE_BOOT_FILES ?= "\
	${KERNEL_IMAGETYPE} \
	${BOARD_DEVICETREE_FILES} \
	"
