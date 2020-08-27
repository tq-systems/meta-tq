
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:${THISDIR}/${PN}:"

# Need tq_imx-scfw-v1.2.7-b3357 or newer for build
# (use SC_FIRMWARE_VERSION_TQ in package name)

SC_FIRMWARE_VERSION_TQ := "tq-TQMa8.NXP-v1.3.1.B4124.0028"

SRC_URI_append = " \
    file://${BPN}-${SC_FIRMWARE_VERSION_TQ}.tar.gz \
"

# clear vars to prevent default assignments
SC_FIRMWARE_NAME = "invalid"

SC_FIRMWARE_NAME_tqma8xqp-mba8xx = "mx8qx-tqma8xqp-mba8xx-scfw-tcm.bin"
SC_FIRMWARE_NAME_tqma8xd-mba8xx = "mx8qx-tqma8xd-mba8xx-scfw-tcm.bin"
# TODO: maybe different config needed
SC_FIRMWARE_NAME_tqma8xqp-mbpa8xx = "mx8qx-tqma8xqp-mba8xx-scfw-tcm.bin"

# TODO: need own firmware later ...
SC_FIRMWARE_NAME_tqma8xqps-mb-smarc-2 = "mx8qx-tqma8xqps-mb-smarc-2-scfw-tcm.bin"
SC_FIRMWARE_NAME_tqma8xds-mb-smarc-2 = "mx8qx-tqma8xds-mb-smarc-2-scfw-tcm.bin"

SC_FIRMWARE_NAME_tqma8qm-4gb-mba8x = "mx8qm-tqma8qm-4gb-scfw-tcm.bin"
SC_FIRMWARE_NAME_tqma8qm-8gb-mba8x = "mx8qm-tqma8qm-8gb-scfw-tcm.bin"

do_deploy_prepend () {
    cp ${WORKDIR}/${PN}-${SC_FIRMWARE_VERSION_TQ}/*.bin ${S}
}
