# point SRC_URI to newer upstream and add patch to prevent STDIN
# reading / waiting

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = "\
    file://0001-Allow-running-in-background-with-STDIN-set-to-O_NONB.patch \
"

DEPENDS:append = " virtual/libgbm"
