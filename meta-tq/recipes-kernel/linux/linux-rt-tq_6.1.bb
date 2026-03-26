SUMMARY = "Linux kernel with PREEMPT_RT support from linux mainline v6.1-rt for TQ-Systems GmbH i.MX based modules"

require linux-tq-6.1.inc

KBRANCH = "TQM-linux-v6.1.y-rt"
SRCREV = "dd3f74fa996708b2202d5a5cddefd431e765f8a1"

# LINUX_VERSION must match version from Makefile
LINUX_VERSION = "${LINUX_RELEASE}.164"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

KERNEL_EXTRA_CONFIG_FILES += "\
    file://rt.cfg \
    file://revert-expert-settings.cfg \
"

LINUX_KERNEL_TYPE = "preempt-rt"
