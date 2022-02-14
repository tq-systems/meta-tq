SUMMARY = "Linux kernel for TQ-Systems TQMa65xx modules"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

inherit kernel

DEPENDS += " lzop-native bc-native openssl-native"
PROVIDES += "linux-tq"

SRC_URI = " \
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://defconfig \
"

SRCBRANCH = "TQMa65xx-ti-rt-linux-5.4.y"
SRCREV = "9bbdb0df80b3508bc2dacccb92aaa63f6ec25f61"

COMPATIBLE_MACHINE = "tqma65xx"

S = "${WORKDIR}/git"
