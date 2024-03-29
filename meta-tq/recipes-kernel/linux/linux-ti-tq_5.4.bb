SUMMARY = "Linux kernel based on linux-ti for TQ-Systems GmbH TQ AM335x / AM57xx based modules"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

require linux-ti-tq-common.inc

KBRANCH = "TQMa57xx-TI-linux-5.4.y-07.00.00.005-rt"
SRCREV = "27465c83cb19e696fddf06642c011ef92db647fe"
# LINUX_VERSION must match version from Makefile
LINUX_RELEASE = "5.4"
LINUX_VERSION = "${LINUX_RELEASE}.257"

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
COMPATIBLE_MACHINE .= ")$"
