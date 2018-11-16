
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:${THISDIR}/${PN}:"

SRC_URI = "file://${PN}.tar.gz"

LIC_FILES_CHKSUM="file://COPYING;md5=5ab1a30d0cd181e3408077727ea5a2db"

SC_FIRMWARE_NAME_tqma8qx-mba8qx = "mx8qx-tqma8qx-mba8qx-scfw-tcm.bin"
SC_FIRMWARE_NAME_tqma8qxa0-mba8qx = "mx8qx-a0-tqma8qx-mba8qx-scfw-tcm.bin"

# TODO: need own firmware later ...
SC_FIRMWARE_NAME_tqma8qxs-mb-smarc-2 = "mx8qx-tqma8qx-mba8qx-scfw-tcm.bin"
