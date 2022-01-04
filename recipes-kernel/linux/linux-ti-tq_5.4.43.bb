SUMMARY = "Linux kernel for TQ-Systems GmbH TQ AM57xx modules"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

inherit kernel

DEPENDS += " lzop-native bc-native openssl-native"
PROVIDES += "linux-tq"

SRC_URI = " \
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://defconfig \
"

SRCBRANCH = "TQMa57xx-TI-linux-5.4.y-07.00.00.005-rt"
SRCREV = "acb70d0b69f7099d0cafc4ebca38698c19eef7a4"


COMPATIBLE_MACHINE = "tqma57xx"
COMPATIBLE_MACHINE_append = "|tqma335x"

S = "${WORKDIR}/git"
