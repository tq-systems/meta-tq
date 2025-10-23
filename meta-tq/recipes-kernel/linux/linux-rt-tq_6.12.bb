SUMMARY = "Linux kernel with PREEMPT_RT support from linux mainline v6.12-rt for TQ-Systems SoMs"

require linux-tq-6.12.inc

KBRANCH = "TQM-linux-v6.12.y-rt"
SRCREV_machine = "2f219c0caec651334ac9f87ec60cfe0d5345ce54"

# LINUX_VERSION must match version from Makefile
LINUX_VERSION = "${LINUX_RELEASE}.54"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

KERNEL_EXTRA_CONFIG_FILES += "\
    file://rt.cfg \
    file://revert-expert-settings.cfg \
"

LINUX_KERNEL_TYPE = "preempt-rt"
