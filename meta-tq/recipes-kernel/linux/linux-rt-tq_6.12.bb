require linux-tq-6.12.inc

SUMMARY = "Linux kernel with PREEMPT_RT support from linux mainline v6.12-rt for TQ-Systems SoMs"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

SRC_URI += "\
    file://rt.cfg \
    file://revert-expert-settings.cfg \
"

LINUX_KERNEL_TYPE = "preempt-rt"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:tqma8mpxl = "tqma8mpxl"
COMPATIBLE_MACHINE:tqma8mpxs = "tqma8mpxs"
COMPATIBLE_MACHINE:tqma93xx = "tqma93xx"
