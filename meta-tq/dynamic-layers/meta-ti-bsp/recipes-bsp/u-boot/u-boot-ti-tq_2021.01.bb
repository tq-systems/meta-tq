require recipes-bsp/u-boot/u-boot-ti.inc
require recipes-bsp/u-boot/u-boot-tq.inc

DESCRIPTION = "U-boot for TQ-Systems TI AM64 based modules"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=5a7450c57ffe5ae63fd732446b988025"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

SRCBRANCH = "TQMaxx-ti-u-boot-2021.01"
SRCREV = "c13ade7473e7c8dc8b54546d1d1aa09a6c1e6f68"

DEPENDS += "python3-setuptools-native"

COMPATIBLE_MACHINE = "tqma64xxl"
