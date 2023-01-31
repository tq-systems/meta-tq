# New version from meta-imx, needed for imx93
#
# G2D support and libdrm for fbdev config is new in meta-imx as well as the
# extended compatibiliy for imx93.
# This should go to meta-freescale.

SRCREV = "b3ccf36b718d16f5fb38ccfc2cccaf45c79854d8"

PACKAGECONFIG:append:mx93-nxp-bsp = " imxgpu imxg2d"

PACKAGECONFIG[fbdev] = "-Dbackend-fbdev=true,-Dbackend-fbdev=false,udev mtdev libdrm"

COMPATIBLE_MACHINE = "(imx-nxp-bsp)"
