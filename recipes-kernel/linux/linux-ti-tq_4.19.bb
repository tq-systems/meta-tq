SUMMARY = "Linux kernel for TQ-Systems GmbH TI AM57 based modules"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

inherit kernel

DEPENDS += "lzop-native bc-native"
#
# append linux-mainline if we provide mainline kernel versions
#
PROVIDES += "linux-tq"

SRC_URI = "\
    ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
    file://defconfig \
"

SRCBRANCH = "TQMa57xx-linux-v4.19.94-rt39-ti2019.06-rt"
SRCREV = "6b9a8c843f219022a6e443b7cd601e30759f5b12"

COMPATIBLE_MACHINE = "tqma57xx"

S = "${WORKDIR}/git"
