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
SRCREV = "dd205af69023490215299e3d531fd93a3a366e9b"
SRCREV_tqmls10xxa = "956cb2816c6e429800baca85f0a57dce778cb86e"

COMPATIBLE_MACHINE = "tqmls102xa"
COMPATIBLE_MACHINE_append = "|tqma7x"
COMPATIBLE_MACHINE_append = "|tqma6x"
COMPATIBLE_MACHINE_append = "|tqma6ulx"
COMPATIBLE_MACHINE_append = "|tqma6ullx"
COMPATIBLE_MACHINE_append = "|tqmls10xxa"

S = "${WORKDIR}/git"
