FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

#
# GLES3 support is optional and may not be available with vendor specific
# implementations. with poky / yocto 4.0.11 virtual/libgles3 was added as a
# hard dependency. Remove this.
#
DEPENDS:remove = "virtual/libgles3"

SRC_URI:append = "\
    file://0001-Allow-running-in-background-with-STDIN-set-to-O_NONB.patch \
"
