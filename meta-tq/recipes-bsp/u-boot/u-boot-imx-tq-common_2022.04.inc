# Copyright (C) 2022 TQ-Systems GmbH

SECTION = "bootloaders"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=5a7450c57ffe5ae63fd732446b988025"

SRCREV = "d7fa6108d0ccaacb576bfd1291576c5d70b64e51"
SRCBRANCH = "TQMaxx-lf_v2022.04"

SRC_URI = "\
    ${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
"

DEPENDS:append = " bison-native"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "^("
COMPATIBLE_MACHINE .= "|tqma93xx"
COMPATIBLE_MACHINE .= "|mx93-generic-bsp"
COMPATIBLE_MACHINE .= ")$"
