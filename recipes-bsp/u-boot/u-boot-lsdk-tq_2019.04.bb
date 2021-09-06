require u-boot-tq.inc

DESCRIPTION = "u-boot for TQ-Group LX2160A based modules"

PROVIDES += "u-boot"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

DEPENDS += "bison-native"

SRCREV = "74382e0e5b140d3808ae3b5bc23fc497d02998f5"
SRCBRANCH = "TQMLX2160A-v2019.04-lx2160a-early-access-bsp0.7"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

COMPATIBLE_MACHINE = "tqmlx2160a"
