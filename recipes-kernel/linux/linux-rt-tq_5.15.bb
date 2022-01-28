PN_BASE = "linux-tq"
require ${PN_BASE}_${PV}.bb

SUMMARY = "Linux kernel with PREEMPT_RT support based on linux mainline v5.15-rt for TQ-Systems GmbH i.MX based modules"

KBRANCH = "TQMaxx-linux-v5.15.y-rt"
SRCREV = "a6a8aed2ddcc3717f22d539bb45fa1f1f6e8509e"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN_BASE}-${LINUX_RELEASE}:"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

SRC_URI_append = "\
    file://rt.cfg \
"

LINUX_KERNEL_TYPE = "preempt-rt"
