SECTION = "bootloaders"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

SRCREV = "8f6302a3f2f993dd92953a2e782e67f88bcd9ec5"
SRCBRANCH = "TQMa8-v2019.04-lf-5.4.y-1.0.0"

SRC_URI = " \
    ${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "tqma8xx"
COMPATIBLE_MACHINE_append = "|tqma8xxs"
COMPATIBLE_MACHINE_append = "|tqma8mq"
COMPATIBLE_MACHINE_append = "|tqma8mxml"
COMPATIBLE_MACHINE_append = "|tqma8mxnl"
# COMPATIBLE_MACHINE_append = "|tqma8qm"
