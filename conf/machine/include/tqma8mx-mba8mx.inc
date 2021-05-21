# Common definitions for TQ-Group MBa8Mx baseboard for TQMa8Mx modules

# Set u-boot DTB
UBOOT_DTB_NAME = "imx8mq-mba8mx.dtb"

IMXBOOT_TARGETS ?= "flash_spl_uboot flash_hdmi_spl_uboot"

BOARD_DEVICETREE_FILES = "\
    imx8mq-mba8mx.dtb \
    imx8mq-mba8mx-hdmi.dtb \
    imx8mq-mba8mx-dcss-lvds-tm070jvhg33.dtb \
    imx8mq-mba8mx-lcdif-lvds-tm070jvhg33.dtb \
    imx8mq-mba8mx-rpmsg.dtb \
"

KERNEL_DEVICETREE = ""

require conf/machine/include/tqma8-common.inc

# Set Serial console
SERIAL_CONSOLES = "115200;ttymxc2"

IMAGE_BOOT_FILES ?= "\
	${KERNEL_IMAGETYPE} \
	${BOARD_DEVICETREE_FILES} \
	"

IMAGE_BOOT_FILES += " \
    hello_world.bin \
    rpmsg_lite_pingpong_rtos_linux_remote.bin \
"

WKS_FILE_DEPENDS_append = "\
	cortexm-demos \
	"

MACHINE_FEATURES_append = " wifi"
MACHINE_EXTRA_RRECOMMENDS_append = " linux-firmware-ath10k"