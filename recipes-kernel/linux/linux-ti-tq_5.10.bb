SUMMARY = "Linux kernel for TQ-Systems TQMa64xxL modules"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

inherit kernel
require ti-kernel.inc

DEPENDS += " lzop-native bc-native openssl-native"
PROVIDES += "linux-tq"

KERNEL_EXTRA_ARGS += "${EXTRA_DTC_ARGS}"

SRC_URI = " \
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://defconfig \
"

SRCBRANCH = "TQMaxx-ti-rt-linux-5.10.y"
SRCREV = "286543510fba22a9d0a4a34d8d3a1a2bd0901147"

COMPATIBLE_MACHINE = "tqma64xxl"

S = "${WORKDIR}/git"
