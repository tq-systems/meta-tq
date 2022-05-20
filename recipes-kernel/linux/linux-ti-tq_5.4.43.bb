SUMMARY = "Linux kernel based on linux-ti for TQ-Systems GmbH TQ AM335x / AM57xx based modules"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

require linux-ti-tq-common.inc

KBRANCH = "TQMa57xx-TI-linux-5.4.y-07.00.00.005-rt"
SRCREV = "acb70d0b69f7099d0cafc4ebca38698c19eef7a4"
# LINUX_VERSION must match version from Makefile
LINUX_RELEASE = "5.4"
LINUX_VERSION = "${LINUX_RELEASE}.43"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_VERSION}:"

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
DEFAULT_PREFERENCE = "1"

COMPATIBLE_MACHINE = "^("
COMPATIBLE_MACHINE .= "tqma57xx"
COMPATIBLE_MACHINE .= "|tqma335x"
COMPATIBLE_MACHINE .= ")$"
