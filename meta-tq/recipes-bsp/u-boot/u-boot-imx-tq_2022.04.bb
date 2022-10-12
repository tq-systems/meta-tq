# Copyright (C) 2022 TQ-Systems GmbH

DESCRIPTION = "U-Boot for TQ-Systems GmbH NXP i.MX9 based modules"

require u-boot-imx-tq-common_${PV}.inc
require recipes-bsp/u-boot/u-boot.inc
require u-boot-tq.inc
require u-boot-bootstream-deploy.inc

DEPENDS:append = "\
    bc-native \
    dtc-native \
    python3-native \
"

UBOOT_NAME:mx9-generic-bsp = "u-boot-${MACHINE}.bin-${UBOOT_CONFIG}"
