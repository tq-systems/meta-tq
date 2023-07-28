require recipes-bsp/u-boot/u-boot.inc
require u-boot-tq.inc

DESCRIPTION = "U-Boot for TQ-Systems GmbH Freescale LS10xx based modules"

PROVIDES += "u-boot"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=5a7450c57ffe5ae63fd732446b988025"

DEPENDS += "bison-native"

SRCREV = "3db82a3db1ad9137a1de4e5acb30308d4a8f8b4e"
SRCBRANCH = "TQMxx-lf_v2022.04"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

COMPATIBLE_MACHINE = "tqmls10xxa"
