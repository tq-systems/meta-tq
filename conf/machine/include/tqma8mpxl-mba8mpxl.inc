# Common definitions for TQ-Group MBa8MPxL baseboard for TQMa8MPxL modules

# Set u-boot DTB
UBOOT_DTB_NAME = "imx8mp-mba8mpxl.dtb"

BOARD_DEVICETREE_FILES = "\
    imx8mp-mba8mpxl.dtb \
    imx8mp-mba8mpxl-hdmi.dtb \
    imx8mp-mba8mpxl-hdmi-imx327.dtb \
    imx8mp-mba8mpxl-hdmi-ov9281.dtb \
    imx8mp-mba8mpxl-rpmsg.dtb \
    imx8mp-mba8mpxl-lvds-tm070jvhg33.dtb \
"

KERNEL_DEVICETREE = ""

require conf/machine/include/tqma8-common.inc

MACHINE_FEATURES_append = "\
  wifi \
  camera \
"

# Set Serial console
SERIAL_CONSOLES = "115200;ttymxc3"

IMAGE_BOOT_FILES ?= "\
	${KERNEL_IMAGETYPE} \
	${BOARD_DEVICETREE_FILES} \
"

# SD boot image and FlexSPI boot image can be built using same U-Boot config
IMXBOOT_TARGETS ?= "flash_spl_uboot flash_evk_flexspi"

IMAGE_BOOT_FILES += "\
    hello_world.bin \
    rpmsg_lite_pingpong_rtos_linux_remote.bin \
"

WKS_FILE_DEPENDS_append = "\
    tqma8-cortexm-demos \
"