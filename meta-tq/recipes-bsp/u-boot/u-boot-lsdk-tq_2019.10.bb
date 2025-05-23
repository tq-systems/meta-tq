require recipes-bsp/u-boot/u-boot.inc
require u-boot-tq.inc

DESCRIPTION = "U-Boot for TQ-Systems GmbH Freescale LS1012A/LS1028A based modules"

PROVIDES += "u-boot"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

DEPENDS += "bison-native"

SRCREV = "1051ca922bef1a292953c06d0e846984accff325"
SRCBRANCH = "TQMLSxx-LSDK-20.04-update-290520"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:tqmls1028a = "tqmls1028a"
