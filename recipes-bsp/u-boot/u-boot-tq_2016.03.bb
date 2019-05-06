require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "u-boot for TQ-Group Freescale i.MX based modules"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

PROVIDES += "u-boot"

SRCREV = "bb029b695300de49270cda863a8fe53f61c90a7d"
SRCBRANCH = "TQMaxx2-v2016.03-rel_imx_4.1.15_2.0.0_ga"

# SRCREV_tqma6q-nav = "6f9af19725574331e5269bbb5f7e4e4caf3f61dc"
# SRCBRANCH_tqma6q-nav = "NAV-imx_v2016.03_4.1.15_2.0.0_ga"
SRCREV_tqma6q-nav = "53a419a26d372f5419d9f5e1768d750df85a43ee"
SRCBRANCH_tqma6q-nav = "nav2/work"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "tqma7x"
COMPATIBLE_MACHINE_append = "|tqma6ulx"
COMPATIBLE_MACHINE_append = "|tqma6ullx"
COMPATIBLE_MACHINE_append = "|tqma6x"
