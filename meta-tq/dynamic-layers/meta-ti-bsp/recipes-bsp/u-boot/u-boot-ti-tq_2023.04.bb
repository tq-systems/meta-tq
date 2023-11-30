require recipes-bsp/u-boot/u-boot-ti.inc
require recipes-bsp/u-boot/u-boot-tq.inc

DESCRIPTION = "U-boot for TQ-Systems TI AM62/64 based modules"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

SRCBRANCH = "TQMaxx-ti-u-boot-2023.04"
SRCREV = "4e5fbd7e4b5593ca8c89e1e49a01ba8f4cfa92e3"

DEPENDS += "python3-setuptools-native"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:tqma62xx = "tqma62xx"
COMPATIBLE_MACHINE:tqma62xx-k3r5 = "tqma62xx-k3r5"
COMPATIBLE_MACHINE:tqma64xxl = "tqma64xxl"
COMPATIBLE_MACHINE:tqma64xxl-k3r5 = "tqma64xxl-k3r5"
