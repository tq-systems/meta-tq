# Common definitions for TQ-Systems GmbH TQMa6[S,DL] modules on MBa6x

# Common device trees used in linux-imx-qt and linux-tq
KERNEL_DEVICETREE = "\
    imx6dl-mba6a.dtb \
    imx6dl-mba6a-duallvds-tm070jvhg33.dtb \
    imx6dl-mba6a-hdmi.dtb \
    imx6dl-mba6a-lvds-tm070jvhg33.dtb \
        \
    imx6dl-mba6b.dtb \
    imx6dl-mba6b-duallvds-tm070jvhg33.dtb \
    imx6dl-mba6b-hdmi.dtb \
    imx6dl-mba6b-lvds-tm070jvhg33.dtb \
"

KERNEL_DEVICETREE_linux-imx-tq = "\
    imx6dl-mba6a-usb-peripheral.dtb \
    imx6dl-mba6b-usb-peripheral.dtb \
"

KERNEL_DEVICETREE_linux-tq = "\
    imx6dl-mba6a-cdtech-dc44.dtb \
    imx6dl-mba6a-cdtech-fc21.dtb \
    imx6dl-mba6a-multi.dtb \
    \
    imx6dl-mba6b-cdtech-dc44.dtb \
    imx6dl-mba6b-cdtech-fc21.dtb \
    imx6dl-mba6b-multi.dtb \
"

KERNEL_DEVICETREE_append = " ${@kernel_provider_dtbs(d)} "

# Files are needed in IMAGE_BOOT_FILES because /boot is on an extra partition.
# If those files should be installed under /boot inside the rootfs, you can
# achieve this in your BSP layer adding them via
# IMAGE_INSTALL_append = " kernel-devicetree kernel-image-zimage"
#
IMAGE_BOOT_FILES = "\
    ${KERNEL_IMAGETYPE};linuximage \
    ${KERNEL_DEVICETREE} \
"
