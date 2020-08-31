require recipes-bsp/u-boot/u-boot-tq.inc

DESCRIPTION = "U-Boot for TQ-Group Freescale LS1012A/LS1028A based modules"
PROVIDES += "u-boot-tq"

SRCREV = "775abddaaa1bd5aea667b13222b1bc21584446af"
SRCBRANCH = "TQMLSxx-LSDK-20.04-update-290520"

COMPATIBLE_MACHINE = "tqmls1012al-mbls1012al|tqmls1028a-mbls1028a"
