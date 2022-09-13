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
SRCREV = "16c18bed076c4c7d583f7f18d72b2fa4f2a1e9db"

COMPATIBLE_MACHINE = "tqmls102xa"
COMPATIBLE_MACHINE:append = "|tqma7x"
COMPATIBLE_MACHINE:append = "|tqma6x"
COMPATIBLE_MACHINE:append = "|tqma6ulx"
COMPATIBLE_MACHINE:append = "|tqma6ullx"

S = "${WORKDIR}/git"