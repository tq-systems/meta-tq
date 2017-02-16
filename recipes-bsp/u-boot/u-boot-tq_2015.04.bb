require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "u-boot for TQ-Group Freescale i.MX based modules"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=c7383a594871c03da76b3707929d2919"

PROVIDES = "u-boot"

SRCREV = "e30fabc6d9a7f60509f6e75b2cc0b63f0bf27124"
SRCBRANCH = "TQMaxx2-v2015.04-rel_imx_4.1.15_1.2.0_ga"

SRC_URI = "git://github.com/tq-systems/u-boot-tqmaxx.git;protocol=https;branch=${SRCBRANCH}"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "(tqma6q-mba6x|tqma6dl-mba6x|tqma6s-mba6x|tqma6ul-mba6ul)"
