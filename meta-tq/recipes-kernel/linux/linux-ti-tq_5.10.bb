SUMMARY = "Linux kernel for TQ-Systems TQMa64xxL modules"

LICENSE = "GPL-2.0-only"
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
SRCREV = "63ff8461253a01cf7f7f7492531cb2e5b5a93976"

COMPATIBLE_MACHINE = "tqma64xxl"

S = "${WORKDIR}/git"
