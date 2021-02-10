# Common definitions for TQ-Group TQMa6[S,DL] modules on MBa6

KERNEL_DEVICETREE = " \
	imx6dl-mba6a.dtb \
	imx6dl-mba6a-hdmi.dtb \
	imx6dl-mba6b.dtb \
	imx6dl-mba6b-hdmi.dtb \
"

###
#	imx6dl-mba6a-lvds.dtb \
#	imx6dl-mba6a-otg.dtb \
#	imx6dl-mba6a-dmb-ct21.dtb \
#	imx6dl-mba6a-dmb-ct44.dtb \
#	imx6dl-mba6a-glyn-etm0700g0dh6.dtb \
#	imx6dl-mba6a-glyn-etm0700g0edh6.dtb \
#	imx6dl-mba6b-hdmi.dtb \
#	imx6dl-mba6b-lvds.dtb \
#	imx6dl-mba6b-otg.dtb \
#	imx6dl-mba6b-dmb-ct21.dtb \
#	imx6dl-mba6b-dmb-ct44.dtb \
#	imx6dl-mba6b-glyn-etm0700g0dh6.dtb \
#	imx6dl-mba6b-glyn-etm0700g0edh6.dtb \
###

IMAGE_BOOT_FILES ?= " \
	${KERNEL_IMAGETYPE};linuximage \
	${KERNEL_DEVICETREE} \
"
