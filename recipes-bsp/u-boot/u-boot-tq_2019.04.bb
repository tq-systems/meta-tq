require u-boot-tq.inc

DESCRIPTION = "u-boot for TQ-Systems GmbH TI AM57 and TI AM335 based modules"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

DEPENDS += "xxd-native bison-native"

SRCREV = "8af2688421a7605670cb4f3e9f62278dd1f9bae9"
SRCBRANCH = "TQMa57xx-u-boot-v2019.04"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

COMPATIBLE_MACHINE = "tqma57xx"
COMPATIBLE_MACHINE_append = "|tqma335x"

SPL_BINARY = "MLO"
