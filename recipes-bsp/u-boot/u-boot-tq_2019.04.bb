require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "u-boot for TQ-Group TI AM57 based modules"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

DEPENDS_append = " bison-native"

PROVIDES += "u-boot"

SRCREV = "59abdf0f29a39be38e3cab402e7ad791dfbcbb4e"
SRCBRANCH = "TQMa57xx-u-boot-v2019.04"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "tqma57xx"

SPL_BINARY = "MLO"
