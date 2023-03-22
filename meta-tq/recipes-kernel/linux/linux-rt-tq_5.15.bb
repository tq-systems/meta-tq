PN_BASE = "linux-tq"
require ${PN_BASE}_${PV}.bb

SUMMARY = "Linux kernel with PREEMPT_RT support based on linux mainline v5.15-rt for TQ-Systems GmbH i.MX based modules"

KBRANCH = "TQMaxx-linux-v5.15.y-rt"
SRCREV = "38a7ba93dfd7b92236f177721827ef549eaaf877"

LINUX_VERSION = "${LINUX_RELEASE}.96"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN_BASE}-${LINUX_RELEASE}:"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

SRC_URI:append = "\
    file://rt.cfg \
"

LINUX_KERNEL_TYPE = "preempt-rt"
