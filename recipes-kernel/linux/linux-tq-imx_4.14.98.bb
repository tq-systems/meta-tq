require linux-tq-common.inc

SRC_URI = "\
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://defconfig \
"

SRCBRANCH = "TQMaxx-bringup-imx_4.14.98_2.2.0"
SRCREV = "6e4e6a9dfa67812eefe7a3409b89c5d51b0d4756"

COMPATIBLE_MACHINE = "tqma7x"
COMPATIBLE_MACHINE_append = "|tqma6x"
COMPATIBLE_MACHINE_append = "|tqma6ulx"
COMPATIBLE_MACHINE_append = "|tqma6ullx"

S = "${WORKDIR}/git"
