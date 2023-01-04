require recipes-bsp/u-boot/u-boot.inc
require u-boot-tq.inc

DESCRIPTION = "U-Boot for TQ-Systems GmbH Freescale LS1012A/LS1028A based modules"

PROVIDES += "u-boot"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

DEPENDS += "bison-native"

SRCREV = "6cbf2dd66cf82e3096c93b9f4daa1769b6a7433a"
SRCBRANCH = "TQMLSxx-LSDK-20.04-update-290520"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

COMPATIBLE_MACHINE = "tqmls1012al"
COMPATIBLE_MACHINE:append = "|tqmls1028a-mbls1028a"
