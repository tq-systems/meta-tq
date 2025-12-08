# SPDX-License-Identifier: MIT
# Copyright (C) 2025 TQ-Systems GmbH <oss@ew.tq-group.com>, D-82229 Seefeld, Germany.

UBOOT_BASE_INC = "recipes-bsp/u-boot/u-boot.inc"
UBOOT_BASE_INC:k3 = "recipes-bsp/u-boot/u-boot-ti.inc"
UBOOT_BASE_INC:k3r5 = "recipes-bsp/u-boot/u-boot-ti.inc"

require ${UBOOT_BASE_INC}

DESCRIPTION = "U-boot 2025.07 for TQ-Systems modules"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

SPL_BINARY = ""
SPL_BINARYNAME = ""
SPL_UART_BINARY = ""

TQ_FW_ENV_CONFIG:k3r5 = ""

require recipes-bsp/u-boot/u-boot-tq.inc

UBOOT_INITIAL_ENV = "u-boot-initial-env"

SRCBRANCH = "TQMaxx-u-boot-v2025.07"
SRCREV = "ef62e6ce3ecb6788030424b7cab4f5acc9a5329a"

DEPENDS += "python3-setuptools-native"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:tqma62xx = "tqma62xx"
COMPATIBLE_MACHINE:tqma62xx-k3r5 = "tqma62xx-k3r5"
COMPATIBLE_MACHINE:tqma64xxl = "tqma64xxl"
COMPATIBLE_MACHINE:tqma64xxl-k3r5 = "tqma64xxl-k3r5"
COMPATIBLE_MACHINE:tqma67xx = "tqma67xx"
COMPATIBLE_MACHINE:tqma67xx-k3r5 = "tqma67xx-k3r5"
