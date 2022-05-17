require u-boot-tq.inc

DESCRIPTION = "u-boot for TQ-Systems GmbH TI AM57 and TI AM335 based modules"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

DEPENDS += "xxd-native bison-native"

SRCREV = "0ff7576ba9fa367e4acdde8e07c7f46b30456365"
SRCBRANCH = "TQMa57xx-u-boot-v2019.04"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

COMPATIBLE_MACHINE = "tqma57xx"
COMPATIBLE_MACHINE_append = "|tqma335x"

SPL_BINARY = "MLO"
