require recipes-bsp/u-boot/u-boot-ti.inc
require recipes-bsp/u-boot/u-boot-tq.inc

DESCRIPTION = "U-boot for TQ-Systems TI AM64 based modules"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=5a7450c57ffe5ae63fd732446b988025"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

SRCBRANCH = "TQMaxx-ti-u-boot-2021.01"
SRCREV = "4116992832434566d7c37114eabec83ba244ae1b"

DEPENDS += "python3-setuptools-native"

SPL_UART_BINARY_k3r5_tqma64xxl-k3r5 = "u-boot-spl.bin"

do_deploy_append_tqma64xxl-k3r5 () {
	mv ${DEPLOYDIR}/tiboot3.bin ${DEPLOYDIR}/tiboot3-r5spl.bin || true
	mv ${DEPLOYDIR}/u-boot-spl.bin ${DEPLOYDIR}/u-boot-spl-r5spl.bin || true
}

COMPATIBLE_MACHINE = "tqma64xxl"
