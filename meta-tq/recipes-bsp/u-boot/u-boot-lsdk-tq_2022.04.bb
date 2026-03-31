# SPDX-License-Identifier: MIT
#
# Copyright (c) 2023 TQ-Systems GmbH <oss@ew.tq-group.com>,
# D-82229 Seefeld, Germany.

require recipes-bsp/u-boot/u-boot.inc
require u-boot-tq.inc

DESCRIPTION = "U-Boot for TQ-Systems GmbH Freescale LS10xx based modules"

LIC_FILES_CHKSUM = "file://Licenses/README;md5=5a7450c57ffe5ae63fd732446b988025"

SRCREV = "769b50c5ac6102d2819c51fa981889d14463109a"
SRCBRANCH = "TQMxx-lf_v2022.04"

COMPATIBLE_MACHINE = "tqmls10xxa"
