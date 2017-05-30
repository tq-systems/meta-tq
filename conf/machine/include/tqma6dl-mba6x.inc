# Common definitions for TQ-Group TQMa6[S,DL] modules on MBa6

KERNEL_DEVICETREE ?= " \
                        imx6dl-mba6a.dtb imx6dl-mba6a-hdmi.dtb \
                        imx6dl-mba6a-lvds.dtb imx6dl-mba6a-pd.dtb \
                        imx6dl-mba6b.dtb imx6dl-mba6b-hdmi.dtb \
                        imx6dl-mba6b-lvds.dtb  imx6dl-mba6b-otg.dtb \
                        imx6dl-mba6b-pd.dtb \
"

# Files are needed in IMAGE_BOOT_FILES because /boot is on an extra partition.
# If those files should be installed under /boot inside the rootfs, you can
# achieve this in your BSP layer adding them via
# IMAGE_INSTALL_append = " kernel-devicetree kernel-image-zimage"
#
IMAGE_BOOT_FILES ?= "\
	${KERNEL_IMAGETYPE} \
	${KERNEL_IMAGETYPE}-imx6dl-mba6a.dtb;imx6dl-mba6a.dtb \
	${KERNEL_IMAGETYPE}-imx6dl-mba6a-hdmi.dtb;imx6dl-mba6a-hdmi.dtb \
	${KERNEL_IMAGETYPE}-imx6dl-mba6a-lvds.dtb;imx6dl-mba6a-lvds.dtb \
	${KERNEL_IMAGETYPE}-imx6dl-mba6a-pd.dtb;imx6dl-mba6a-pd.dtb \
	${KERNEL_IMAGETYPE}-imx6dl-mba6b.dtb;imx6dl-mba6b.dtb \
	${KERNEL_IMAGETYPE}-imx6dl-mba6b-hdmi.dtb;imx6dl-mba6b-hdmi.dtb \
	${KERNEL_IMAGETYPE}-imx6dl-mba6b-lvds.dtb;imx6dl-mba6b-lvds.dtb \
	${KERNEL_IMAGETYPE}-imx6dl-mba6b-otg.dtb;imx6dl-mba6b-otg.dtb \
	${KERNEL_IMAGETYPE}-imx6dl-mba6b-pd.dtb;imx6dl-mba6b-pd.dtb \
	"

WKS_FILE ?= "tqma6dl-sdimg.wks"

