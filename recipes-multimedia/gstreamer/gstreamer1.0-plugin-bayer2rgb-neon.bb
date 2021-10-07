SUMMARY = "bayer2rgb gstreamer plugin with neon support"
SECTION = "multimedia"
HOMEPAGE = "https://gitlab-ext.sigma-chemnitz.de/ensc/gst-bayer2rgb-neon.git"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

BRANCH = "master"
SRC_URI = "git://gitlab-ext.sigma-chemnitz.de/ensc/gst-bayer2rgb-neon.git;protocol=https;branch=${BRANCH}"

S = "${WORKDIR}/git"

SRCREV = "4fe7bba3f0a84db89e1412fefe6adfcdb515761f"
PV = "0.6.1+git${SRCPV}"

inherit pkgconfig autotools

DEPENDS += "bayer2rgb-neon gstreamer1.0-plugins-base gstreamer1.0"

FILES_${PN} += "${libdir}/gstreamer-*/*.so"
FILES_${PN}-dbg += "${libdir}/gstreamer-*/.debug"
