# Common definitions for TQ-Systems GmbH MBa8Mx baseboard for TQMa8Mx modules

require conf/machine/include/tqma8mx.inc
require conf/machine/include/mba8mx-common.inc

# Set u-boot DTB
UBOOT_DTB_NAME = "imx8mq-mba8mx.dtb"

KERNEL_DEVICETREE = "\
    freescale/imx8mq-tqma8mq-mba8mx.dtb \
"

KERNEL_DEVICETREE_linux-imx-tq = "\
    freescale/imx8mq-tqma8mq-mba8mx-dcss-hdmi.dtb \
    freescale/imx8mq-tqma8mq-mba8mx-dcss-hdmi-ov9281.dtb \
    freescale/imx8mq-tqma8mq-mba8mx-dcss-hdmi-imx327.dtb \
    freescale/imx8mq-tqma8mq-mba8mx-dcss-lvds-tm070jvhg33.dtb \
    freescale/imx8mq-tqma8mq-mba8mx-lcdif-lvds-tm070jvhg33.dtb \
    freescale/imx8mq-tqma8mq-mba8mx-rpmsg.dtb \
"

KERNEL_DEVICETREE += "${@kernel_provider_dtbs(d)}"

IMAGE_BOOT_FILES += "${@create_image_boot_dtb_files(d)}"

IMXBOOT_SDMMC_HDMI_TARGET = "flash_hdmi_spl_uboot"
IMXBOOT_SDMMC_TARGET = "flash_spl_uboot"

IMXBOOT_TARGETS ?= "\
    ${IMXBOOT_SDMMC_HDMI_TARGET} \
    ${IMXBOOT_SDMMC_TARGET} \
"

#
# primary bootstream name for WKS / WIC, construction see imx-boot recipe,
# do_deploy
#
MACHINE_WKS_BOOTSTREAM ?= "imx-boot-${MACHINE}-sd.bin-${IMXBOOT_SDMMC_HDMI_TARGET}"
