# Common definitions for TQ-Systems GmbH MBa8MPxL baseboard for TQMa8MPxL modules

# Set u-boot DTB
UBOOT_DTB_NAME = "imx8mp-tqma8mpql-mba8mpxl.dtb"

BOARD_DEVICETREE_FILES = "\
    imx8mp-tqma8mpql-mba8mpxl.dtb \
    imx8mp-tqma8mpql-mba8mpxl-hdmi.dtb \
    imx8mp-tqma8mpql-mba8mpxl-hdmi-imx327.dtb \
    imx8mp-tqma8mpql-mba8mpxl-hdmi-ov9281.dtb \
    imx8mp-tqma8mpql-mba8mpxl-lvds-tm070jvhg33.dtb \
    imx8mp-tqma8mpql-mba8mpxl-rpmsg.dtb \
"
BOARD_DEVICETREE_FILES:append:mx8mp = "${@bb.utils.contains('MACHINE_FEATURES', 'basler', ' imx8mp-tqma8mpql-mba8mpxl-lvds-basler.dtb', '', d)}"

KERNEL_DEVICETREE = ""

require conf/machine/include/tqma8-common.inc

MACHINE_FEATURES:append = "\
    bluetooth \
    camera \
    display \
    wifi \
"

MACHINE_EXTRA_RRECOMMENDS += "\
    linux-firmware-ath10k \
    linux-firmware-qca \
"

# Set Serial console
SERIAL_CONSOLES = "115200;ttymxc3"

# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"

IMAGE_BOOT_FILES = "\
    ${KERNEL_IMAGETYPE} \
    ${BOARD_DEVICETREE_FILES} \
"

# SD boot image and FlexSPI boot image can be built using same U-Boot config
IMXBOOT_TARGETS ?= "\
    flash_evk_flexspi \
    flash_spl_uboot \
"

#
# bootstream name, construction see imx-boot recipe, do_deploy
#
MACHINE_WKS_BOOTSTREAM ?= "imx-boot-${MACHINE}-sd.bin-flash_spl_uboot"

IMAGE_BOOT_FILES += "\
    hello_world.bin \
    rpmsg_lite_pingpong_rtos_linux_remote.bin \
"

WKS_FILE_DEPENDS:append = "\
    virtual/imx-cortexm-demos \
"
