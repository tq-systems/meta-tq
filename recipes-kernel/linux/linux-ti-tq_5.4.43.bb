SUMMARY = "Linux kernel based on linux-ti for TQ-Systems GmbH TQ AM335x / AM57xx based modules"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

require linux-ti-tq-common.inc

KBRANCH = "TQMa57xx-TI-linux-5.4.y-07.00.00.005-rt"
SRCREV = "ac339a72addb7b5d41a7a1d34517c1be94ad2946"
# LINUX_VERSION must match version from Makefile
LINUX_RELEASE = "5.4"
LINUX_VERSION = "${LINUX_RELEASE}.43"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${LINUX_VERSION}:"

DEPENDS += " lzop-native bc-native openssl-native"
PROVIDES += "linux-tq"

SRC_URI = "\
    ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${KBRANCH} \
    file://defconfig \
"

SRC_URI_append_tqma335x = "\
    file://config-only-armv7.cfg \
    file://disable-highpte.cfg \
    file://dynamic-debug.cfg \
    file://general-optimizations.cfg \
    file://kallsyms.cfg \
    file://local-version.cfg \
    file://neon.cfg \
    file://pack-lzo.cfg \
    file://remove-debug.cfg \
"

DEFAULT_PREFERENCE = "1"

COMPATIBLE_MACHINE = "^("
COMPATIBLE_MACHINE .= "tqma57xx"
COMPATIBLE_MACHINE .= "|tqma335x"
COMPATIBLE_MACHINE .= ")$"
