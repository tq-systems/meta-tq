SUMMARY = "Linux for TQ-Group Freescale LS1088A module based on LSDK 20.12"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

inherit kernel

DEPENDS += " lzop-native bc-native openssl-native"
#
# append linux-mainline if we provide mainline kernel versions
#
PROVIDES += "linux-tq"

SRCBRANCH = "TQMLxxx-linux-lsdk-v5.4.y"
SRC_URI = "\
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://defconfig \
"
SRCREV = "6bf4b562abb861cd21b0c92041de73cca8b4dad3"

COMPATIBLE_MACHINE = "tqmls1088a"

S = "${WORKDIR}/git"
