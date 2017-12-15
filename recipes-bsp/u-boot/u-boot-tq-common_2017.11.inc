SECTION = "bootloader"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

SRCBRANCH = "TQMLS1012AL-bringup-v2017.11-LSDK-18.03"
SRCREV = "39ac425560e00cc74270eebafba443b27b5d2b02"

SRC_URI = "\
  ${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://v2017.11-fw-utils-build-fix.patch \
"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "tqmls1012al"
