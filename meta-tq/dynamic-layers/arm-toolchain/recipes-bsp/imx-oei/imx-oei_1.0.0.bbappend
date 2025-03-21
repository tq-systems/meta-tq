SUMMARY = "i.MX Optional Execution Image for TQ modules"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=b66f32a90f9577a5a3255c21d79bc619"

IMX_OEI_SRC = "${TQ_GIT_BASEURL}/tq-imx-oei.git;protocol=https"
SRCBRANCH = "TQM-lf-6.6.52"
SRCREV = "2c7b38423366429047756f7728e81a8d02ea891f"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:tqma95xx = "tqma95xx"
