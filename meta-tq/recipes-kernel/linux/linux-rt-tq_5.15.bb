PN_BASE = "linux-tq"
require ${PN_BASE}_${PV}.bb

SUMMARY = "Linux kernel with PREEMPT_RT support based on linux mainline v5.15-rt for TQ-Systems GmbH i.MX based modules"

KBRANCH = "TQMaxx-linux-v5.15.y-rt"
SRCREV = "5f3a9a15f55a2ffda06914e3d4b656e492a1acf9"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN_BASE}-${LINUX_RELEASE}:"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

SRC_URI:append = "\
    file://rt.cfg \
"

LINUX_KERNEL_TYPE = "preempt-rt"
