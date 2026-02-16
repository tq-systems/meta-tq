# SPDX-License-Identifier: MIT
#
# Copyright 2017-2021 NXP
# Copyright (c) 2021-2024 TQ-Systems GmbH <oss@ew.tq-group.com>,
# D-82229 Seefeld, Germany.

DEPENDS = "\
    openssl-native \
    zlib-native \
"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRCBRANCH = "lf-6.12.49_2.2.0"
SRCREV = "81fca6434be0610f3f9216a762aadc4dc3e8d8db"

SRC_URI = "\
    git://github.com/nxp-imx/imx-mkimage.git;protocol=https;branch=${SRCBRANCH} \
    file://0001-iMX8M-soc.mak-use-native-mkimage-from-sysroot.patch \
    file://0002-iMX93-remove-unused-targets-from-soc.mak.patch \
    file://0003-iMX91-remove-unused-targets-from-soc.mak.patch \
    file://0004-iMX95-remove-unused-targets-from-soc.mak.patch \
    file://0005-iMX8DXL-remove-unused-targets.patch \
    file://0006-iMX8QM-remove-unused-target.patch \
    file://0007-iMX8QX-remove-unused-target.patch \
    file://0008-iMX8ULP-remove-unused-targets.patch \
    file://0009-iMX8M-add-mkimage_imx8-wrapper-to-redirect-output.patch \
    file://0010-iMX8-9-redirect-more-messages-to-stderr.patch \
    file://0011-iMX8M-print_fit_hab-fix-output-for-symlinked-inputs.patch \
    file://0012-Add-generic-wrapper-for-mkimage_imx8.patch \
    file://0013-i.MX94-i.MX95-use-default-logik-for-QSPI_HEADER.patch \
"

SRC_URI:append:nxp-hab4 = "\
    file://csf_fit.txt.in \
    file://csf_spl.txt.in \
"

SRC_URI:append:nxp-ahab = "\
    file://csf_boot_image.txt.in \
    file://csf_uboot_atf.txt.in \
"

S = "${WORKDIR}/git"
