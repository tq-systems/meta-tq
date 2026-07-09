# SPDX-License-Identifier: MIT

# Copyright (c) 2023 TQ-Systems GmbH <oss@ew.tq-group.com>,
# D-82229 Seefeld, Germany.
# Author: Markus Niebel

# From meta-freescale: linuxfb missing in meta-freescale for imx91 / imx93
# current linuxfb plugin can handle DRM framebuffer, too
PACKAGECONFIG_GRAPHICS:imxpxp += "\
    linuxfb \
"

# From meta-freescale: we want eglfs QPA plugin even when we have wayland
# and / or X11. This way we can use this for testing without display server
# based on MACHINEOVERRIDES for imx:
PACKAGECONFIG_PLATFORM_EGLFS:imxgpu3d += "eglfs"

# From meta-freescale: we want eglfs and linuxfb QPA plugin even when we have
# wayland and / or X11. This way we can use this for testing without
# display server
PACKAGECONFIG_GRAPHICS:use-mainline-bsp += "\
    linuxfb \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'eglfs', '', d)} \
"

# collect everything we expect from libs on system
# it is better to use these libs than to rely on the builtin versions
# if Qt provides such.
PACKAGECONFIG_SYSTEM += "\
    mtdev \
    tslib \
"
