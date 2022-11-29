SUMMARY = "Linux kernel based on linux-ti for TQ-Systems GmbH TQ AM335x / AM57xx / AM65xx based modules"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

require linux-ti-tq-common.inc

KBRANCH = "TQMa57xx-TI-linux-5.4.y-07.00.00.005-rt"
SRCREV = "ac339a72addb7b5d41a7a1d34517c1be94ad2946"
# LINUX_VERSION must match version from Makefile
LINUX_RELEASE = "5.4"
LINUX_VERSION = "${LINUX_RELEASE}.43"

KBRANCH:tqma65xx = "TQMa65xx-ti-rt-linux-5.4.y"
SRCREV:tqma65xx = "4437b403c11594bd9d58d35978bd4e33779bc276"
LINUX_VERSION:tqma65xx = "${LINUX_RELEASE}.109"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

DEPENDS += "\
    bc-native \
    lzop-native \
    openssl-native \
"

PROVIDES += "linux-tq"
PROVIDES += "linux-ti-staging"

SRC_URI = "\
    ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${KBRANCH} \
    file://defconfig \
"

SRC_URI:append:tqma335x = "\
    file://config-only-armv7.cfg \
    file://disable-highpte.cfg \
    file://dynamic-debug.cfg \
    file://general-optimizations.cfg \
    file://kallsyms.cfg \
    file://local-version.cfg \
    file://neon.cfg \
    file://pack-lzo.cfg \
    file://remove-debug.cfg \
    file://bpf-support.cfg \
"

SRC_URI:append:tqma57xx = "\
    file://config-only-armv7.cfg \
    file://disable-highpte.cfg \
    file://dynamic-debug.cfg \
    file://general-optimizations.cfg \
    file://kallsyms.cfg \
    file://local-version.cfg \
    file://neon.cfg \
    file://pack-lzo.cfg \
    file://remove-debug.cfg \
    file://bpf-support.cfg \
    file://enable-legacy-drm.cfg \
"

KERNEL_DEVICETREE:append:tqma57xx = "\
    am571x-mba57xx-cdtech-dc44.dtb \
    am571x-mba57xx-edt-etm0700g0edh6.dtb \
    am571x-mba57xx-tianma-tm070jvhg33.dtb \
    am572x-mba57xx-cdtech-dc44.dtb \
    am572x-mba57xx-edt-etm0700g0edh6.dtb \
    am572x-mba57xx-tianma-tm070jvhg33.dtb \
    am574x-mba57xx-cdtech-dc44.dtb \
    am574x-mba57xx-edt-etm0700g0edh6.dtb \
    am574x-mba57xx-tianma-tm070jvhg33.dtb \
"

DEFAULT_PREFERENCE = "1"

COMPATIBLE_MACHINE = "^("
COMPATIBLE_MACHINE .= "tqma335x"
COMPATIBLE_MACHINE .= "|tqma57xx"
COMPATIBLE_MACHINE .= "|tqma65xx"
COMPATIBLE_MACHINE .= ")$"
