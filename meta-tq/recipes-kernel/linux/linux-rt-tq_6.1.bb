PN_BASE = "linux-tq"
require ${PN_BASE}_${PV}.bb

SUMMARY = "Linux kernel with PREEMPT_RT support from linux mainline v6.1-rt for TQ-Systems GmbH i.MX based modules"

KBRANCH = "TQM-linux-v6.1.y-rt"
SRCREV = "84b5694083d96a55b6e1d5123b9d01e0ddfa53da"

# LINUX_VERSION must match version from Makefile
LINUX_VERSION = "${LINUX_RELEASE}.103"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN_BASE}-${LINUX_RELEASE}:"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

SRC_URI += "\
    file://rt.cfg \
    file://revert-expert-settings.cfg \
"

LINUX_KERNEL_TYPE = "preempt-rt"
