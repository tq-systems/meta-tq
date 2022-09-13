LICENSE = "BSD-3-Clause"

FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI:append = "\
    file://config/;subdir=git \
"
