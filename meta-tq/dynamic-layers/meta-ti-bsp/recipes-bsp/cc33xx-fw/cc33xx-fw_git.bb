# SPDX-License-Identifier: MIT
# Copyright (C) 2025 TQ-Systems GmbH <oss@ew.tq-group.com>, D-82229 Seefeld, Germany.
#
# Based on a recipe from https://git.yoctoproject.org/meta-ti/

SUMMARY = "Firmware files for use with TI cc33xx"
LICENSE = "TI-TSPA"
LIC_FILES_CHKSUM = "file://LICENCE;md5=df68504cbd0a4da1643ebcfd5783dbc9"

SRCREV = "92a809a88e5598d38dd92f8e94e11a0518043a94"
SRC_URI = "git://git.ti.com/git/cc33xx-wlan/cc33xx-fw.git;protocol=https;branch=master"

PV = "1.7.0.237"

CLEANBROKEN = "1"

S = "${WORKDIR}/git"

do_compile[no_exec] = "1"

EXTRA_OEMAKE = "DEST_DIR=${D} BASE_LIB_DIR=${nonarch_base_libdir}"

do_install() {
    oe_runmake install
}

FILES:${PN} = "${nonarch_base_libdir}/firmware/ti-connectivity/*"
