FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot-tqmt_v2015.07:"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=0507cd7da8e7ad6d6701926ec9b84c95"

# TODO (workaround) changed for local build
SRC_URI = "${TQ_GIT_BASEURL}/uboot-tqmt.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

SRCREV = "cf0fc92df56891967604f994fbd95a1a063a363f"
SRCBRANCH = "TQMTxxxx-u-boot-v2015.07"

PV_append = "-tqmt"
PR = "r1"

S = "${WORKDIR}/git"

do_compile_prepend() {
	unset LDFLAGS
	unset CFLAGS
	unset CPPFLAGS

	printf "%s" "${LOCALVERSION}" > ${S}/.scmversion
	printf "%s" "${LOCALVERSION}" > ${B}/.scmversion
}
