# SPDX-License-Identifier: MIT

# Copyright (c) 2023 TQ-Systems GmbH <oss@ew.tq-group.com>,
# D-82229 Seefeld, Germany.
# Author: Markus Niebel

PACKAGECONFIG_DEFAULT += "\
    examples \
    widgets \
"

PACKAGECONFIG_DISTRO += "\
    dbus \
    udev \
"

PACKAGECONFIG_FONTS += "\
    fontconfig \
    freetype \
"

# from meta-imx, linuxfb missing in meta-freescale for imx93
# current linuxfb plugin can handle DRM framebuffer, too
PACKAGECONFIG_GL:imxpxp = "\
    gles2 \
    linuxfb \
"

# we want eglfs QPA plugin even when we have wayland and / or X11
# this way we can use this for testing without display server
# based on MACHINEOVERRIDES for imx:
PACKAGECONFIG_PLATFORM:imxgpu3d += "eglfs"

PACKAGECONFIG_OPENSSL += "\
    openssl \
    getentropy \
"

# collect everything we expect from libs on system
# it is better to use these libs than to rely on the builtin versions
# if Qt provides such.
PACKAGECONFIG_SYSTEM += "\
    evdev \
    jpeg \
    libinput \
    libpng \
    mtdev \
    tslib \
    zlib \
"
