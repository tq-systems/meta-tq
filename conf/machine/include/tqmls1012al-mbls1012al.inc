# Common definitions for TQ-Group TQMLS1012AL modules on MBLS1012AL

SERIAL_CONSOLES = "115200;ttyS0"
SERIAL_CONSOLES_CHECK = "${SERIAL_CONSOLES}"
# no virtual terminal needed
USE_VT = "0"

KERNEL_DEVICETREE ?= " \
  freescale/fsl-ls1012a-mbls1012al.dtb \
"

# Files are needed in IMAGE_BOOT_FILES because /boot is on an extra partition.
# If those files should be installed under /boot inside the rootfs, you can
# achieve this in your BSP layer adding them via
# IMAGE_INSTALL_append = " kernel-devicetree kernel-image"
#
IMAGE_BOOT_FILES ?= "\
	${KERNEL_IMAGETYPE} \
	fsl-ls1012a-mbls1012al.dtb \
"

UBOOT_CONFIG ?= "tfa"
UBOOT_CONFIG[tfa] = "tqmls1012al_mbls1012al_tfa_defconfig"
