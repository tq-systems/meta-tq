# SPDX-License-Identifier: MIT
#
# Copyright (c) 2022-2023 TQ-Systems GmbH <oss@ew.tq-group.com>,
# D-82229 Seefeld, Germany.

DESCRIPTION = "U-Boot for TQ-Systems GmbH NXP i.MX9 based SOM"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"

SRCREV = "2548f8d7eff24685500af398e03b832d6d554adc"
SRCBRANCH = "TQM-lf_v2023.04"

SRC_URI = "\
    ${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
"

require recipes-bsp/u-boot/u-boot.inc
require u-boot-tq.inc
require u-boot-bootstream-deploy.inc

DEPENDS:append = "\
    bc-native \
    bison-native \
    dtc-native \
    python3-native \
"

UBOOT_NAME:mx9-generic-bsp = "u-boot-${MACHINE}.bin-${UBOOT_CONFIG}"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:tqma93xx = "tqma93xx"
