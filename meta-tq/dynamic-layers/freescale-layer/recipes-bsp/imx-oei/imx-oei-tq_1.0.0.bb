SUMMARY = "i.MX Optional Execution Image(s) for TQ modules."
DESCRIPTION = "\
The OEI image(s) are responsible for RAM controller initialisation and system setup \
to run the System Manager (SM) firmware on dedicated Cortex-M boot core. \
This recipe provides the implementation for TQ-Systems SOM"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=b66f32a90f9577a5a3255c21d79bc619"

SRC_URI = "${IMX_OEI_SRC};branch=${SRCBRANCH}"
IMX_OEI_SRC = "${TQ_GIT_BASEURL}/tq-imx-oei.git;protocol=${TQ_GIT_PROTOCOL}"
SRCBRANCH = "TQM-lf-6.12.49"
SRCREV = "c3fee0e8b985f56083caaba0607520d02fa8fad9"

require dynamic-layers/arm-toolchain/recipes-bsp/imx-oei/imx-oei.inc

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:tqma95xx = "tqma95xx"
