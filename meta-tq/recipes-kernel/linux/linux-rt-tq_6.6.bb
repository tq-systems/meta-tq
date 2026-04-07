SUMMARY = "Linux kernel with PREEMPT_RT support from linux mainline v6.6-rt for TQ-Systems GmbH i.MX based modules"

require linux-tq-6.6.inc

KBRANCH = "TQM-linux-v6.6.y-rt"
SRCREV = "3368c5d0f74dd3c9f2c8798d3aaae2df15dd58ed"

# LINUX_VERSION must match version from Makefile
LINUX_VERSION = "${LINUX_RELEASE}.132"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

KERNEL_EXTRA_CONFIG_FILES += "\
    file://rt.cfg \
    file://revert-expert-settings.cfg \
"

LINUX_KERNEL_TYPE = "preempt-rt"
