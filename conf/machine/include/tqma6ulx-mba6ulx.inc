# Common definitions for TQ-Systems GmbH MBa6ULx baseboard for TQMa6ULx SOM

SERIAL_CONSOLES = "115200;ttymxc0"

# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"

KERNEL_DEVICETREE_linux-imx-tq = "\
    imx6ul-mba6ulx.dtb \
    imx6ul-mba6ulx-g1.dtb \
    imx6ul-mba6ulx-lvds.dtb \
    imx6ul-mba6ulx-dmb-ct21.dtb \
    imx6ul-mba6ulx-dmb-ct44.dtb \
    imx6ul-mba6ulx-glyn-etm0700g0edh6.dtb \
"
KERNEL_DEVICETREE_linux-tq = "\
    imx6ul-tqma6ul1-mba6ulx.dtb \
    imx6ul-tqma6ul2-mba6ulx.dtb \
    imx6ul-tqma6ul2-mba6ulx-cdtech-dc44.dtb \
    imx6ul-tqma6ul2-mba6ulx-cdtech-fc21.dtb \
    imx6ul-tqma6ul2-mba6ulx-lvds.dtb \
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

WKS_FILE = "tqmaxx-sdimg.wks"

MACHINE_FEATURES_append = "\
    display \
    wifi \
"

MACHINE_EXTRA_RRECOMMENDS += "linux-firmware-ath10k"
