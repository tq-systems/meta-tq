SUMMARY = "Linux kernel for TQ-Group Freescale i.MX based modules"

include recipes-kernel/linux/linux-imx.inc

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel

DEPENDS += "lzop-native bc-native"
#
# append linux-mainline if we provide mainline kernel versions
#
PROVIDES += "linux-tq"

SRC_URI = "\
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://defconfig \
"

SRCBRANCH = "TQMaxx2-v4.14-rel_imx_4.14.78_1.0.0_ga"
SRCREV ?= "bb0360f3f557ef3e49c5aa1eab236a87e911e61f"

SRCREV_tqma7x = "bb0360f3f557ef3e49c5aa1eab236a87e911e61f"
SRCREV_tqma6ulx = "8034ee52a335aaf9e14b9a23ee1630bc3f5b01ca"
SRCREV_tqma6ullx = "8034ee52a335aaf9e14b9a23ee1630bc3f5b01ca"

COMPATIBLE_MACHINE = "tqma7x"
COMPATIBLE_MACHINE_append = "|tqma6x"
COMPATIBLE_MACHINE_append = "|tqma6ulx"
COMPATIBLE_MACHINE_append = "|tqma6ullx"

S = "${WORKDIR}/git"
