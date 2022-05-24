# Common definitions for TQ-Systems GmbH MBa7x baseboard for TQMa7x modules

include conf/machine/include/tqma7.inc

SERIAL_CONSOLES = "115200;ttymxc5"

# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"

KERNEL_DEVICETREE = "\
    imx7d-mba7.dtb \
    imx7d-mba7-lvds.dtb \
    imx7s-mba7.dtb \
    imx7s-mba7-lvds.dtb \
"

KERNEL_DEVICETREE_linux-imx-tq = "\
    imx7d-mba7-dmb-ct21.dtb \
    imx7d-mba7-dmb-ct44.dtb \
    imx7d-mba7-glyn-etm0700g0edh6.dtb \
    imx7s-mba7-dmb-ct21.dtb \
    imx7s-mba7-dmb-ct44.dtb \
    imx7s-mba7-glyn-etm0700g0edh6.dtb \
"

KERNEL_DEVICETREE_linux-tq = "\
    imx7d-mba7-cdtech-fc21.dtb \
    imx7d-mba7-cdtech-dc44.dtb \
    imx7s-mba7-cdtech-fc21.dtb \
    imx7s-mba7-cdtech-dc44.dtb \
"

KERNEL_DEVICETREE:append = " ${@kernel_provider_dtbs(d)} "

# install also under /boot inside the rootfs, needed for UBIFS use case
# and because extra FAT boot partition on SD / e-MMC shall be removed
IMAGE_INSTALL:append = "\
    kernel-devicetree \
    kernel-image \
"

# Files are needed in IMAGE_BOOT_FILES because we use an extra partition for
# backward compatibility.
IMAGE_BOOT_FILES = "\
    ${KERNEL_IMAGETYPE};linuximage \
    ${KERNEL_DEVICETREE} \
"

WKS_FILE = "tqmaxx-sdimg.wks"
