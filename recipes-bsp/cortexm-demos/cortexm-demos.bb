# Copyright 2020 TQ Systems GmbH
# Released under the MIT license

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SUMMARY = "CortexM Demo images"
SECTION = "app"
LICENSE = "MIT"

inherit deploy

SOC        	?= "INVALID"
SOC_tqma8mxnl	= "i.MX8MN"
SOC_tqma8mxml	= "i.MX8MM"
SOC_tqma8mq	= "i.MX8MQ"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = "file://cortexm-demos.tar.gz"

S = "${WORKDIR}/${PN}"

do_install () {
   cp ${S}/${SOC}/* ${D}
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

PACKAGE_ARCH = "${MACHINE_ARCH}"
