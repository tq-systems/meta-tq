# Copyright 2021 TQ-Systems GmbH
# Released under the BSD-3 license

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

SUMMARY = "CortexM Demo images"
SECTION = "app"
LICENSE = "BSD-3-Clause"

inherit deploy

SOC		?= "INVALID"
SOC_tqma8mxnl	= "tqma8mxnl"
SOC_tqma8mxml	= "tqma8mxml"
SOC_tqma8mq	= "tqma8mx"
SOC_tqma8mpxl	= "tqma8mpxl"

SDK_BASE_REV = "v2.9.0"
SDK_TQ_REV = "g11654154c280"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = "file://tqma8-cortexm-demos-${SDK_BASE_REV}-${SDK_TQ_REV}.tar.gz"

S = "${WORKDIR}/${BPN}-${SDK_BASE_REV}-${SDK_TQ_REV}"

do_install () {
   cp ${S}/${SOC}/*.bin ${D}
}

do_deploy () {
   # Install the demo binaries
   cp ${D}/* ${DEPLOYDIR}/
   chmod 0644 ${DEPLOYDIR}/*
}
addtask deploy after do_install

FILES_${PN} = " \
   /* \
"

COMPATIBLE_MACHINE_append = "tqma8mq"
COMPATIBLE_MACHINE_append = "|tqma8mxml"
COMPATIBLE_MACHINE_append = "|tqma8mxnl"
COMPATIBLE_MACHINE_append = "|tqma8mpxl"

PACKAGE_ARCH = "${MACHINE_ARCH}"
