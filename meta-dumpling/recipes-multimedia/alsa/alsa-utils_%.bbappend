FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-bat-Fix-buffer-time-configuration.patch"

PACKAGECONFIG:append = " bat "
