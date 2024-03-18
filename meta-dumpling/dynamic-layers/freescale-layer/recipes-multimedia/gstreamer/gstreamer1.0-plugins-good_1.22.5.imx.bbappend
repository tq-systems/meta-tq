FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${PV}:"

# NOTE: '+=' does not work here; a bbappend in meta-imx overrides SRC_URI
SRC_URI:append = " file://0001-v4l2src-adding-support-for-bayer-10-12-14-16.patch"

