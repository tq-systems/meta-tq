SUMMARY = "Linux for TQ-Group NXP LS1028A module based on LSDK 20.04"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

inherit kernel

DEPENDS += " lzop-native bc-native openssl-native"
PROVIDES += "linux-tq"

SRC_URI = " \
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://defconfig \
"

SRCBRANCH = "TQMLSxx-linux-v5.4-LSDK-20.04"
SRCREV = "2623b63143a1820e8e550ed67246a80a6ccbe8df"


COMPATIBLE_MACHINE = "tqmls1028a"

S = "${WORKDIR}/git"
