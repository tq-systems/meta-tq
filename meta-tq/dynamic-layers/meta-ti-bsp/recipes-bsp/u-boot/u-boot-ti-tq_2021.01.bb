require recipes-bsp/u-boot/u-boot-ti.inc
require recipes-bsp/u-boot/u-boot-tq.inc

DESCRIPTION = "U-boot for TQ-Systems TI AM64 based modules"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=5a7450c57ffe5ae63fd732446b988025"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

SRCBRANCH = "TQMaxx-ti-u-boot-2021.01"
SRCREV = "f6338d9743841a4e3d62b7ccd42603044112aa72"

DEPENDS += "python3-setuptools-native"

COMPATIBLE_MACHINE = "tqma64xxl"
