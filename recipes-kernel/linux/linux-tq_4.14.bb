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

SRCBRANCH = "TQMaxx-linux-v4.14.78"
SRCREV = "1d020ed3f825d09f37d58d522e2500fcf40a9275"

COMPATIBLE_MACHINE = "tqmls102xa"
COMPATIBLE_MACHINE_append = "|tqma7x"
COMPATIBLE_MACHINE_append = "|tqma6x"

S = "${WORKDIR}/git"
