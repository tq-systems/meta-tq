require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "u-boot for TQ-Group TI AM57 based modules"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

# TODO: adopt SRCREV and SRCBRANCH when available on github */
SRCREV = "ff53add599acd131e7af1fd3bebd9eb87c67d8f8"
SRCBRANCH = "private/gateware_lange_stefan/TQMa57xx_devel"

SRC_URI = "git://github.com/tq-systems/u-boot-tqmaxx.git;protocol=https;branch=${SRCBRANCH}"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "(tqma572x-mba57xx)"

SPL_BINARY = "MLO"
