DESCRIPTION = "Packages and tools for camera support"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

LIBCAMERA_PKGS = " \
    libcamera \
    libcamera-gst \
"
ISP_PKGS ?= ""
# FIXME: Add kernel-module-isp-vvcam once TQMa8MPxL is supported
# for linux-imx-tq v6.18. For now, kernel-module-isp-vvcam from meta-freescale,
# is not compatible with TQMa8MPxL kernel linux-imx-tq v6.6.
ISP_PKGS:mx8mp-nxp-bsp = " \
    isp-imx \
"
ISP_PKGS:mx95-nxp-bsp = " \
    neo-ipa-uguzzi \
"
RDEPENDS:${PN} = " \
    ${ISP_PKGS} \
    ${LIBCAMERA_PKGS} \
"
