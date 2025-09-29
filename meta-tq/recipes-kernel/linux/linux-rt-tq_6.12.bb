require linux-tq-6.12.inc

SUMMARY = "Linux kernel with PREEMPT_RT support from linux mainline v6.12-rt for TQ-Systems SoMs"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

KBRANCH = "TQM-linux-v6.12.y-rt"
SRCREV_machine = "2ec81266f551b2cdbda34617a99bb8b1db0008ca"
LINUX_VERSION = "${LINUX_RELEASE}.49"

SRC_URI += "\
    file://rt.cfg \
    file://revert-expert-settings.cfg \
"

LINUX_KERNEL_TYPE = "preempt-rt"
