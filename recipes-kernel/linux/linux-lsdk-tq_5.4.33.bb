SUMMARY = "Linux for TQ-Group NXP LX2160A module based on LSDK 20.04"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

inherit kernel

DEPENDS += " lzop-native bc-native openssl-native"
PROVIDES += "linux-tq"

SRC_URI = " \
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://defconfig \
"

SRCBRANCH_tqmlx2160a = "TQMLX2160a-linux-v5.4-LSDK-20.04"
SRCREV_tqmlx2160a = "345ead1ab678578eba3f625a3eefb30fe4b7e534"

COMPATIBLE_MACHINE = "tqmlx2160a"

S = "${WORKDIR}/git"
