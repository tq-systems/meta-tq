FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = "\
    file://0001-Allow-running-in-background-with-STDIN-set-to-O_NONB.patch \
    file://0001-Make-gles3-support-optional.patch \
    file://0002-Fix-GLES3-detection.patch \
"
