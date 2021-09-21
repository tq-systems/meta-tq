# Copyright 2021 TQ-Systems GmbH
# Released under the BSD-3 license

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

SUMMARY = "CortexM Demo images"
SECTION = "app"
LICENSE = "BSD-3-Clause"

PROVIDES += "virtual/imx-cortexm-demos"

inherit deploy

do_configure[noexec] = "1"
do_compile[noexec] = "1"

SOC		?= "INVALID"
SOC_tqma8mxnl	= "tqma8mxnl"
SOC_tqma8mxml	= "tqma8mxml"
SOC_tqma8mq	= "tqma8mx"
SOC_tqma8mpxl	= "tqma8mpxl"
SOC_tqma8x	= "tqma8qm"
SOC_tqma8xx	= "tqma8xx"
SOC_tqma8xxs	= "tqma8xxs"

SDK_BASE_REV = "v2.9.0"
SDK_TQ_REV = "g4eb071e74f9d"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = "file://tqma8-cortexm-demos-${SDK_BASE_REV}-${SDK_TQ_REV}.tar.gz"

S = "${WORKDIR}/${BPN}-${SDK_BASE_REV}-${SDK_TQ_REV}"

do_compile[noexec] = "1"

do_install[noexec] = "1"

do_deploy () {
    install -m 0644 ${S}/${SOC}/*.bin ${DEPLOYDIR}
}

addtask deploy after do_install

FILES_${PN} = " \
   /*.bin \
"

COMPATIBLE_MACHINE  = "^("
COMPATIBLE_MACHINE .= "tqma8mq"
COMPATIBLE_MACHINE .= "|tqma8mxml"
COMPATIBLE_MACHINE .= "|tqma8mxnl"
COMPATIBLE_MACHINE .= "|tqma8mpxl"
COMPATIBLE_MACHINE .= "|tqma8x"
COMPATIBLE_MACHINE .= "|tqma8xx"
COMPATIBLE_MACHINE .= "|tqma8xxs"
COMPATIBLE_MACHINE .= ")$"

PACKAGE_ARCH = "${MACHINE_ARCH}"
