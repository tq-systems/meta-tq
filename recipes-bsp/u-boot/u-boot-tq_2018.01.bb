require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "u-boot for TQ-Group TI AM57 based modules"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

# TODO: adopt SRCREV and SRCBRANCH when available on github */
SRCREV = "0371136cc61d9f3bba795c2d35af061a641bdd69"
SRCBRANCH = "private/gateware_lange_stefan/TQMa57xx_devel"

# TODO: Test and use following SRC_URI with github meta-tq layer
#SRC_URI = "git://github.com/tq-systems/u-boot-tqmaxx.git;protocol=https;branch=${SRCBRANCH}"
# TODO: remove this SRC_URI when working with github meta-tq layer
SRC_URI = "git:///opt/tqma57xx-bsp/u-boot-tqmaxx/.git;branch=${SRCBRANCH}"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "(tqma572x-mba57xx)"

SPL_BINARY = "MLO"
