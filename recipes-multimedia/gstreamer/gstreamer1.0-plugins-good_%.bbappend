FILESEXTRAPATHS_prepend := "${THISDIR}/gstreamer1.0-plugins-good:"

# NOTE: '+=' does not work here; a bbappend in meta-imx overrides SRC_URI
SRC_URI_append = "\
    file://gstreamer1.0-plugins-good_bayer.patch \
"
