SUMMARY = "Linux kernel for TQ-Group TQ TQMa65xx modules"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

inherit kernel

DEPENDS += " lzop-native bc-native openssl-native"
PROVIDES += "linux-tq"

SRC_URI = " \
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://defconfig \
"

SRCBRANCH = "TQMa65xx-linux-v5.4.40-ti-5.4.y"
SRCREV = "0b78a6f31d5c8b3fd45bac573050cb4a0a056a48"

COMPATIBLE_MACHINE = "tqma65xx"

S = "${WORKDIR}/git"
