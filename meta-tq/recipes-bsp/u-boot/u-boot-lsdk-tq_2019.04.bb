require recipes-bsp/u-boot/u-boot.inc
require u-boot-tq.inc

DESCRIPTION = "u-boot for TQ-Systems GmbH LX2160A based modules"

PROVIDES += "u-boot"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

DEPENDS += "bison-native"

SRCREV = "ae6f79a24209ecbbb44a348ef613590177db85c9"
SRCBRANCH = "TQMLX2160A-v2019.04-lx2160a-early-access-bsp0.7"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

COMPATIBLE_MACHINE = "tqmlx2160a"
