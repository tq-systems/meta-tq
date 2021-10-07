# point SRC_URI to newer upstream and add patch to prevent STDIN
# reading / waiting

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRCREV = "9f63f359fab1b5d8e862508e4e51c9dfe339ccb0"
SRC_URI = "\
    git://gitlab.freedesktop.org/mesa/kmscube;branch=master;protocol=https \
    file://0001-Allow-running-in-background-with-STDIN-set-to-O_NONB.patch \
"

inherit meson pkgconfig features_check

REQUIRED_DISTRO_FEATURES = "opengl"

