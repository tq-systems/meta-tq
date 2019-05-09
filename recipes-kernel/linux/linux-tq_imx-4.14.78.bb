require linux-tq-common.inc

SRC_URI = "\
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://defconfig \
"

SRCBRANCH = "TQMaxx2-v4.14-rel_imx_4.14.78_1.0.0_ga"
SRCREV = "b6a3c5f948ba7f321bc69d73f811053f18e472ca"

COMPATIBLE_MACHINE = "tqma7x"
COMPATIBLE_MACHINE_append = "|tqma6ulx"
COMPATIBLE_MACHINE_append = "|tqma6ullx"

S = "${WORKDIR}/git"
