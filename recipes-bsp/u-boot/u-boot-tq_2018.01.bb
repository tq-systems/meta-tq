require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "u-boot for TQ-Group TI AM57 based modules"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

SRC_URI = "${TQ_GIT}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

SRCREV = "31ee3b569317c756790dcc95fce869a5b6dbd749"
SRCBRANCH = "private/gateware_lange_stefan/TQMa57xx_devel"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "tqma572x-mba57xx|tqma571x-mba57xx"

SPL_BINARY = "MLO"
