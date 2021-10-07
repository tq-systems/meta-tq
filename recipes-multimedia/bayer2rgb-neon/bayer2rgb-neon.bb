SUMMARY = "bayer2rgb color conversion with ARM neon support"
SECTION = "libs"
HOMEPAGE = "https://gitlab-ext.sigma-chemnitz.de/ensc/bayer2rgb.git"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

BRANCH = "master"
SRC_URI = "git://gitlab-ext.sigma-chemnitz.de/ensc/bayer2rgb.git;protocol=https;branch=${BRANCH}"

S = "${WORKDIR}/git"

SRCREV = "15feb1115b4828488cc36d09f625e23e8b6a0ec5"
PV = "0.6.1+git${SRCPV}"

inherit autotools ptest pkgconfig lib_package

REQUIRED_TUNE_FEATURES = "neon"
REQUIRED_TUNE_FEATURES_aarch64 = ""

DEPENDS += "gengetopt-native"

PACKAGES =+ "${PN}-tests"

FILES_${PN}-dbg += "${PTEST_PATH}/.debug"

PTEST_PATH = "${libdir}/bayer2rgb/testsuite"
FILES_${PN}-tests += "${libdir}/bayer2rgb/testsuite"
RDEPENDS_${PN}-tests += "bash"
