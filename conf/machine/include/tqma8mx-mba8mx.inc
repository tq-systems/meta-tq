# Common definitions for TQ-Systems GmbH MBa8Mx baseboard for TQMa8Mx modules

# Set u-boot DTB
UBOOT_DTB_NAME = "imx8mq-mba8mx.dtb"

BOARD_DEVICETREE_FILES = "\
    imx8mq-tqma8mq-mba8mx.dtb \
    imx8mq-tqma8mq-mba8mx-dcss-hdmi.dtb \
    imx8mq-tqma8mq-mba8mx-dcss-hdmi-ov9281.dtb \
    imx8mq-tqma8mq-mba8mx-dcss-hdmi-imx327.dtb \
    imx8mq-tqma8mq-mba8mx-dcss-lvds-tm070jvhg33.dtb \
    imx8mq-tqma8mq-mba8mx-lcdif-lvds-tm070jvhg33.dtb \
    imx8mq-tqma8mq-mba8mx-rpmsg.dtb \
"

KERNEL_DEVICETREE = ""

require conf/machine/include/tqma8-common.inc

# Set Serial console
SERIAL_CONSOLES = "115200;ttymxc2"

# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"

IMAGE_BOOT_FILES = "\
    ${KERNEL_IMAGETYPE} \
    ${BOARD_DEVICETREE_FILES} \
"

IMAGE_BOOT_FILES += "\
    hello_world.bin \
    rpmsg_lite_pingpong_rtos_linux_remote.bin \
"

IMXBOOT_TARGETS ?= "flash_hdmi_spl_uboot flash_spl_uboot"

WKS_FILE_DEPENDS_append = "\
    virtual/imx-cortexm-demos \
"

MACHINE_FEATURES_append = "\
    camera \
    display \
    wifi \
"

MACHINE_EXTRA_RRECOMMENDS_append = " linux-firmware-ath10k"
