# SPDX-License-Identifier: MIT
# Copyright (C) 2026 TQ-Systems GmbH <oss@ew.tq-group.com>, D-82229 Seefeld, Germany.

UBOOT_BASE_INC = "recipes-bsp/u-boot/u-boot.inc"
UBOOT_BASE_INC:imx-generic-bsp = "u-boot-imx.inc"

require ${UBOOT_BASE_INC}

DESCRIPTION = "U-boot 2026.04 for TQ-Systems modules"

LIC_FILES_CHKSUM = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"

require recipes-bsp/u-boot/u-boot-tq.inc

UBOOT_INITIAL_ENV = "u-boot-initial-env"

SRCBRANCH = "TQM-v2026.04"
SRCREV = "430ddbee730c0bf282afe35665e1b41acd13d2dd"

DEPENDS += "\
    bc-native \
    dtc-native \
    flex-native \
    gnutls-native \
    python3-native \
    python3-setuptools-native \
"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:tqma8mpxl = "tqma8mpxl"
