SUMMARY = "Linux kernel with PREEMPT_RT support for TQ-Systems GmbH Layerscape based modules"

inherit kernel

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

DEPENDS += "lzop-native bc-native openssl-native"

PROVIDES += "linux-rt linux-tq"

inherit kernel

SRC_URI = "\
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://defconfig \
"

SRCBRANCH = "TQMaxx-LSDK-20.12-V5.4-RT"
SRCREV = "ae195e3b32c3527d062d7c5ddb957819ffb92649"

COMPATIBLE_MACHINE = "tqmls1028a"

S = "${WORKDIR}/git"
