SUMMARY = "Linux for TQ-Group Freescale LS1012A based modules"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

DEPENDS += "lzop-native bc-native"

#
# append linux-mainline if we provide mainine kernel versions
#
PROVIDES = "virtual/kernel"

inherit kernel

SRC_URI = "\
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://defconfig \
"

SRCBRANCH = "TQMLS1012AL-bringup-LSDK-18.03-V4.14"
SRCREV = "7265353d3b62d66ffdab6f911999d70756b4dce0"

COMPATIBLE_MACHINE = "(tqmls1012al-mbls1012al)"

S = "${WORKDIR}/git"
