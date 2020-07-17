require recipes-bsp/u-boot/u-boot-tq.inc

DESCRIPTION = "U-Boot for TQ-Group Freescale LS1012A/LS1028A based modules"
PROVIDES += "u-boot-tq"

SRCREV = "1f44e323d4afd3fd9260a716008983fd03717110"
SRCBRANCH = "TQMLSxx-LSDK-20.04-update-290520"

COMPATIBLE_MACHINE = "tqmls1012al-mbls1012al|tqmls1028a-mbls1028a"
