# new version from meta-imx, needed for imx93

SRC_URI = "${WAYLAND_PROTOCOLS_SRC};branch=${SRCBRANCH}"
WAYLAND_PROTOCOLS_SRC ?= "git://github.com/nxp-imx/wayland-protocols-imx.git;protocol=https"
SRCBRANCH = "wayland-protocols-imx-1.25"

COMPATIBLE_MACHINE = "(imx-nxp-bsp)"
