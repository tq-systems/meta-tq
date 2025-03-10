PN_BASE = "linux-tq"
require ${PN_BASE}_${PV}.bb

SUMMARY = "Linux kernel with PREEMPT_RT support from linux mainline v6.12-rt for TQ-Systems GmbH i.MX based modules"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN_BASE}-${LINUX_RELEASE}:"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

SRC_URI += "\
    file://rt.cfg \
    file://revert-expert-settings.cfg \
"

LINUX_KERNEL_TYPE = "preempt-rt"
