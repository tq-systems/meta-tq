SUMMARY = "Linux kernel based on linux stable 6.1.y for TQ-Systems GmbH i.MX based modules"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require linux-tq-common.inc

KBRANCH = "linux-6.1.y"
SRCREV = "76ba310227d2490018c271f1ecabb6c0a3212eb0"

# LINUX_VERSION must match version from Makefile
LINUX_RELEASE = "6.1"
LINUX_VERSION = "${LINUX_RELEASE}.32"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

SRC_URI = "\
    git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git;protocol=https;branch=${KBRANCH} \
    file://imx.cfg \
    file://rm-non-tq-platforms.cfg \
"

KBUILD_DEFCONFIG = "defconfig"

DEFAULT_PREFERENCE = "0"

COMPATIBLE_MACHINE = "^("
COMPATIBLE_MACHINE .= "tqma8mq"
COMPATIBLE_MACHINE .= "|tqma8mxml"
COMPATIBLE_MACHINE .= "|tqma8mxnl"
COMPATIBLE_MACHINE .= "|tqma8mpxl"
COMPATIBLE_MACHINE .= ")$"
