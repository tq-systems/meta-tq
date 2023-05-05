# Common definitions for TQ-Systems GmbH TQMa8MxML modules on MBa8Mx starter kit

require conf/machine/include/tqma8mxml.inc
require conf/machine/include/mba8mx-common.inc

# Set u-boot DTB
UBOOT_DTB_NAME = "imx8mm-mba8mx.dtb"

KERNEL_DEVICETREE = "\
    freescale/imx8mm-tqma8mqml-mba8mx.dtb \
"

KERNEL_DEVICETREE_linux-imx-tq = "\
    freescale/imx8mm-tqma8mqml-mba8mx-lcdif-lvds-tm070jvhg33.dtb \
    freescale/imx8mm-tqma8mqml-mba8mx-lcdif-lvds-tm070jvhg33-imx327.dtb \
    freescale/imx8mm-tqma8mqml-mba8mx-lcdif-lvds-tm070jvhg33-ov9281.dtb \
    freescale/imx8mm-tqma8mqml-mba8mx-rpmsg.dtb \
"

KERNEL_DEVICETREE += "${@kernel_provider_dtbs(d)}"

IMAGE_BOOT_FILES += "${@create_image_boot_dtb_files(d)}"

IMXBOOT_FSPI_TARGET = "flash_evk_flexspi"
IMXBOOT_SDMMC_TARGET = "flash_spl_uboot"

IMXBOOT_TARGETS ?= "\
    ${IMXBOOT_FSPI_TARGET} \
    ${IMXBOOT_SDMMC_TARGET} \
"

#
# primary bootstream name for WKS / WIC, construction see imx-boot recipe,
# do_deploy
#
MACHINE_WKS_BOOTSTREAM ?= "imx-boot-${MACHINE}-sd.bin-${IMXBOOT_SDMMC_TARGET}"
# append to prevent overwriting default assignment
WICVARS:append = " MACHINE_WKS_BOOTSTREAM"
