SUMMARY = "Linux kernel with PREEMPT_RT support based on linux stable 6.18.y for TQ-Systems SoMs"

require linux-tq_6.18.bb

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

KERNEL_EXTRA_CONFIG_FILES += "\
    file://rt.cfg \
    file://revert-expert-settings.cfg \
"

LINUX_KERNEL_TYPE = "preempt-rt"

COMPATIBLE_MACHINE:tqma335x = "^$"
COMPATIBLE_MACHINE:tqma6x = "^$"
COMPATIBLE_MACHINE:tqma6ulx = "^$"
COMPATIBLE_MACHINE:tqma6ullx = "^$"
COMPATIBLE_MACHINE:tqma7x = "^$"
