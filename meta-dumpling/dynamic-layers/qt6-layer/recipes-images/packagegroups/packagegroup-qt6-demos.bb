# SPDX-License-Identifier: MIT

# Copyright (c) 2020-2023 TQ-Systems GmbH <oss@ew.tq-group.com>,
# D-82229 Seefeld, Germany.
# Author: Markus Niebel

DESCRIPTION = "Package group for Qt6 demos"
LICENSE = "MIT"

#
# Config of Qt may depend on MACHINE and/or MACHINE_FEATURES
# See bbappend for qtbase in meta-freescale and PACKAGE_ARCH assignments in
# packagegroup recipes im meta-qt6
#
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS:${PN} = "\
    packagegroup-qt6-libs \
    qtbase-examples \
    qtmultimedia-examples \
"

QT6_OPENGL_DEMOS = "\
    qt3d-examples \
    qtquick3d-examples \
"

RDEPENDS:${PN} += "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', "${QT6_OPENGL_DEMOS}", '', d)} \
"
