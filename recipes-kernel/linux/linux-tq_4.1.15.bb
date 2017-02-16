SUMMARY = "Linux kernel for TQ-Group Freescale i.MX based modules"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

DEPENDS += "lzop-native bc-native"
#
# append linux-mainline if we provide mainine kernel versions
#
PROVIDES = "virtual/kernel linux-mainline"

inherit kernel

require recipes-kernel/linux/linux-dtb.inc


SRC_URI = "git://github.com/tq-systems/linux-tqmaxx.git;protocol=https;branch=${SRCBRANCH} \
           file://defconfig"

SRCBRANCH = "TQMaxx2-v4.1.15-rel_imx_4.1.15_2.0.0_ga"
SRCREV = "8f8cd92f3056f7b93ec0d63138399ef0fe35042e"

SRCBRANCH_tqma6ul-mba6ul = "TQMaxx2-v4.1.15-rel_imx_4.1.15_1.2.0_ga"
SRCREV_tqma6ul-mba6ul = "35fc6c78ddec0220e2bebf08bd91eb4feac5753c"

SRCBRANCH_tqma6q-mba6x = "TQMaxx2-v4.1.15-rel_imx_4.1.15_1.2.0_ga"
SRCREV_tqma6q-mba6x = "35fc6c78ddec0220e2bebf08bd91eb4feac5753c"

SRCBRANCH_tqma6dl-mba6x = "TQMaxx2-v4.1.15-rel_imx_4.1.15_1.2.0_ga"
SRCREV_tqma6dl-mba6x = "35fc6c78ddec0220e2bebf08bd91eb4feac5753c"

SRCBRANCH_tqma6s-mba6x = "TQMaxx2-v4.1.15-rel_imx_4.1.15_1.2.0_ga"
SRCREV_tqma6s-mba6x = "35fc6c78ddec0220e2bebf08bd91eb4feac5753c"

SRCBRANCH_tqma6q-nav = "NAV-v4.1.15-rel_imx_4.1.15_2.0.0_ga"
SRCREV_tqma6q-nav = "b57ce170e24af5e39be75b75217f39051d4e5f2d"

COMPATIBLE_MACHINE = "(tqma6q-mba6x|tqma6dl-mba6x|tqma6s-mba6x|tqma6q-nav|tqma7-mba7|tqma6ul-mba6ul)"

S = "${WORKDIR}/git"
