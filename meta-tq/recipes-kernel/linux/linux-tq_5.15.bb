SUMMARY = "Linux kernel based on linux mainline 5.15.y for TQ-Systems GmbH i.MX based modules"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require linux-tq-common.inc

KBRANCH = "TQMaxx-linux-v5.15.y"
SRCREV = "1524008258f7be129b5e75cd39c6325ed91c1c15"

# LINUX_VERSION must match version from Makefile
LINUX_RELEASE = "5.15"
LINUX_VERSION = "${LINUX_RELEASE}.104"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

SRC_URI = "\
    ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${KBRANCH} \
    file://defconfig \
    ${@bb.utils.contains('COMBINED_FEATURES', 'bluetooth', 'file://features/bluetooth-support.cfg', '', d)} \
    ${@bb.utils.contains('COMBINED_FEATURES', 'wifi', 'file://features/wifi-support.cfg', '', d)} \
"

DEFAULT_PREFERENCE = "0"

COMPATIBLE_MACHINE = "^("
COMPATIBLE_MACHINE .= "tqma6x"
COMPATIBLE_MACHINE .= "|tqma6ulx"
COMPATIBLE_MACHINE .= "|tqma6ullx"
COMPATIBLE_MACHINE .= "|tqma7x"
COMPATIBLE_MACHINE .= ")$"
