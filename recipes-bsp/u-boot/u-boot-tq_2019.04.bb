require u-boot-tq.inc

DESCRIPTION = "u-boot for TQ-Group TI AM57 and TI AM335 based modules"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

DEPENDS += "xxd-native bison-native"

SRCREV = "d472edad92a4c75d6187a38e51fabc5b0d9f34b8"
SRCBRANCH = "TQMa57xx-u-boot-v2019.04"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

COMPATIBLE_MACHINE = "tqma57xx"
COMPATIBLE_MACHINE_append = "|tqma335x"

SPL_BINARY = "MLO"
