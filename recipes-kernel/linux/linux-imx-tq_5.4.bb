SUMMARY = "Linux kernel based on linux-imx for TQ-Systems GmbH i.MX / LS based modules"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

require linux-imx-tq-common.inc

KBRANCH = "TQMaxx-fslc-5.4-1.0.0-imx"
SRCREV = "2b11415c757089214777f8f5198282e154beff8f"

# LINUX_VERSION must match version from Makefile
LINUX_RELEASE = "5.4"
LINUX_VERSION = "${LINUX_RELEASE}.44"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

SRC_URI = "\
    ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${KBRANCH} \
    file://defconfig \
"

DEFAULT_PREFERENCE = "0"

COMPATIBLE_MACHINE = "^("
COMPATIBLE_MACHINE .= "tqma7x"
COMPATIBLE_MACHINE .= "|tqma6x"
COMPATIBLE_MACHINE .= "|tqma6ulx"
COMPATIBLE_MACHINE .= "|tqma6ullx"
COMPATIBLE_MACHINE .= "|tqmls1012al"
COMPATIBLE_MACHINE .= "|tqmls1028a"
COMPATIBLE_MACHINE .= ")$"
