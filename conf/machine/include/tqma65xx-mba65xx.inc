# Common definitions for TQ-Group TQMa65xx modules on MBa65xx
# WKS_FILE ?= "tqma65xx-sdimg.wks"


KERNEL_DEVICETREE ?= " \
    tq/tqma654-base-board.dtb tq/tqma654-gp.dtbo \
    tq/tqma654-evm-hdmi.dtbo tq/tqma654-idk.dtbo \
    tq/tqma654-evm-oldi-lcd1evm.dtbo tq/tqma654-evm-tc358876.dtbo \
    tq/tqma654-pcie-usb2.dtbo tq/tqma654-pcie-usb3.dtbo \
    tq/tqma654-base-board-jailhouse.dtbo \
    tq/tqma654-evm-prupwm.dtbo \
"

# Files are needed in IMAGE_BOOT_FILES because /boot is on an extra partition.
# If those files should be installed under /boot inside the rootfs, you can
# achieve this in your BSP layer adding them via
# IMAGE_INSTALL_append = " kernel-devicetree kernel-image-image"
#
IMAGE_BOOT_FILES_append = "\
	${KERNEL_IMAGETYPE};linuximage \
	${KERNEL_DEVICETREE} \
"

SERIAL_CONSOLES ?= "115200;ttyS2"
