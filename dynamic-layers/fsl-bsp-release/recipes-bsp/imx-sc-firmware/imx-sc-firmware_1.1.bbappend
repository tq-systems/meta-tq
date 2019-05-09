
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:${THISDIR}/${PN}:"

SRC_URI_append = " \
    file://${PN}-tq.tar.gz \
"

LIC_FILES_CHKSUM="file://COPYING;md5=6dfb32a488e5fd6bae52fbf6c7ebb086"

# clear vars to prevent default assignments
BOARD_TYPE_mx8 = ""
SC_FIRMWARE_NAME_mx8qxp = "invalid"
SC_FIRMWARE_NAME_mx8qm = "invalid"

SC_FIRMWARE_NAME_tqma8xqp-mba8xx = "mx8qx-tqma8xqp-mba8xx-scfw-tcm.bin"
SC_FIRMWARE_NAME_tqma8xd-mba8xx = "mx8qx-tqma8xd-mba8xx-scfw-tcm.bin"
# TODO: maybe different config needed
SC_FIRMWARE_NAME_tqma8xqp-mbpa8xx = "mx8qx-tqma8xqp-mba8xx-scfw-tcm.bin"

# TODO: need own firmware later ...
# SC_FIRMWARE_NAME_tqma8qxps-mb-smarc-2 = "mx8qx-tqma8qx-mba8xx-scfw-tcm.bin"
SC_FIRMWARE_NAME_tqma8xqps-mb-smarc-2 = "mx8qx-tqma8xqps-mb-smarc-2-scfw-tcm.bin"
SC_FIRMWARE_NAME_tqma8xds-mb-smarc-2 = "mx8qx-tqma8xds-mb-smarc-2-scfw-tcm.bin"

do_compile_prepend () {
    cp ${WORKDIR}/${PN}-tq-${PV}/*.bin ${S}
}
