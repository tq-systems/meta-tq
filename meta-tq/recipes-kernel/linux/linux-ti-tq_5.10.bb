SUMMARY = "Linux kernel for TQ-Systems TQMa64xxL modules"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require linux-ti-tq-common.inc

KBRANCH = "TQMaxx-ti-rt-linux-5.10.y"
SRCREV = "78aebe7dab3d6b4321e889e94f4b21a4ca63701c"
# LINUX_VERSION must match version from Makefile
LINUX_RELEASE = "5.10"
LINUX_VERSION = "${LINUX_RELEASE}.152"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

KERNEL_EXTRA_ARGS += "${EXTRA_DTC_ARGS}"

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
"

SRC_URI:append:tqma57xx = "\
    file://bpf-support.cfg \
    file://devicetree.cfg \
    file://dynamic-debug.cfg \
    file://general-optimizations.cfg \
    file://kallsyms.cfg \
    file://local-version.cfg \
    file://remove-debug.cfg \
"

SRC_URI:append:tqma64xxl = "\
    file://bpf-support.cfg \
    file://devicetree.cfg \
    file://dynamic-debug.cfg \
    file://general-optimizations.cfg \
    file://kallsyms.cfg \
    file://local-version.cfg \
    file://remove-debug.cfg \
"

COMPATIBLE_MACHINE = "tqma57xx|tqma64xxl"
