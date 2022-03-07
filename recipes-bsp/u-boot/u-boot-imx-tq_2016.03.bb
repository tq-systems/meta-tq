require u-boot-tq.inc

DESCRIPTION = "U-Boot for TQ-Systems GmbH IMX based modules"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

DEPENDS += "xxd-native"

SRCREV = "c8256abcff63f095645d6d5b7dd69bc24794b45c"
SRCBRANCH = "TQMaxx2-v2016.03-rel_imx_4.1.15_2.0.0_ga"

SRC_URI = "\
    ${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
    file://0001-tqma6ul-fix-OTG2-fuse-check-on-MBa6ULx.patch \
    file://0002-tqma6ul-configure-OTG1_PWR-pin-early-in-mba6ul_setup.patch \
    file://0003-tqma6ul-check-fuse-state-for-ENET1-2-on-MBa6ULxL.patch \
    file://0004-tqma6ul-add-fuse-check-for-USB-IP.patch \
"

COMPATIBLE_MACHINE = "tqma7x"
COMPATIBLE_MACHINE_append = "|tqma6ulx"
COMPATIBLE_MACHINE_append = "|tqma6ullx"
COMPATIBLE_MACHINE_append = "|tqma6x"
