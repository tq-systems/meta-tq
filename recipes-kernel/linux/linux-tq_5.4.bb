SUMMARY = "Linux for TQ-Group Freescale / NXP i.MX6/6UL/7 and LS102xA/LS104xA based modules"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

DEPENDS += "lzop-native bc-native openssl-native"

#
# append linux-mainline if we provide mainine kernel versions
#
PROVIDES += "linux-mainline"

inherit kernel

SRC_URI = "\
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://defconfig \
"

SRCBRANCH = "TQMaxx-linux-v5.4.y"
SRCREV = "5f33bc464d83b11e15fcdf7f507ee83578257bc9"

COMPATIBLE_MACHINE = "tqmls102xa"
COMPATIBLE_MACHINE_append = "|tqma7x"
COMPATIBLE_MACHINE_append = "|tqma6x"
COMPATIBLE_MACHINE_append = "|tqma6ulx"
COMPATIBLE_MACHINE_append = "|tqma6ullx"
COMPATIBLE_MACHINE_append = "|tqmls10xxa"

S = "${WORKDIR}/git"
