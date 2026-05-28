SUMMARY = "Linux kernel for TQ-Systems TQMa64xxL modules"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require linux-tq-unsupported.inc
require linux-ti-tq-common.inc

KBRANCH = "TQMaxx-ti-rt-linux-6.1.y"
SRCREV = "6b7c5b0184afa29ad5554776408cfd872ea2e69d"
# LINUX_VERSION must match version from Makefile
LINUX_RELEASE = "6.1"
LINUX_VERSION = "${LINUX_RELEASE}.83"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

DEPENDS += "\
    bc-native \
    lzop-native \
    openssl-native \
"

PROVIDES += "linux-tq"
PROVIDES += "linux-ti-staging"

SRC_URI = "\
    ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${KBRANCH} \
    file://defconfig \
    file://bpf-support.cfg \
    file://devicetree.cfg \
    file://dynamic-debug.cfg \
    file://general-optimizations.cfg \
    file://kallsyms.cfg \
    file://local-version.cfg \
    file://remove-debug.cfg \
"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:tqma62xx = "tqma62xx"
COMPATIBLE_MACHINE:tqma64xxl = "tqma64xxl"
