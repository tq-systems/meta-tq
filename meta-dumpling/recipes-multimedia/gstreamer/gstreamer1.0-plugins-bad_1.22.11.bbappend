FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${PV}:"

# NOTE: '+=' does not work here; a bbappend in meta-imx overrides SRC_URI
SRC_URI:append = " \
    file://0001-rgb2bayer-Support-video-x-bayer-10-12-14-16-bit-dept.patch \
    file://0002-bayer2rgb-Disable-in-place-transform.patch \
    file://0003-bayer2rgb-Inline-the-j-0-value.patch \
    file://0004-bayer2rgb-Fold-src_stride-into-gst_bayer2rgb_process.patch \
    file://0005-bayer2rgb-Pass-all-parameters-to-LINE-macro.patch \
    file://0006-bayer2rgb-Pass-filter-pointer-into-gst_bayer2rgb_spl.patch \
    file://0007-bayer2rgb-Add-comment-on-bayer_orc_horiz_upsample.patch \
    file://0008-bayer2rgb-Add-comments-explaining-gst_bayer2rgb_proc.patch \
    file://0009-bayer2rgb-Support-video-x-bayer-10-12-14-16-bit-dept.patch \
"
