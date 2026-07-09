# SPDX-License-Identifier: MIT
#
# Copyright (c) 2020-2023 TQ-Systems GmbH <oss@ew.tq-group.com>,
# D-82229 Seefeld, Germany.

require recipes-bsp/u-boot/u-boot.inc
require u-boot-tq.inc

DESCRIPTION = "U-Boot for TQ-Systems GmbH NXP i.MX6 based modules"

LIC_FILES_CHKSUM = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"

DEPENDS += "\
    bc-native \
    dtc-native \
    lzop-native \
"

SRCREV = "9e9dd7765de0b6f2f594049b62d1652c0a0d0b8a"
SRCBRANCH = "TQM-v2023.04"

UBOOT_INITIAL_ENV = "u-boot-initial-env"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:tqma6ulx = "tqma6ulx"
COMPATIBLE_MACHINE:tqma6ullx = "tqma6ullx"
COMPATIBLE_MACHINE:tqma6x = "tqma6x"
COMPATIBLE_MACHINE:tqma7x = "tqma7x"
