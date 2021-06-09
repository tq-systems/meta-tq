# Common definitions for TQ-Group TQMa335x modules on MBA335x board

KERNEL_DEVICETREE = " \
	am335x-mba335x.dtb \
	am335x-mba335x-lvds-g104x1.dtb \
	am335x-mba335x-glyn-etm0700.dtb \
	am335x-mba335x-dmb-ct44.dtb \
"

# Files are needed in IMAGE_BOOT_FILES because /boot is on an extra partition.
# If those files should be installed under /boot inside the rootfs, you can
# achieve this in your BSP layer adding them via
# IMAGE_INSTALL_append = " kernel-devicetree kernel-image-zimage"
#
IMAGE_BOOT_FILES_append = "\
	${KERNEL_IMAGETYPE};linuximage \
	${KERNEL_DEVICETREE} \
"

MACHINE_EXTRA_RRECOMMENDS += "${@bb.utils.contains('BBFILE_COLLECTIONS', 'meta-ti', 'ti-sgx-ddk-km', '', d)}"

IMAGE_FSTYPES_append = " tar.gz ext4 wic"

SERIAL_CONSOLES ?= "115200;ttyS4"

WKS_FILE = "tqma57xx_tqma335x-sdimg.wks"
