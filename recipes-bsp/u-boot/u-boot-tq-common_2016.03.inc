SECTION = "bootloaders"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

SRCREV = "82958ffcc05a8331aa1f5670c90d07632c32fa7b"
SRCBRANCH = "TQMaxx2-v2016.03-rel_imx_4.1.15_2.0.0_ga"

SRCREV_tqma6q-nav = "53a419a26d372f5419d9f5e1768d750df85a43ee"
SRCBRANCH_tqma6q-nav = "nav2/work"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "tqma7x-mba7"
COMPATIBLE_MACHINE .= "|tqma6ulx-mba6ulx|tqma6ulx-lga-mba6ulx"
COMPATIBLE_MACHINE .= "|tqma6q-nav"
COMPATIBLE_MACHINE .= "|tqma6qp-mba6x|tqma6q-mba6x|tqma6dl-mba6x|tqma6s-mba6x"
