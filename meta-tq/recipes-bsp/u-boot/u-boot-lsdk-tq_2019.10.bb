require recipes-bsp/u-boot/u-boot.inc
require u-boot-tq.inc

DESCRIPTION = "U-Boot for TQ-Systems GmbH Freescale LS1012A/LS1028A based modules"

DEPENDS += "bison-native"

SRCREV = "70cb4d824a7cbbe7bdefc7c485b6dcf5eed74415"
SRCBRANCH = "TQMLSxx-LSDK-20.04-update-290520"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:tqmls1028a = "tqmls1028a"
