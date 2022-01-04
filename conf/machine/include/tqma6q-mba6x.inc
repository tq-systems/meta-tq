# Common definitions for TQ-Systems GmbH TQMa6[Q,D] modules on MBa6

KERNEL_DEVICETREE = " \
	imx6q-mba6a.dtb \
	imx6q-mba6a-duallvds-tm070jvhg33.dtb \
	imx6q-mba6a-lvds-tm070jvhg33.dtb \
	imx6q-mba6a-hdmi.dtb \
	imx6q-mba6a-usb-peripheral.dtb \
	\
	imx6q-mba6b.dtb \
	imx6q-mba6b-duallvds-tm070jvhg33.dtb \
	imx6q-mba6b-lvds-tm070jvhg33.dtb \
	imx6q-mba6b-hdmi.dtb \
	imx6q-mba6b-usb-peripheral.dtb \
"

KERNEL_DEVICETREE_append_use-mainline-bsp = " \
	imx6q-mba6a-dmb-ct21.dtb \
	imx6q-mba6a-dmb-ct44.dtb \
	imx6q-mba6a-glyn-etm0700g0edh6.dtb \
	imx6q-mba6a-multi.dtb \
	\
	imx6q-mba6b-dmb-ct21.dtb \
	imx6q-mba6b-dmb-ct44.dtb \
	imx6q-mba6b-glyn-etm0700g0edh6.dtb \
	imx6q-mba6b-multi.dtb \
"

# Files are needed in IMAGE_BOOT_FILES because /boot is on an extra partition.
# If those files should be installed under /boot inside the rootfs, you can
# achieve this in your BSP layer adding them via
# IMAGE_INSTALL_append = " kernel-devicetree kernel-image-zimage"
#
IMAGE_BOOT_FILES = "\
	${KERNEL_IMAGETYPE};linuximage \
	${KERNEL_DEVICETREE} \
"