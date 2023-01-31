# SPDX-License-Identifier: MIT

# Copyright (c) 2020-2023 TQ-Systems GmbH <oss@ew.tq-group.com>,
# D-82229 Seefeld, Germany.
# Author: Markus Niebel

DESCRIPTION = "Package group for Qt5 librararies"
LICENSE = "MIT"

#
# Config of Qt may depend on MACHINE an/or MACHINE_FEATURES
# See bbappend for qtbase in meta-freescale
# For other vendor specific GPU / graphic support this is currently not clear
# this is why we restrict PACKAGE_ARCH at least for imx with NXP software stack
# otherwise we will see QA warnings
#
PACKAGE_ARCH:imx-nxp-bsp = "${TUNE_PKGARCH}"

inherit packagegroup

RDEPENDS:${PN} = "\
    qtbase \
    qtbase-plugins \
    qtserialport \
    qtdeclarative \
    qtmultimedia \
    qwt-qt5 \
"
