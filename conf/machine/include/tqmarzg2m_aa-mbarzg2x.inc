# Common definitions for TQ-Group TQMaRZG2M_AA module on MBaRZG2x

# Device tree for RZG2M
KERNEL_DEVICETREE = " \
	renesas/r8a774a1-tqmarzg2m_aa-mbarzg2x.dtb \
	renesas/r8a774a1-tqmarzg2m_aa-mbarzg2x-lvds-tm070jvhg33.dtb \
"

# RZG2M u-boot configure
UBOOT_CONFIG ??= "tqmarzg2m_aa-mbarzg2x"
UBOOT_CONFIG[tqmarzg2m_aa-mbarzg2x] = "tqmarzg2m_aa-mbarzg2x_defconfig"

# Files are needed in IMAGE_BOOT_FILES because /boot is on an extra partition.
# If those files should be installed under /boot inside the rootfs, you can
# achieve this in your BSP layer adding them via
# IMAGE_INSTALL_append = " kernel-devicetree kernel-image-zimage"
#
IMAGE_BOOT_FILES ?= "\
	${KERNEL_IMAGETYPE} \
	r8a774a1-tqmarzg2m_aa-mbarzg2x.dtb \
	r8a774a1-tqmarzg2m_aa-mbarzg2x-lvds-tm070jvhg33.dtb \
"
