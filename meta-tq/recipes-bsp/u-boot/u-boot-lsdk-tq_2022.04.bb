# SPDX-License-Identifier: MIT
#
# Copyright (c) 2023 TQ-Systems GmbH <oss@ew.tq-group.com>,
# D-82229 Seefeld, Germany.

DESCRIPTION = "U-Boot for TQ-Systems GmbH Freescale LS10xx based modules"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=5a7450c57ffe5ae63fd732446b988025"

SRCREV = "14aa49225710ec7955ac76e1a6e807b529f3cd92"
SRCBRANCH = "TQMxx-lf_v2022.04"

require recipes-bsp/u-boot/u-boot.inc
require u-boot-tq.inc

DEPENDS += "bison-native"

COMPATIBLE_MACHINE = "tqmls10xxa"
