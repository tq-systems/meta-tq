# Define function for negative filter, e.g. apply value only if 'use-nxp-bsp' is NOT listed in MACHINEOVERRIDES
def non_nxp_bsp(d, value):
    if not "use-nxp-bsp" in d.getVar('MACHINEOVERRIDES').split(":"):
        return value
    return ""

FILESEXTRAPATHS:prepend := "${@non_nxp_bsp(d, "${THISDIR}/${PN}-${PV}:")}"

SRC_URI += "${@non_nxp_bsp(d, "file://0001-v4l2src-adding-support-for-bayer-10-12-14-16.patch")}"
