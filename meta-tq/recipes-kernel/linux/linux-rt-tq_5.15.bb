PN_BASE = "linux-tq"
require ${PN_BASE}_${PV}.bb

SUMMARY = "Linux kernel with PREEMPT_RT support based on linux mainline v5.15-rt for TQ-Systems GmbH i.MX based modules"

KBRANCH = "TQMaxx-linux-v5.15.y-rt"
SRCREV = "d1b21895f86233e1643d61f0bcfc276c0e3ff61c"

LINUX_VERSION = "${LINUX_RELEASE}.119"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN_BASE}-${LINUX_RELEASE}:"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

SRC_URI:append = "\
    file://rt.cfg \
"

LINUX_KERNEL_TYPE = "preempt-rt"
