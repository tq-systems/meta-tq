require recipes-bsp/u-boot/u-boot.inc

inherit imx-hab
inherit ${@oe.utils.ifelse(d.getVar('UBOOT_PROVIDES_BOOT_CONTAINER') == '1', 'imx-boot-container', '')}

DEPENDS += "${IMX_EXTRA_FIRMWARE}"
DEPENDS += "${IMX_DEFAULT_ATF_PROVIDER}"

# This package aggregates output deployed by other packages,
# so set the appropriate dependencies
do_compile[deptask] = "do_deploy"

EXTRA_OEMAKE += "BL31=${DEPLOY_DIR_IMAGE}/${ATF_MACHINE_NAME}"
EXTRA_OEMAKE += "BINMAN_INDIRS=${DEPLOY_DIR_IMAGE}"

SPL_BINARY = ""
SPL_BINARYNAME = ""
SPL_UART_BINARY = ""

PROVIDES += "imx-boot"

OVERRIDES:append = "${@bb.utils.contains('DISTRO_FEATURES', 'secure', ':secure', '', d)}"
DEPENDS:append:secure = " imx-cst-native imx-cst-keys-native"
SRC_URI:append:secure:nxp-ahab = " file://imx-hab.cfg"
SRC_URI:append:secure:nxp-hab4 = " file://imx-hab.cfg"

EXTRA_OEMAKE:append:secure = " SRK_TABLE=${STAGING_DATADIR_NATIVE}/imx-cst-keys/${IMX_HAB_KEY_NAME}/${IMX_HAB_SRK_TABLE}"
EXTRA_OEMAKE:append:secure = " CSF_KEY=${STAGING_DATADIR_NATIVE}/imx-cst-keys/${IMX_HAB_KEY_NAME}/${IMX_HAB_CSF_CERT}"
EXTRA_OEMAKE:append:secure = " IMG_KEY=${STAGING_DATADIR_NATIVE}/imx-cst-keys/${IMX_HAB_KEY_NAME}/${IMX_HAB_IMG_CERT}"
