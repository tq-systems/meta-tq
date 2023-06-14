SUMMARY = "Linux kernel based on linux stable 6.1.y for TQ-Systems GmbH Layerscape based modules"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require linux-tq.inc

KBRANCH = "TQM-linux-v6.1.y"
SRCREV = "a3c4397dc097d5642441b87e4bb3782fc1cd5953"

# LINUX_VERSION must match version from Makefile
LINUX_RELEASE = "6.1"
LINUX_VERSION = "${LINUX_RELEASE}.35"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

SRC_URI = "\
    ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${KBRANCH} \
    file://defconfig \
"

COMPATIBLE_MACHINE = "tqmls10xxa"
