SUMMARY = "Linux kernel with PREEMPT_RT support from linux mainline v6.6-rt for TQ-Systems GmbH i.MX based modules"

require linux-tq-6.6.inc

KBRANCH = "TQM-linux-v6.6.y-rt"
SRCREV = "4e10eb357745df8be17508e5ca960bb1e3cba3dc"

# LINUX_VERSION must match version from Makefile
LINUX_VERSION = "${LINUX_RELEASE}.126"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

KERNEL_EXTRA_CONFIG_FILES += "\
    file://rt.cfg \
    file://revert-expert-settings.cfg \
"

LINUX_KERNEL_TYPE = "preempt-rt"
