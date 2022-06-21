SUMMARY = "Linux kernel for TQ-Systems GmbH TQMa57xx modules based on Linux 4.19-rt"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

inherit kernel

DEPENDS += " lzop-native bc-native openssl-native"
PROVIDES += "linux-rt linux-tq"

SRC_URI = "\
    ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
    file://defconfig \
"

SRCBRANCH = "TQMa57xx-linux-v4.19.94-rt39"
SRCREV = "49c7f26292f193e35239dcfb9955264b23000a2b"

COMPATIBLE_MACHINE = "tqma57xx"

S = "${WORKDIR}/git"
