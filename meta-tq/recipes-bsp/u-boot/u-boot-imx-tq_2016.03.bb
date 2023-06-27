require recipes-bsp/u-boot/u-boot.inc
require u-boot-tq.inc

DESCRIPTION = "U-Boot for TQ-Systems GmbH IMX based modules"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

DEPENDS += "\
    bc-native \
    xxd-native \
"

SRCREV = "e89ae952fcc86139a8c7bc25cda5e7c3fe67a385"
SRCBRANCH = "TQMaxx2-v2016.03-rel_imx_4.1.15_2.0.0_ga"

SRC_URI = "\
    ${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
"

# target not supported in U-Boot before v2019.07
UBOOT_INITIAL_ENV = ""

COMPATIBLE_MACHINE = "tqma7x"
COMPATIBLE_MACHINE:append = "|tqma6ulx"
COMPATIBLE_MACHINE:append = "|tqma6ullx"
COMPATIBLE_MACHINE:append = "|tqma6x"
