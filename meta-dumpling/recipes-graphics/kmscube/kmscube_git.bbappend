FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRCREV = "0be1681d09e77330e02b8d4708e47a04585f4b16"
SRC_URI = "git://gitlab.freedesktop.org/mesa/kmscube;branch=master;protocol=https"

SRC_URI:append = "\
    file://0001-Allow-running-in-background-with-STDIN-set-to-O_NONB.patch \
"
