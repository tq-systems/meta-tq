
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:tqma91xx += "\
    file://loadaddr.cfg \
"

SRC_URI:tqma93xx += "\
    file://loadaddr.cfg \
"
