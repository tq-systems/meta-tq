
DESCRIPTION = "u-boot for TQ-Systems GmbH NXP i.MX8 based modules"

require u-boot-imx-tq-common_${PV}.inc
require recipes-bsp/u-boot/u-boot.inc
require u-boot-tq.inc
require u-boot-bootstream-deploy.inc

# inherit pythonnative

DEPENDS:append = " dtc-native bc-native python3-native"

do_deploy:append:mx8m-generic-bsp () {
    do_deploy_uboot_dtb
}

UBOOT_NAME:mx8-generic-bsp = "u-boot-${MACHINE}.bin-${UBOOT_CONFIG}"
