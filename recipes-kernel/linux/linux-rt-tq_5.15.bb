PN_BASE = "linux-tq"
require ${PN_BASE}_${PV}.bb

SUMMARY = "Linux kernel with PREEMPT_RT support based on linux mainline v5.15-rt for TQ-Systems GmbH i.MX based modules"

KBRANCH = "TQMaxx-linux-v5.15.y-rt"
SRCREV = "8bbe98b675ce3bcb4ec524f54617d5f3d2789f6d"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN_BASE}-${LINUX_RELEASE}:"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

SRC_URI_append = "\
    file://rt.cfg \
"

LINUX_KERNEL_TYPE = "preempt-rt"
