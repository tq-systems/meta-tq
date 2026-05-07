# SPDX-License-Identifier: MIT
# Copyright (C) 2026 TQ-Systems GmbH <oss@ew.tq-group.com>, D-82229 Seefeld, Germany.

UBOOT_BASE_INC = "recipes-bsp/u-boot/u-boot.inc"
UBOOT_BASE_INC:k3 = "recipes-bsp/u-boot/u-boot-ti.inc"
UBOOT_BASE_INC:k3r5 = "recipes-bsp/u-boot/u-boot-ti.inc"

require ${UBOOT_BASE_INC}

DESCRIPTION = "U-boot 2026.01 for TQ-Systems modules"

LIC_FILES_CHKSUM = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"

SPL_BINARY = ""
SPL_BINARYNAME = ""
SPL_UART_BINARY = ""

TQ_FW_ENV_CONFIG:k3r5 = ""

require recipes-bsp/u-boot/u-boot-tq.inc

UBOOT_INITIAL_ENV = "u-boot-initial-env"

SRCBRANCH = "TQMaxx-u-boot-v2026.01"
SRCREV = "e02c04473aefff6a70cfee46b3dfa91652d5e6d7"

DEPENDS += "python3-setuptools-native"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:tqma62xx = "tqma62xx"
COMPATIBLE_MACHINE:tqma62xx-k3r5 = "tqma62xx-k3r5"
COMPATIBLE_MACHINE:tqma64xxl = "tqma64xxl"
COMPATIBLE_MACHINE:tqma64xxl-k3r5 = "tqma64xxl-k3r5"
COMPATIBLE_MACHINE:tqma67xx = "tqma67xx"
COMPATIBLE_MACHINE:tqma67xx-k3r5 = "tqma67xx-k3r5"
