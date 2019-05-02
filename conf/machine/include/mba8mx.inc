# Common definitions for TQ-Group MBa8Mx baseboard for TQMa8Mx modules

KERNEL_DEVICETREE = "\
	freescale/fsl-imx8mq-tqma8mq-mba8mx.dtb \
	freescale/fsl-imx8mq-tqma8mq-mba8mx-hdmi.dtb \
	freescale/fsl-imx8mq-tqma8mq-mba8mx-sd-hs104.dtb \
	freescale/fsl-imx8mq-tqma8mq-mba8mx-dcss-lvds-tm070jvhg33.dtb \
	freescale/fsl-imx8mq-tqma8mq-mba8mx-dcss-lvds-tm070jvhg33-en_gpio.dtb \
	freescale/fsl-imx8mq-tqma8mq-mba8mx-lcdif-lvds-tm070jvhg33.dtb \
	freescale/fsl-imx8mq-tqma8mq-mba8mx-lcdif-lvds-tm070jvhg33-en_gpio.dtb \
"

# Set Serial console
SERIAL_CONSOLES = "115200;ttymxc0"

# Files are needed in IMAGE_BOOT_FILES because /boot is on an extra partition.
# If those files should be installed under /boot inside the rootfs, you can
# achieve this in your BSP layer adding them via
# IMAGE_INSTALL_append = " kernel-devicetree kernel-image-zimage"
#
IMAGE_BOOT_FILES ?= "\
	${KERNEL_IMAGETYPE} \
	${KERNEL_IMAGETYPE}-fsl-imx8mq-tqma8mq-mba8mx.dtb;fsl-imx8mq-tqma8mq-mba8mx.dtb \
	${KERNEL_IMAGETYPE}-fsl-imx8mq-tqma8mq-mba8mx-hdmi.dtb;fsl-imx8mq-tqma8mq-mba8mx-hdmi.dtb \
	${KERNEL_IMAGETYPE}-fsl-imx8mq-tqma8mq-mba8mx-sd-hs104.dtb;fsl-imx8mq-tqma8mq-mba8mx-sd-hs104.dtb \
	${KERNEL_IMAGETYPE}-fsl-imx8mq-tqma8mq-mba8mx-dcss-lvds-tm070jvhg33.dtb;fsl-imx8mq-tqma8mq-mba8mx-dcss-lvds-tm070jvhg33.dtb \
	${KERNEL_IMAGETYPE}-fsl-imx8mq-tqma8mq-mba8mx-dcss-lvds-tm070jvhg33-en_gpio.dtb;fsl-imx8mq-tqma8mq-mba8mx-dcss-lvds-tm070jvhg33-en_gpio.dtb \
	${KERNEL_IMAGETYPE}-fsl-imx8mq-tqma8mq-mba8mx-lcdif-lvds-tm070jvhg33.dtb;fsl-imx8mq-tqma8mq-mba8mx-lcdif-lvds-tm070jvhg33.dtb \
	${KERNEL_IMAGETYPE}-fsl-imx8mq-tqma8mq-mba8mx-lcdif-lvds-tm070jvhg33-en_gpio.dtb;fsl-imx8mq-tqma8mq-mba8mx-lcdif-lvds-tm070jvhg33-en_gpio.dtb \
	"

