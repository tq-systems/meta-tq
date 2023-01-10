SUMMARY = "Linux kernel based on linux mainline 5.15.y for TQ-Systems GmbH TQMT104x modules"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
PROVIDES = "linux-tqmt"

require linux-tq-common.inc

KBRANCH = "TQMT10xx-linux-v5.15"
SRCREV = "0e97f6856c078d6e98dd1c9c798c8423a36ad7e1"

# LINUX_VERSION must match version from Makefile
LINUX_RELEASE = "5.15"
LINUX_VERSION = "${LINUX_RELEASE}.86"


FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

SRC_URI = "\
    ${TQ_GIT_BASEURL}/linux-tqmt.git;protocol=${TQ_GIT_PROTOCOL};branch=${KBRANCH} \
    file://defconfig \
    ${@bb.utils.contains('COMBINED_FEATURES', 'bluetooth', 'file://features/bluetooth-support.cfg', '', d)} \
    ${@bb.utils.contains('COMBINED_FEATURES', 'wifi', 'file://features/wifi-support.cfg', '', d)} \
"

COMPATIBLE_MACHINE = "(tqmt10xx)"
