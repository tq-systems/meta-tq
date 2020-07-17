require recipes-bsp/u-boot/u-boot-tq.inc

DESCRIPTION = "U-Boot for TQ-Group Freescale LS1028A based modules"
PROVIDES += "u-boot-tq"

SRCREV = "8774bf6ea9fa3a2f91d7537fe4a4780592b9e75e"
SRCBRANCH = "TQMLSxx-LSDK-20.04-update-290520"

COMPATIBLE_MACHINE = "tqmls1028a-mbls1028a"
