# Common definitions for TQ-Systems GmbH MBa8Mx baseboard for TQMa8Mx modules

require conf/machine/include/tqma8mx.inc
require conf/machine/include/mba8mx-common.inc

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

IMXBOOT_TARGETS ?= "\
    flash_hdmi_spl_uboot \
    flash_spl_uboot \
"
