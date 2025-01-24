
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append:tqma91xx = "\
    file://loadaddr.cfg \
"

SRC_URI:append:tqma93xx = "\
    file://loadaddr.cfg \
"
