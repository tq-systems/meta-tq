require u-boot-tq.inc

DESCRIPTION = "u-boot for TQ-Systems GmbH TI AM57 and TI AM335 based modules"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

DEPENDS += "xxd-native bison-native"

SRCREV = "b4917b9f544f05563dad72cbbb8605f1688765eb"
SRCBRANCH = "TQMa57xx-u-boot-v2019.04"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

COMPATIBLE_MACHINE = "tqma57xx"
COMPATIBLE_MACHINE:append = "|tqma335x"

SPL_BINARY = "MLO"
