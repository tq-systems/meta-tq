SUMMARY = "Linux kernel with PREEMPT_RT support from linux mainline v6.6-rt for TQ-Systems GmbH i.MX based modules"

require linux-tq-6.6.inc

KBRANCH = "TQM-linux-v6.6.y-rt"
SRCREV = "fba7071a3ebf11726027daf97627b99d1991dfe4"

# LINUX_VERSION must match version from Makefile
LINUX_VERSION = "${LINUX_RELEASE}.142"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

KERNEL_EXTRA_CONFIG_FILES += "\
    file://rt.cfg \
    file://revert-expert-settings.cfg \
"

LINUX_KERNEL_TYPE = "preempt-rt"
