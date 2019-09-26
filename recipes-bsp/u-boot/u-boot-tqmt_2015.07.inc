FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot-tqmt_v2015.07:"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=0507cd7da8e7ad6d6701926ec9b84c95"

# TODO (workaround) changed for local build
SRC_URI = "${TQ_GIT_BASEURL}/uboot-tqmt.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

SRCREV = "78211f3e66eb6a6628c96f78944c6256cee240d8"
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
