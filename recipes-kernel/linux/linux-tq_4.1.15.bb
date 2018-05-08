SUMMARY = "Linux kernel for TQ-Group Freescale i.MX based modules"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel

DEPENDS += "lzop-native bc-native"
#
# append linux-mainline if we provide mainline kernel versions
#
PROVIDES = "virtual/kernel linux-mainline"

SRC_URI = "git://github.com/tq-systems/linux-tqmaxx.git;protocol=https;branch=${SRCBRANCH} \
           file://defconfig"

SRCBRANCH = "TQMaxx2-v4.1.15-rel_imx_4.1.15_2.0.0_ga"
SRCREV = "6297c9dcc0bc74e51b41865fe8291a05293622a6"

SRCBRANCH_tqma6q-nav = "TQMaxx2-NAV-v4.1.15-rel_imx_4.1.15_2.0.0_ga"
SRCREV_tqma6q-nav = "58ce624280c2ca170374b6fcc349819c4a087050"

COMPATIBLE_MACHINE = "tqma7x-mba7"
COMPATIBLE_MACHINE .= "|tqma6ulx-mba6ulx|tqma6ulx-lga-mba6ulx"
COMPATIBLE_MACHINE .= "|tqma6q-nav"
COMPATIBLE_MACHINE .= "|tqma6qp-mba6x|tqma6q-mba6x|tqma6dl-mba6x|tqma6s-mba6x"

S = "${WORKDIR}/git"
