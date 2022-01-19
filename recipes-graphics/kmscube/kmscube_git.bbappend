# point SRC_URI to newer upstream and add patch to prevent STDIN
# reading / waiting

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = "\
    file://0001-Allow-running-in-background-with-STDIN-set-to-O_NONB.patch \
"

DEPENDS_append = " virtual/libgbm"
