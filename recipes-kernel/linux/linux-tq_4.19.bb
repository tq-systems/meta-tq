SUMMARY = "Linux for TQ-Group Freescale LS10XxA based modules"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

inherit kernel

DEPENDS += "lzop-native bc-native"
#
# append linux-mainline if we provide mainline kernel versions
#
PROVIDES = "virtual/kernel linux-mainline"

SRC_URI = "\
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://defconfig \
"

SRCBRANCH = "TQMLS10xxA-linux-4.19.0"
SRCREV = "381d81f3d01adc9b3c1372416bc6fdc524f91918"

COMPATIBLE_MACHINE = "tqmls10xxa"
COMPATIBLE_MACHINE .= "|tqmls1046a-mbls10xxa"

S = "${WORKDIR}/git"
