# Copyright (C) 2016 Freescale Semiconductor
# Copyright 2017-2019 NXP
# Copyright 2019-2022 TQ-Systems GmbH

DESCRIPTION = "i.MX System Controller Firmware"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://COPYING;md5=d3c315c6eaa43e07d8c130dc3a04a011"
SECTION = "BSP"

PROVIDES = "imx-sc-firmware"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${PV}:${THISDIR}/${PN}:"

# Need tq_imx-scfw-v1.2.7-b3357 or newer for build
# (use SC_FIRMWARE_VERSION_TQ in package name)

SC_FIRMWARE_VERSION_TQ ?= "TQMa8.NXP-v1.13.0.B5561.0034"
SC_FIRMWARE_DBG ??= "0"
SC_FIRMWARE_NAME_TQ ?= "${SC_FIRMWARE_VERSION_TQ}${@'_dbg' if d.getVar('SC_FIRMWARE_DBG') == '1' else ''}"

SRC_URI = "\
    file://${BPN}-${SC_FIRMWARE_NAME_TQ}.tar.gz \
"

S = "${WORKDIR}/${BPN}-${SC_FIRMWARE_VERSION_TQ}"

# clear vars to prevent default assignments
SC_FIRMWARE_NAME = "invalid"

# TODO: move firmware name to machine config.
SC_FIRMWARE_NAME:tqma8xdp-1gb-mba8xx = "mx8qx-tqma8xqp-1gb-mba8xx-scfw-tcm.bin"
SC_FIRMWARE_NAME:tqma8xqp-1gb-mba8xx = "mx8qx-tqma8xqp-1gb-mba8xx-scfw-tcm.bin"
SC_FIRMWARE_NAME:tqma8xqp-2gb-mba8xx = "mx8qx-tqma8xqp-2gb-mba8xx-scfw-tcm.bin"
SC_FIRMWARE_NAME:tqma8xdp4-mba8xx = "mx8qx-tqma8xqp4-mba8xx-scfw-tcm.bin"
SC_FIRMWARE_NAME:tqma8xqp4-mba8xx = "mx8qx-tqma8xqp4-mba8xx-scfw-tcm.bin"

SC_FIRMWARE_NAME:tqma8xqps-mb-smarc-2 = "mx8qx-tqma8xqps-mb-smarc-2-scfw-tcm.bin"
SC_FIRMWARE_NAME:tqma8xdps-mb-smarc-2 = "mx8qx-tqma8xqps-mb-smarc-2-scfw-tcm.bin"
SC_FIRMWARE_NAME:tqma8xqps-mb-smarc-2-r0200 = "mx8qx-tqma8xqps-mb-smarc-2-scfw-tcm.bin"

SC_FIRMWARE_NAME:tqma8qm-4gb-mba8x = "mx8qm-tqma8qm-4gb-scfw-tcm.bin"
SC_FIRMWARE_NAME:tqma8qm-8gb-mba8x = "mx8qm-tqma8qm-8gb-scfw-tcm.bin"

inherit deploy

symlink_name = "scfw_tcm.bin"

BOOT_TOOLS = "imx-boot-tools"

do_compile[noexec] = "1"

do_install[noexec] = "1"

do_deploy() {
    install -Dm 0644 ${S}/${SC_FIRMWARE_NAME} ${DEPLOYDIR}/${BOOT_TOOLS}/${SC_FIRMWARE_NAME}
    ln -sf ${SC_FIRMWARE_NAME} ${DEPLOYDIR}/${BOOT_TOOLS}/${symlink_name}
}
addtask deploy after do_install

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "(tqma8xx|tqma8xxs|tqma8x)"