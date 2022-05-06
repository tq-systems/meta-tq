FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI:append = "\
    file://config/;subdir=git \
"
