FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRCREV = "0be1681d09e77330e02b8d4708e47a04585f4b16"
SRC_URI = "git://gitlab.freedesktop.org/mesa/kmscube;branch=master;protocol=https"

#
# GLES3 support is optional and may not be available with vendor specific
# implementations. with poky / yocto 4.0.11 virtual/libgles3 was added as a
# hard dependency. Remove this.
#
DEPENDS:remove = "virtual/libgles3"

SRC_URI:append = "\
    file://0001-Allow-running-in-background-with-STDIN-set-to-O_NONB.patch \
"
