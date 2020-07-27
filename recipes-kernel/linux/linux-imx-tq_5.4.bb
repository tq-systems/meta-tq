SUMMARY = "Linux kernel for TQ-Group Freescale i.MX based modules"

include recipes-kernel/linux/linux-imx.inc

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

DEPENDS += "lzop-native bc-native openssl-native"

PROVIDES += "linux-imx"

inherit kernel

SRC_URI = "\
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://defconfig \
"

SRCBRANCH = "TQMaxx-fslc-5.4-1.0.0-imx"
SRCREV = "fa6b9c508c9cd6c239a42cd11d551219ca930e67"

COMPATIBLE_MACHINE = "tqma7x"
COMPATIBLE_MACHINE_append = "|tqma6x"
COMPATIBLE_MACHINE_append = "|tqma6ulx"
COMPATIBLE_MACHINE_append = "|tqma6ullx"
COMPATIBLE_MACHINE_append = "|tqmls1012al"
COMPATIBLE_MACHINE_append = "|tqmls1028a"

S = "${WORKDIR}/git"
