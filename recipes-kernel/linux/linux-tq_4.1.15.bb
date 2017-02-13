include recipes-kernel/linux/linux-fslc.inc

SUMMARY = "Linux kernel for TQ-Group Freescale i.MX based modules"

SRC_URI = "git://github.com/tq-systems/linux-tqmaxx.git;protocol=https;branch=${SRCBRANCH} \
           file://defconfig"

SRCBRANCH = "TQMaxx2-v4.1.15-rel_imx_4.1.15_1.2.0_ga"
SRCREV = "35fc6c78ddec0220e2bebf08bd91eb4feac5753c"

COMPATIBLE_MACHINE = "(tqma6q-mba6x|tqma7-mba7|tqma6ul-mba6ul)"
