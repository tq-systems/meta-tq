require recipes-bsp/u-boot/u-boot.inc
require u-boot-tq.inc

DESCRIPTION = "U-Boot for TQ-Systems GmbH Freescale LS1012A based modules"

LIC_FILES_CHKSUM = "file://Licenses/README;md5=5a7450c57ffe5ae63fd732446b988025"

DEPENDS += "bison-native"

SRCREV = "043de483fb12d05a0bcdbbbb4473e290c22eec38"
SRCBRANCH = "TQM-lf-5.15"

COMPATIBLE_MACHINE = "tqmls1012al"
