# SPDX-License-Identifier: MIT

# Copyright (c) 2020-2024 TQ-Systems GmbH <oss@ew.tq-group.com>,
# D-82229 Seefeld, Germany.
# Author: Markus Niebel

DESCRIPTION = "Package group for Qt6 librararies"
LICENSE = "MIT"

#
# Config of Qt may depend on MACHINE and/or MACHINE_FEATURES
# See bbappend for qtbase in meta-freescale and PACKAGE_ARCH assignments in
# packagegroup recipes im meta-qt6
#
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS:${PN} = "\
    packagegroup-qt6-essentials \
    qtbase-plugins \
    qtmultimedia \
"

RDEPENDS:${PN} += "\
    ttf-dejavu-sans \
    ttf-dejavu-sans-condensed \
    ttf-dejavu-sans-mono \
    ttf-dejavu-serif \
    ttf-dejavu-serif-condensed \
    ttf-dejavu-mathtexgyre \
    ttf-dejavu-common \
"

RDEPENDS:${PN} += "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', "qtwayland qtwayland-plugins", '', d)} \
"

RDEPENDS:${PN} += "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl',  "qt3d qtquick3d qtquick3d-dev", '', d)} \
"
