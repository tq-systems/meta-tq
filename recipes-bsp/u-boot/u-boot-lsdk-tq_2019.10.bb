require u-boot-tq.inc

DESCRIPTION = "U-Boot for TQ-Systems GmbH Freescale LS1012A/LS1028A based modules"

PROVIDES += "u-boot"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

DEPENDS += "bison-native"

SRCREV = "c986ee772449f591bc3474f9dca726058b3cb95b"
SRCBRANCH = "TQMLSxx-LSDK-20.04-update-290520"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

COMPATIBLE_MACHINE = "tqmls1012al"
COMPATIBLE_MACHINE_append = "|tqmls1028a-mbls1028a"
