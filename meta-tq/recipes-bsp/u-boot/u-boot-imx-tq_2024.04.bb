# SPDX-License-Identifier: MIT
#
# Copyright (c) 2024 TQ-Systems GmbH <oss@ew.tq-group.com>,
# D-82229 Seefeld, Germany.

require recipes-bsp/u-boot/u-boot.inc
require u-boot-tq.inc

DESCRIPTION = "U-Boot for TQ-Systems GmbH NXP i.MX8MP / i.MX9 based SOM"

LIC_FILES_CHKSUM = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"

SRCREV = "d8ce4cf32ffabc2ac8a4af0ee0fa5b17d3b46c61"
SRCBRANCH = "TQM-lf_v2024.04"

SRC_URI:append:nxp-ahab = " ${@bb.utils.contains('DISTRO_FEATURES', 'secure', 'file://imx-hab.cfg', '', d)}"
SRC_URI:append:nxp-hab4 = " ${@bb.utils.contains('DISTRO_FEATURES', 'secure', 'file://imx-hab.cfg', '', d)}"

DEPENDS:append = "\
    bc-native \
    dtc-native \
    python3-native \
"

UBOOT_NAME:mx8-generic-bsp = "u-boot-${MACHINE}.bin-${UBOOT_CONFIG}"
UBOOT_NAME:mx9-generic-bsp = "u-boot-${MACHINE}.bin-${UBOOT_CONFIG}"

UBOOT_INITIAL_ENV = "u-boot-initial-env"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:tqma8mpxl = "tqma8mpxl"
COMPATIBLE_MACHINE:tqma8mpxs = "tqma8mpxs"
COMPATIBLE_MACHINE:tqma91xx = "tqma91xx"
COMPATIBLE_MACHINE:tqma93xx = "tqma93xx"
