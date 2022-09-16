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

KERNEL_DEVICETREE:append = " ${@kernel_provider_dtbs(d)} "

# install also under /boot inside the rootfs, needed for UBIFS use case
# and because extra FAT boot partition on SD / e-MMC shall be removed
IMAGE_INSTALL:append = "\
    kernel-devicetree \
    kernel-image \
"

# Files also in IMAGE_BOOT_FILES for backward compatibility
IMAGE_BOOT_FILES = "\
    ${KERNEL_IMAGETYPE};linuximage \
    ${KERNEL_DEVICETREE} \
"