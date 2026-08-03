require recipes-bsp/u-boot/u-boot.inc
require u-boot-tq.inc

DESCRIPTION = "U-Boot for TQ-Systems GmbH Freescale LS1012A/LS1028A based modules"

SRCREV = "b707f7d920e45fdb3c8f83955c6a296c549d8b11"
SRCBRANCH = "TQMLSxx-LSDK-20.04-update-290520"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:tqmls1028a = "tqmls1028a"
