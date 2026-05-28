# SPDX-License-Identifier: MIT
#
# Copyright (c) 2025 TQ-Systems GmbH <oss@ew.tq-group.com>,
# D-82229 Seefeld, Germany.

require recipes-bsp/u-boot/u-boot.inc
require u-boot-tq.inc

DESCRIPTION = "U-Boot for TQ-Systems GmbH NXP i.MX9 based SOM"

LIC_FILES_CHKSUM = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"

SRCREV = "03b6d6007103c14f41d48fe02347aab42c54b45e"
SRCBRANCH = "TQM-lf_v2025.04"

SRC_URI:append:nxp-ahab = " ${@bb.utils.contains('DISTRO_FEATURES', 'secure', 'file://imx-hab.cfg', '', d)}"
SRC_URI:append:nxp-hab4 = " ${@bb.utils.contains('DISTRO_FEATURES', 'secure', 'file://imx-hab.cfg', '', d)}"

DEPENDS += "\
    bc-native \
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
