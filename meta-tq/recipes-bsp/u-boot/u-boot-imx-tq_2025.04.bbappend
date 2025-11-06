
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

DISTROBOOT_EXTRA_SOURCES = ""

DISTROBOOT_EXTRA_SOURCES:tqma91xx = "\
    file://loadaddr.cfg \
"

DISTROBOOT_EXTRA_SOURCES:tqma93xx = "\
    file://loadaddr.cfg \
"

SRC_URI += "${DISTROBOOT_EXTRA_SOURCES}"
