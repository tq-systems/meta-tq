# This file is included from meta-freescale/conf/machine/include/imx-base.inc
# and provides a simple mechanism to override settings in the file

# should be removed as soon as meta-freescale has imx93 support

# i.MX 93
MACHINEOVERRIDES_EXTENDER:mx93:use-nxp-bsp  = "imx-generic-bsp:imx-nxp-bsp:imxdrm:imxpxp:mx9-generic-bsp:mx9-nxp-bsp:mx93-generic-bsp:mx93-nxp-bsp"
MACHINEOVERRIDES_EXTENDER_FILTER_OUT += "mx93"
MACHINE_SOCARCH_SUFFIX:mx93-nxp-bsp = "-mx93"

# Graphics libraries
PREFERRED_PROVIDER_virtual/libg2d:mx93-nxp-bsp ?= "imx-pxp-g2d"
PREFERRED_VERSION_wayland-protocols:mx9-nxp-bsp ??= "1.25.imx"
PREFERRED_VERSION_libdrm:mx9-nxp-bsp ??= "2.4.109.imx"
