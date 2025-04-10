SUMMARY = "Linux kernel with PREEMPT_RT support from linux mainline v6.1-rt for TQ-Systems GmbH i.MX based modules"

require linux-tq-6.1.inc

KBRANCH = "TQM-linux-v6.1.y-rt"
SRCREV = "223a1ca75db71dc73ebfc8e82a5ba0d85c4f0f72"

# LINUX_VERSION must match version from Makefile
LINUX_VERSION = "${LINUX_RELEASE}.119"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

KERNEL_EXTRA_CONFIG_FILES += "\
    file://rt.cfg \
    file://revert-expert-settings.cfg \
"

LINUX_KERNEL_TYPE = "preempt-rt"
