require u-boot-tq.inc

DESCRIPTION = "U-Boot for TQ-Systems GmbH Freescale LS1012A based modules"

PROVIDES += "u-boot"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=5a7450c57ffe5ae63fd732446b988025"

DEPENDS += "bison-native"

SRCREV = "37790eff05828efa51c407c7031b8f18c4afa3f4"
SRCBRANCH = "TQM-lf-5.15"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

COMPATIBLE_MACHINE = "tqmls1012al"
