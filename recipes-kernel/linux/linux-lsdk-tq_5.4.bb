SUMMARY = "Linux for TQ-Group Freescale LS1088A/LX2160A module based on LSDK 20.12"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

inherit kernel

DEPENDS += " lzop-native bc-native openssl-native"
#
# append linux-mainline if we provide mainline kernel versions
#
PROVIDES += "linux-tq"

SRCBRANCH = "TQMLSXxx-linux-v5.4-LSDK-20.04"
SRC_URI = "\
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://defconfig \
"
SRCREV = "d04b2ec9d778e2d8883848d3726cdc881a1ad93c"

COMPATIBLE_MACHINE = "tqmls1088a"
COMPATIBLE_MACHINE_append = "|tqmlx2160a"

S = "${WORKDIR}/git"
