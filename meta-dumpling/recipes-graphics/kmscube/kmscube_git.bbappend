FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRCREV = "467e86c5cbeb2a2051b31ce2c240d6ddf5bc3112"

#
# GLES3 support is optional and may not be available with vendor specific
# implementations. with poky / yocto 4.0.11 virtual/libgles3 was added as a
# hard dependency. Remove this.
#
DEPENDS:remove = "virtual/libgles3"
