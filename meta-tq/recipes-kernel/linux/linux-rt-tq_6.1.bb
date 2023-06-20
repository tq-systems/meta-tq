PN_BASE = "linux-tq"
require ${PN_BASE}_${PV}.bb

SUMMARY = "Linux kernel with PREEMPT_RT support from linux mainline v6.1-rt for TQ-Systems GmbH i.MX based modules"

KBRANCH = "v6.1-rt"
SRCREV = "3f9ca5f166e5796bd21176f356a479e505ce7760"
KERNEL_SOURCE = "git://git.kernel.org/pub/scm/linux/kernel/git/rt/linux-stable-rt.git"

# LINUX_VERSION must match version from Makefile
LINUX_VERSION = "${LINUX_RELEASE}.33"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN_BASE}-${LINUX_RELEASE}:"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

SRC_URI += "\
    file://rt.cfg \
    file://revert-expert-settings.cfg \
"

LINUX_KERNEL_TYPE = "preempt-rt"
