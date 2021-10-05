SUMMARY = "Linux kernel with PREEMPT_RT support for TQMLS1017/1028A modules"

inherit kernel

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

DEPENDS += "lzop-native bc-native openssl-native"

PROVIDES += "linux-rt linux-tq"

SRC_URI = "\
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://defconfig \
"

SRCBRANCH = "TQMLS1028A-lf-5.10.y-rt"
SRCREV = "0d4bcc6c5bcb9f79d3459257716ed53be62909dc"

COMPATIBLE_MACHINE = "tqmls1028a"

S = "${WORKDIR}/git"
