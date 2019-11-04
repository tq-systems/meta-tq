
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:${THISDIR}/${PN}:"

SRC_URI_append = " \
    file://${PN}-tq.tar.gz \
"

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

SC_FIRMWARE_NAME_tqma8qm-mba8x = "mx8qm-tqma8qm-scfw-tcm.bin"

do_deploy_prepend () {
# TODO: need new version number
#    cp ${WORKDIR}/${PN}-tq-${PV}/*.bin ${S}
    cp ${WORKDIR}/${PN}-tq-1.2.2/*.bin ${S}
}
