# Define function for negative filter, e.g. apply value only if 'use-nxp-bsp' is NOT listed in MACHINEOVERRIDES
def non_nxp_bsp(d, value):
    if not "use-nxp-bsp" in d.getVar('MACHINEOVERRIDES').split(":"):
        return value
    return ""

FILESEXTRAPATHS:prepend := "${@non_nxp_bsp(d, "${THISDIR}/${PN}-${PV}:")}"

SRC_URI += "${@non_nxp_bsp(d, "file://0001-rgb2bayer-Support-video-x-bayer-10-12-14-16-bit-dept.patch")}"
SRC_URI += "${@non_nxp_bsp(d, "file://0002-bayer2rgb-Disable-in-place-transform.patch")}"
SRC_URI += "${@non_nxp_bsp(d, "file://0003-bayer2rgb-Inline-the-j-0-value.patch")}"
SRC_URI += "${@non_nxp_bsp(d, "file://0004-bayer2rgb-Fold-src_stride-into-gst_bayer2rgb_process.patch")}"
SRC_URI += "${@non_nxp_bsp(d, "file://0005-bayer2rgb-Pass-all-parameters-to-LINE-macro.patch")}"
SRC_URI += "${@non_nxp_bsp(d, "file://0006-bayer2rgb-Pass-filter-pointer-into-gst_bayer2rgb_spl.patch")}"
SRC_URI += "${@non_nxp_bsp(d, "file://0007-bayer2rgb-Add-comment-on-bayer_orc_horiz_upsample.patch")}"
SRC_URI += "${@non_nxp_bsp(d, "file://0008-bayer2rgb-Add-comments-explaining-gst_bayer2rgb_proc.patch")}"
SRC_URI += "${@non_nxp_bsp(d, "file://0009-bayer2rgb-Support-video-x-bayer-10-12-14-16-bit-dept.patch")}"
