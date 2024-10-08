SUMMARY = "i.MX System Manager Firmware for TQ modules"

LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=b66f32a90f9577a5a3255c21d79bc619"

IMX_SYSTEM_MANAGER_SRC = "${TQ_GIT_BASEURL}/tq-imx-sm.git;protocol=https"
SRCBRANCH = "TQM-lf-6.6.52"
SRCREV = "71f528b1ac59bd53351a439e31f969ad1aed4128"

# needs to be removed for production releases
PACKAGECONFIG ?= "m1"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:tqma95xx = "tqma95xx"
