# SPDX-License-Identifier: MIT
#
# Copyright (c) 2025 TQ-Systems GmbH <oss@ew.tq-group.com>,
# D-82229 Seefeld, Germany.

require recipes-bsp/u-boot/u-boot.inc
require u-boot-tq.inc
require u-boot-bootstream-deploy.inc

DESCRIPTION = "U-Boot for TQ-Systems GmbH NXP i.MX9 based SOM"

LIC_FILES_CHKSUM = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"

SRCREV = "4127107fd03d4f3e4666603d7708c666741a08a0"
SRCBRANCH = "TQM-lf_v2025.04"

SRC_URI:append:nxp-ahab = " ${@bb.utils.contains('DISTRO_FEATURES', 'secure', 'file://imx-hab.cfg', '', d)}"
SRC_URI:append:nxp-hab4 = " ${@bb.utils.contains('DISTRO_FEATURES', 'secure', 'file://imx-hab.cfg', '', d)}"

DEPENDS += "\
    bc-native \
    bison-native \
    dtc-native \
    flex-native \
    gnutls-native \
    python3-native \
    python3-setuptools-native \
"

UBOOT_NAME:mx8-generic-bsp = "u-boot-${MACHINE}.bin-${UBOOT_CONFIG}"
UBOOT_NAME:mx9-generic-bsp = "u-boot-${MACHINE}.bin-${UBOOT_CONFIG}"

UBOOT_INITIAL_ENV = "u-boot-initial-env"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:tqma95xx = "tqma95xx"
