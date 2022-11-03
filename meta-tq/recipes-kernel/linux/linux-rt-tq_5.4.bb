SUMMARY = "Linux with PREEMPT_RT support for TQ-Systems GmbH Freescale / NXP LS102xA based modules"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

DEPENDS += "lzop-native bc-native openssl-native"

PROVIDES += "linux-rt linux-tq"

inherit kernel

SRC_URI = "\
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://defconfig \
"

SRCBRANCH = "TQMaxx-linux-v5.4.y-rt"
SRCREV = "828cb3af0a0e565cd986bc0bfc788a29e5591d83"

COMPATIBLE_MACHINE = "tqmls102xa"
COMPATIBLE_MACHINE:append = "|tqma7x"
COMPATIBLE_MACHINE:append = "|tqma6x"
COMPATIBLE_MACHINE:append = "|tqma6ulx"
COMPATIBLE_MACHINE:append = "|tqma6ullx"

S = "${WORKDIR}/git"
