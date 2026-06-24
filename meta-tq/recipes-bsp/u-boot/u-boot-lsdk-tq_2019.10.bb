require recipes-bsp/u-boot/u-boot.inc
require u-boot-tq.inc

DESCRIPTION = "U-Boot for TQ-Systems GmbH Freescale LS1012A/LS1028A based modules"

SRCREV = "361a162a569b5e78a070796af5e9142a44993557"
SRCBRANCH = "TQMLSxx-LSDK-20.04-update-290520"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:tqmls1028a = "tqmls1028a"
