SECTION = "bootloaders"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

SRCREV = "e682bf1477f370a001ca5349b9e0810f9851ae0a"
SRCBRANCH = "TQMa8-v2019.04-lf-5.4.y-1.0.0"

SRC_URI = " \
    ${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
"

DEPENDS_append = " bison-native"

S = "${WORKDIR}/git"

# COMPATIBLE_MACHINE = "tqma8xx"
COMPATIBLE_MACHINE = "|tqma8xxs"
COMPATIBLE_MACHINE_append = "|tqma8mq"
COMPATIBLE_MACHINE_append = "|tqma8mxml"
COMPATIBLE_MACHINE_append = "|tqma8mxnl"
COMPATIBLE_MACHINE_append = "|tqma8qm"
