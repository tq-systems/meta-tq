require u-boot-tq.inc

DESCRIPTION = "U-Boot for TQ-Systems GmbH IMX based modules"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

DEPENDS += "xxd-native"

SRCREV = "86a65bceb893edf33f996948a11e461979ace69b"
SRCBRANCH = "TQMaxx2-v2016.03-rel_imx_4.1.15_2.0.0_ga"

SRC_URI = "\
    ${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
"

COMPATIBLE_MACHINE = "tqma7x"
COMPATIBLE_MACHINE_append = "|tqma6ulx"
COMPATIBLE_MACHINE_append = "|tqma6ullx"
COMPATIBLE_MACHINE_append = "|tqma6x"
