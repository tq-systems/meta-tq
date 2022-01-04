# Common definitions for TQ-Systems GmbH TQMa8MxML modules on MBa8Mx starter kit

require conf/machine/include/tqma8mxml.inc

# Set u-boot DTB
UBOOT_DTB_NAME = "imx8mm-mba8mx.dtb"

BOARD_DEVICETREE_FILES = "\
    imx8mm-tqma8mqml-mba8mx.dtb \
    imx8mm-tqma8mqml-mba8mx-lcdif-lvds-tm070jvhg33.dtb \
    imx8mm-tqma8mqml-mba8mx-lcdif-lvds-tm070jvhg33-imx327.dtb \
    imx8mm-tqma8mqml-mba8mx-lcdif-lvds-tm070jvhg33-ov9281.dtb \
    imx8mm-tqma8mqml-mba8mx-rpmsg.dtb \
"

KERNEL_DEVICETREE = ""

require conf/machine/include/tqma8-common.inc

# Set Serial console
SERIAL_CONSOLES = "115200;ttymxc2"

IMAGE_BOOT_FILES ?= "\
    ${KERNEL_IMAGETYPE} \
    ${BOARD_DEVICETREE_FILES} \
"

MACHINE_FEATURES_append = " camera wifi"

MACHINE_EXTRA_RRECOMMENDS_append = " linux-firmware-ath10k"

IMAGE_BOOT_FILES += "\
    hello_world.bin \
    rpmsg_lite_pingpong_rtos_linux_remote.bin \
"

WKS_FILE_DEPENDS_append = "\
    virtual/imx-cortexm-demos \
"
