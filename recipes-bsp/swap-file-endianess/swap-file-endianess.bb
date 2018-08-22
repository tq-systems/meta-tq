DESCRIPTION = "provides a tcl script for endian swap"
SECTION = "bootloader"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://byteswap.tcl"

S = "${WORKDIR}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install () {
    install -d ${D}/${bindir}
    install -m 755 ${WORKDIR}/byteswap.tcl ${D}/${bindir}
}

RDEPENDS_${PN} += "tcl"

BBCLASSEXTEND = "native nativesdk"
