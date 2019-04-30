SUMMARY = "Linux for TQ-Group Freescale / NXP LS102xA based modules"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

DEPENDS += "lzop-native bc-native openssl-native"

#
# append linux-mainline if we provide mainine kernel versions
#
PROVIDES += "linux-mainline linux-tq"

inherit kernel

SRC_URI = "\
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://defconfig \
"

SRCBRANCH = "TQMLS102xA-linux-v4.14.78-BSP0110"
SRCREV = "4ce6f998570f62d9d7d5738f089a534ba98bf047"

COMPATIBLE_MACHINE = "tqmls102xa"

S = "${WORKDIR}/git"
