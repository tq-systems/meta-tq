FILESEXTRAPATHS:prepend := "${THISDIR}/gstreamer1.0-plugins-good-1.20:"

# NOTE: '+=' does not work here; a bbappend in meta-imx overrides SRC_URI
SRC_URI:append = "\
    file://0001-gst-plugins-good-v4l2-add-missing-Y14-support.patch \
    file://0002-gst-plugins-good-v4l2-add-missing-raw-bayer-format-s.patch \
"
