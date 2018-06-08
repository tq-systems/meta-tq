require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "u-boot for TQ-Group TI AM57 based modules"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

SRC_URI = "${TQ_GIT}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

# TODO: rename branch before pushing to github
SRCREV = "fa8dedfa8693d5d01cd038b47b46ec378cced472"
SRCBRANCH = "external/private/gateware_graefe_konrad/TQMa57xx_devel"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "tqma572x-mba57xx|tqma571x-mba57xx"

SPL_BINARY = "MLO"
