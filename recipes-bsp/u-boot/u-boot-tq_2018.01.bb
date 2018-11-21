require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "u-boot for TQ-Group TI AM57 based modules"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

# TODO: rename branch before pushing to github
SRCREV = "b8cdca2be1f646f3373a05f0ceb1df0b9e10f84c"
SRCBRANCH = "TQMa57xx-u-boot-v2018.01"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "tqma572x-mba57xx|tqma571x-mba57xx|tqma574x-mba57xx"

SPL_BINARY = "MLO"
