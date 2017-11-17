SUMMARY = "Linux kernel for TQ-Group Freescale i.MX based modules"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

DEPENDS += "lzop-native bc-native"
#
# append linux-mainline if we provide mainline kernel versions
#
PROVIDES = "virtual/kernel linux-mainline"

inherit kernel

require recipes-kernel/linux/linux-dtb.inc


SRC_URI = "git://github.com/tq-systems/linux-tqmaxx.git;protocol=https;branch=${SRCBRANCH} \
           file://defconfig"

SRCBRANCH = "TQMaxx2-v4.1.15-rel_imx_4.1.15_2.0.0_ga"
SRCREV = "1fbadbaddb092b912570bf0aa8a58e8aa81dfe23"

#SRCBRANCH_tqma6q-nav = "NAV-v4.1.15-rel_imx_4.1.15_2.0.0_ga"
#SRCREV_tqma6q-nav = "f9f69c718d0dde59527e029a454d5ec9e0c1c1ee"
SRCBRANCH_tqma6q-nav = "nav2/work"
SRCREV_tqma6q-nav = "be4f0b42ca3dba5cfca1625342c053194b0a40e4"

COMPATIBLE_MACHINE = "tqma7-mba7"
COMPATIBLE_MACHINE .= "|tqma6ul-mba6ul|tqma6ulxl-mba6ul"
COMPATIBLE_MACHINE .= "|tqma6q-nav"
COMPATIBLE_MACHINE .= "|tqma6qp-mba6x|tqma6q-mba6x|tqma6dl-mba6x|tqma6s-mba6x"

S = "${WORKDIR}/git"
