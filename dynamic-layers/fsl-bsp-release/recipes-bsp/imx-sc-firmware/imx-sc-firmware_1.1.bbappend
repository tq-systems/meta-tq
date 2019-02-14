
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:${THISDIR}/${PN}:"

SRC_URI = "file://${PN}.tar.gz"

LIC_FILES_CHKSUM="file://COPYING;md5=6dfb32a488e5fd6bae52fbf6c7ebb086"

SC_FIRMWARE_NAME_tqma8qx-mba8qx = "mx8qx-tqma8qx-mba8qx-scfw-tcm.bin"

# TODO: need own firmware later ...
SC_FIRMWARE_NAME_tqma8qxs-mb-smarc-2 = "mx8qx-tqma8qx-mba8qx-scfw-tcm.bin"
