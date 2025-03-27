SUMMARY = "i.MX Optional Execution Image(s) for TQ modules."
DESCRIPTION = "\
The OEI image(s) are responsible for RAM controller initialisation and system setup \
to run the System Manager (SM) firmware on dedicated Cortex-M boot core. \
This recipe provides the implementation for TQ-Systems SOM"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=b66f32a90f9577a5a3255c21d79bc619"

SRC_URI = "${IMX_OEI_SRC};branch=${SRCBRANCH}"
IMX_OEI_SRC = "${TQ_GIT_BASEURL}/tq-imx-oei.git;protocol=${TQ_GIT_PROTOCOL}"
SRCBRANCH = "TQM-lf-6.6.52"
SRCREV = "2c7b38423366429047756f7728e81a8d02ea891f"

S = "${WORKDIR}/git"

# Support for TQ-Systems SOM wit hdifferent RAM sizes
EXTRA_OEMAKE += "RAM_SIZE=${OEI_RAM_SIZE}"

# TODO: use full path after patches are ported to meta-freescale
require imx-oei.inc
# require recipes-bsp/imx-oei/imx-oei.inc

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:tqma95xx = "tqma95xx"
