# shared definitions for MBa8x mainboard

# We have to disable SERIAL_CONSOLE due to auto-serial-console
SERIAL_CONSOLES = "115200;ttyLP0"

# we do not want to have getty running on tty0 as we run
# auto-serial-console there
USE_VT = "0"

BOARD_DEVICETREE_FILES = "\
	fsl-imx8qm-tqma8qm-mba8x.dtb \
	fsl-imx8qm-tqma8qm-mba8x-lvds0-tm070jvhg33.dtb \
	fsl-imx8qm-tqma8qm-mba8x-lvds1-tm070jvhg33.dtb \
"

require conf/machine/include/tqma8-common.inc

# Files are needed in IMAGE_BOOT_FILES because /boot is on an extra partition.
# If those files should be installed under /boot inside the rootfs, you can
# achieve this in your BSP layer adding them via
# IMAGE_INSTALL_append = " kernel-devicetree kernel-image-zimage"
#
IMAGE_BOOT_FILES ?= "\
	${KERNEL_IMAGETYPE} \
	${BOARD_DEVICETREE_FILES} \
	"
