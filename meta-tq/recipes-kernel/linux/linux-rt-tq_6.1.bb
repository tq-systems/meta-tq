SUMMARY = "Linux kernel with PREEMPT_RT support from linux mainline v6.1-rt for TQ-Systems GmbH i.MX based modules"

require linux-tq-6.1.inc

KBRANCH = "TQM-linux-v6.1.y-rt"
SRCREV = "09307d4a4d7f14c512551f6f63ed3bb96e2650b1"

# LINUX_VERSION must match version from Makefile
LINUX_VERSION = "${LINUX_RELEASE}.132"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

KERNEL_EXTRA_CONFIG_FILES += "\
    file://rt.cfg \
    file://revert-expert-settings.cfg \
"

LINUX_KERNEL_TYPE = "preempt-rt"
