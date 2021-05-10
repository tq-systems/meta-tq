SECTION = "bootloaders"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

SRCREV = "fa2e258a0c7be2f6333d17f8c348427185f527e9"
SRCBRANCH = "TQMa8-v2020.04_imx_5.4.47_2.2.0"

SRC_URI = " \
    ${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
"

DEPENDS_append = " bison-native"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "tqma8xx"
COMPATIBLE_MACHINE_append = "|tqma8xxs"
COMPATIBLE_MACHINE_append = "|tqma8qm"
#COMPATIBLE_MACHINE_append = "|tqma8mq"
#COMPATIBLE_MACHINE_append = "|tqma8mxml"
#COMPATIBLE_MACHINE_append = "|tqma8mxnl"
