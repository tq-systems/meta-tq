# Common definitions for TQ-Systems GmbH MBa8MPxL baseboard for TQMa8MPxL modules

require conf/machine/include/tqma8mpxl.inc
require conf/machine/include/mba8-common.inc

# Set u-boot DTB
UBOOT_DTB_NAME = "imx8mp-tqma8mpql-mba8mpxl.dtb"

KERNEL_DEVICETREE = "\
    freescale/imx8mp-tqma8mpql-mba8mpxl.dtb \
"

KERNEL_DEVICETREE_linux-imx-tq = "\
    freescale/imx8mp-tqma8mpql-mba8mpxl-hdmi.dtb \
    freescale/imx8mp-tqma8mpql-mba8mpxl-hdmi-imx327.dtb \
    freescale/imx8mp-tqma8mpql-mba8mpxl-hdmi-ov9281.dtb \
    freescale/imx8mp-tqma8mpql-mba8mpxl-lvds-tm070jvhg33.dtb \
    freescale/imx8mp-tqma8mpql-mba8mpxl-rpmsg.dtb \
"

KERNEL_DEVICETREE_linux-imx-tq:append:mx8mp-nxp-bsp = "\
    ${@bb.utils.contains('MACHINE_FEATURES', 'basler', \
        ' freescale/imx8mp-tqma8mpql-mba8mpxl-lvds-basler.dtb', '', d)} \
"

KERNEL_DEVICETREE += "${@kernel_provider_dtbs(d)}"

MACHINE_FEATURES:append = "\
    bluetooth \
    camera \
    screen \
    touchscreen \
    wifi \
"

MACHINE_EXTRA_RRECOMMENDS += "\
    linux-firmware-ath10k \
    linux-firmware-qca \
    tq-extlinux \
"

# Add packages we want to be deployed, even when they are not part of rootfs
# We need these here to support UBI / NFS boot case
EXTRA_IMAGEDEPENDS += "\
    virtual/imx-cortexm-demos \
    tq-bootscripts \
"

# Set Serial console
SERIAL_CONSOLES = "115200;ttymxc3"

# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"

CORTEXM_DEMOS += "\
    hello_world.bin \
    rpmsg_lite_pingpong_rtos_linux_remote.bin \
"

IMAGE_BOOT_FILES = "\
    ${@create_image_boot_dtb_files(d)} \
    boot-blockdev.scr;boot.scr \
    ${CORTEXM_DEMOS} \
    ${KERNEL_IMAGETYPE} \
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
