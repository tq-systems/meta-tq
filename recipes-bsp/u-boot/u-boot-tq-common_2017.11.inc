SECTION = "bootloader"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

SRCBRANCH = "TQMLS1012AL-bringup-v2017.11-LSDK-18.03"
SRCREV = "b6ea6ee49b07c85c974645f1dc6f50165868edce"

SRC_URI = "\
  ${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://v2017.11-fw-utils-build-fix.patch \
"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "tqmls1012al"
