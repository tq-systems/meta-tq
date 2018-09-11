require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "u-boot for TQ-Group Freescale i.MX based modules"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

PROVIDES = "u-boot"

SRCREV = "6a90e0a39fb0dce3a2b0ca80bb342391b77cddd8"
SRCBRANCH = "TQMaxx2-v2016.03-rel_imx_4.1.15_2.0.0_ga"

# SRCREV_tqma6q-nav = "6f9af19725574331e5269bbb5f7e4e4caf3f61dc"
# SRCBRANCH_tqma6q-nav = "NAV-imx_v2016.03_4.1.15_2.0.0_ga"
SRCREV_tqma6q-nav = "53a419a26d372f5419d9f5e1768d750df85a43ee"
SRCBRANCH_tqma6q-nav = "nav2/work"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "tqma7x-mba7"
COMPATIBLE_MACHINE .= "|tqma6ulx-mba6ulx|tqma6ulx-lga-mba6ulx"
COMPATIBLE_MACHINE .= "|tqma6q-nav"
COMPATIBLE_MACHINE .= "|tqma6qp-mba6x|tqma6q-mba6x|tqma6dl-mba6x|tqma6s-mba6x"

