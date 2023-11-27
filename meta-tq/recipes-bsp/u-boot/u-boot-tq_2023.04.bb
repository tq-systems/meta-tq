# SPDX-License-Identifier: MIT
#
# Copyright (c) 2020-2023 TQ-Systems GmbH <oss@ew.tq-group.com>,
# D-82229 Seefeld, Germany.

DESCRIPTION = "U-Boot for TQ-Systems GmbH NXP i.MX6 based modules"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"

DEPENDS += "\
    bc-native \
    bison-native \
    dtc-native \
"

SRCREV = "534a95cd61662252b7affa86065aab7c651a0564"
SRCBRANCH = "TQM-v2023.04"

SRC_URI = " \
    ${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
"

require recipes-bsp/u-boot/u-boot.inc
require u-boot-tq.inc

COMPATIBLE_MACHINE = "tqma6x"
