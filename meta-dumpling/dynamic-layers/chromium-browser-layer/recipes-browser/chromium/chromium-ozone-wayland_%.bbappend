# SPDX-License-Identifier: MIT
#
# Copyright (c) 2024 TQ-Systems GmbH <oss@ew.tq-group.com>, D-82229 Seefeld, Germany.
# Author: Matthias Schiffer
#
# Original patch and configuration by Texas Instruments, found in
# https://git.ti.com/cgit/arago-project/meta-arago/
#
# Patch currently applies cleanly to Chromium 126.0.6478.126. The bbappend
# does not limit it to a specific version, as meta-browser-chromium is
# updated too frequently, and the patch will usually apply just fine to
# newer versions as well (and working around a non-applying patch using
# :remove is easier than working around a non-applying bbappend).

FILESEXTRAPATHS:prepend:dumpling-wayland-ti := "${THISDIR}/${PN}:"

CHROMIUM_EXTRA_ARGS:remove:dumpling-wayland-ti = " --use-gl=egl"
CHROMIUM_EXTRA_ARGS:append:dumpling-wayland-ti = " --use-gl=angle"

SRC_URI:append:dumpling-wayland-ti = " \
    file://0001-chromium-gpu-sandbox-allow-access-to-PowerVR-GPU-fro.patch \
"
