
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:${THISDIR}/${PN}:"

SRC_URI_append = " \
    file://${PN}-tq.tar.gz \
"

LIC_FILES_CHKSUM="file://COPYING;md5=6dfb32a488e5fd6bae52fbf6c7ebb086"

SC_FIRMWARE_NAME_tqma8qxp-mba8xx = "mx8qx-tqma8qx-mba8xx-scfw-tcm.bin"
SC_FIRMWARE_NAME_tqma8dx-mba8xx = "mx8qx-tqma8dx-mba8xx-scfw-tcm.bin"

# TODO: need own firmware later ...
SC_FIRMWARE_NAME_tqma8qxs-mb-smarc-2 = "mx8qx-tqma8qx-mba8xx-scfw-tcm.bin"

do_compile_prepend () {
    cp ${WORKDIR}/${PN}-tq-${PV}/*.bin ${S}
}
