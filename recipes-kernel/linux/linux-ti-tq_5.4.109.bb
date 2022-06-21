SUMMARY = "Linux kernel for TQ-Systems TQMa65xx modules"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

inherit kernel

DEPENDS += "\
    bc-native \
    lzop-native \
    openssl-native \
"

PROVIDES += "linux-tq"
PROVIDES += "linux-ti-staging"

SRC_URI = "\
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://defconfig \
"

SRCBRANCH = "TQMa65xx-ti-rt-linux-5.4.y"
SRCREV = "4437b403c11594bd9d58d35978bd4e33779bc276"

COMPATIBLE_MACHINE = "tqma65xx"

S = "${WORKDIR}/git"
