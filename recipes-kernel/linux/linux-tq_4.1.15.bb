include recipes-kernel/linux/linux-fslc.inc

SUMMARY = "Linux kernel for TQ-Group Freescale i.MX based modules"

SRC_URI = "git://git@tq-git-pr1.tq-net.de/tq-embedded/tqmaxx/linux-tqmaxx.git;protocol=ssh;branch=${SRCBRANCH} \
           file://defconfig"

SRCBRANCH = "TQMaxx2-v4.1.15-rel_imx_4.1.15_1.2.0_ga"
SRCREV = "${AUTOREV}"

COMPATIBLE_MACHINE = "(tqma6q-mba6x)"
