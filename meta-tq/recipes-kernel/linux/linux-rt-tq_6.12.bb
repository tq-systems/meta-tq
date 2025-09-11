require linux-tq-6.12.inc

SUMMARY = "Linux kernel with PREEMPT_RT support from linux mainline v6.12-rt for TQ-Systems SoMs"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

KBRANCH = "TQM-linux-v6.12.y-rt"
SRCREV_machine = "aae546fd738a8ad8202d5904755b3af14a1236a5"

SRC_URI += "\
    file://rt.cfg \
    file://revert-expert-settings.cfg \
"

LINUX_KERNEL_TYPE = "preempt-rt"
