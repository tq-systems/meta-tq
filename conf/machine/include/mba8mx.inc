# Common definitions for TQ-Group MBa8Mx baseboard for TQMa8Mx modules

BOARD_DEVICETREE_FILES = "\
	fsl-imx8mq-tqma8mq-mba8mx.dtb \
	fsl-imx8mq-tqma8mq-mba8mx-hdmi.dtb \
	fsl-imx8mq-tqma8mq-mba8mx-sd-hs104.dtb \
	fsl-imx8mq-tqma8mq-mba8mx-dcss-lvds-tm070jvhg33.dtb \
	fsl-imx8mq-tqma8mq-mba8mx-dcss-lvds-tm070jvhg33-en_gpio.dtb \
	fsl-imx8mq-tqma8mq-mba8mx-lcdif-lvds-tm070jvhg33.dtb \
	fsl-imx8mq-tqma8mq-mba8mx-lcdif-lvds-tm070jvhg33-en_gpio.dtb \
	fsl-imx8mq-tqma8mq-mba8mx-dcss-lvds-g150xne-l01-en_gpio.dtb \
	fsl-imx8mq-tqma8mq-mba8mx-dcss-lvds-g150xne-l01-18bit-en_gpio.dtb \
	"

KERNEL_DEVICETREE = ""

# Set Serial console
SERIAL_CONSOLES = "115200;ttymxc0"

# Files are needed in IMAGE_BOOT_FILES because /boot is on an extra partition.
# If those files should be installed under /boot inside the rootfs, you can
# achieve this in your BSP layer adding them via
# IMAGE_INSTALL_append = " kernel-devicetree kernel-image-zimage"
#
IMAGE_BOOT_FILES ?= "\
	${KERNEL_IMAGETYPE} \
	${BOARD_DEVICETREE_FILES} \
	"

KERNEL_DEVICETREE_PATH_PREFIX = "freescale/"

# sets KERNEL_DEVICETREE
python () {
	dt_list = d.getVar("BOARD_DEVICETREE_FILES").split()
	dt_prefix = d.getVar("KERNEL_DEVICETREE_PATH_PREFIX")
	dt_prefix += "% s"
	dt_list =  [dt_prefix % i for i in dt_list]
	d.setVar("KERNEL_DEVICETREE", ' '.join(dt_list))
}
