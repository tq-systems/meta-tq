DESCRIPTION = "ARM Trusted Firmware"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://license.rst;md5=1dd070c98a281d18d9eefd938729b031"

ATF_BRANCH ?= "lf_v2.12"
ATF_SRC ?= "git://github.com/nxp-qoriq/atf.git;protocol=https"
SRC_URI = "${ATF_SRC};branch=${ATF_BRANCH}"
SRCREV = "7e62e7d69e6becf756d16af0aa66c26d99a254dc"

S = "${WORKDIR}/git"
