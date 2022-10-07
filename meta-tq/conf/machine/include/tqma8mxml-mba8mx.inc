# Common definitions for TQ-Systems GmbH TQMa8MxML modules on MBa8Mx starter kit

require conf/machine/include/tqma8mxml.inc
require conf/machine/include/mba8mx-common.inc

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

IMXBOOT_TARGETS ?= "\
    flash_evk_flexspi \
    flash_spl_uboot \
"

#
# bootstream name, construction see imx-boot recipe, do_deploy
#
MACHINE_WKS_BOOTSTREAM ?= "imx-boot-${MACHINE}-sd.bin-flash_spl_uboot"
