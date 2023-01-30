FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "\
    file://0001-glibc-support-e5500-and-e6500.patch \
    file://0002-sunrpc-Suppress-GCC-Os-warning-on-user2netname.patch \
"
