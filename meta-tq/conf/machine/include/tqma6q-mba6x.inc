# Common definitions for TQ-Systems GmbH TQMa6[Q,D] modules on MBa6x

# Common device trees used in linux-imx-qt and linux-tq
KERNEL_DEVICETREE = "\
    imx6q-mba6a.dtb \
    imx6q-mba6a-duallvds-tm070jvhg33.dtb \
    imx6q-mba6a-hdmi.dtb \
    imx6q-mba6a-lvds-tm070jvhg33.dtb \
    \
    imx6q-mba6b.dtb \
    imx6q-mba6b-duallvds-tm070jvhg33.dtb \
    imx6q-mba6b-hdmi.dtb \
    imx6q-mba6b-lvds-tm070jvhg33.dtb \
"

KERNEL_DEVICETREE_linux-tq = "\
    imx6q-mba6a-cdtech-dc44.dtb \
    imx6q-mba6a-cdtech-fc21.dtb \
    imx6q-mba6a-multi.dtb \
    \
    imx6q-mba6b-cdtech-dc44.dtb \
    imx6q-mba6b-cdtech-fc21.dtb \
    imx6q-mba6b-multi.dtb \
"

KERNEL_DEVICETREE += "${@kernel_provider_dtbs(d)}"

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
