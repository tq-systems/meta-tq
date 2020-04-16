SUMMARY = "Linux for TQ-Group Freescale LS10XxA based modules"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

inherit kernel

DEPENDS += " lzop-native bc-native openssl-native"
#
# append linux-mainline if we provide mainline kernel versions
#
PROVIDES += "linux-mainline linux-tq"

SRC_URI = "\
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://defconfig;md5=9dc87e929994ceb86a9fdfb03a87504e \
"

SRCBRANCH = "TQMLS10xxA-linux-4.19.0"
SRCREV = "49070219f52a4a36072c122d06b63760ef5d2ccc"


COMPATIBLE_MACHINE = "tqmls10xxa"

S = "${WORKDIR}/git"
