# SPDX-License-Identifier: MIT
#
# Copyright (c) 2024 TQ-Systems GmbH <oss@ew.tq-group.com>, D-82229 Seefeld, Germany.
# Author: Matthias Schiffer

FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI += "file://0001-udev-builtin-net_id-add-NAMING_DEVICETREE_PORT_ALIAS.patch"
EXTRA_OEMESON += "-Dextra-net-naming-schemes=latest=v255+devicetree-port-aliases"
