SUMMARY = "Linux kernel for TQ-Group Freescale i.MX based modules"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

DEPENDS += "lzop-native bc-native"
#
# append linux-mainline if we provide mainline kernel versions
#
PROVIDES = "virtual/kernel"

inherit kernel

SRC_URI = "\
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://defconfig \
"

SRCBRANCH = "TQMa8xx-bringup-imx_4.9.88_imx8qxp_beta2"
SRCREV = "618794a8fc3fd49bfe8b99f3bedea5cc6da6205c"

COMPATIBLE_MACHINE = "tqma8qx-mba8qx|tqma8qxa0-mba8qx"

S = "${WORKDIR}/git"
