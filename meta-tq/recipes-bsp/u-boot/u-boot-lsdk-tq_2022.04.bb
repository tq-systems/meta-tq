# SPDX-License-Identifier: MIT
#
# Copyright (c) 2023 TQ-Systems GmbH <oss@ew.tq-group.com>,
# D-82229 Seefeld, Germany.

DESCRIPTION = "U-Boot for TQ-Systems GmbH Freescale LS10xx based modules"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=5a7450c57ffe5ae63fd732446b988025"

SRCREV = "2c38dcd430d3f35370941c11e09c4875913aa0e5"
SRCBRANCH = "TQMxx-lf_v2022.04"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

require recipes-bsp/u-boot/u-boot.inc
require u-boot-tq.inc

DEPENDS += "bison-native"

COMPATIBLE_MACHINE = "tqmls10xxa"
