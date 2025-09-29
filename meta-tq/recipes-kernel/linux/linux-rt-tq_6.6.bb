SUMMARY = "Linux kernel with PREEMPT_RT support from linux mainline v6.6-rt for TQ-Systems GmbH i.MX based modules"

require linux-tq-6.6.inc

KBRANCH = "TQM-linux-v6.6.y-rt"
SRCREV = "04cf144da31fce996a37f142c299baf37a2d1c8d"

# LINUX_VERSION must match version from Makefile
LINUX_VERSION = "${LINUX_RELEASE}.106"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

LINUX_KERNEL_TYPE = "preempt-rt"

KERNEL_EXTRA_CONFIG_FILES += "\
    file://rt.cfg \
    file://revert-expert-settings.cfg \
"

