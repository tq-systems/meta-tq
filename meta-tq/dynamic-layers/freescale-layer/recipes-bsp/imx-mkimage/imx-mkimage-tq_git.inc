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

SRCBRANCH = "lf-6.6.36_2.1.0"
BRANCH_SPEC = "branch=${SRCBRANCH}"
SRCREV = "4622115cbc037f79039c4522faeced4aabea986b"

SRC_URI = "\
    git://github.com/nxp-imx/imx-mkimage.git;protocol=https;${BRANCH_SPEC} \
    file://0001-iMX8M-soc.mak-use-native-mkimage-from-sysroot.patch \
    file://0002-iMX9-remove-unused-targets-from-soc.mak.patch \
    file://0003-iMX8DXL-remove-unused-targets.patch \
    file://0004-iMX8QM-remove-unused-target.patch \
    file://0005-iMX8QX-remove-unused-target.patch \
    file://0006-iMX8ULP-remove-unused-targets.patch \
    file://0007-iMX8M-add-mkimage_imx8-wrapper-to-redirect-output.patch \
    file://0008-iMX8M-redirect-more-messages-to-stderr.patch \
    file://0009-iMX8M-print_fit_hab-fix-output-for-symlinked-inputs.patch \
    file://0010-Add-generic-wrapper-for-mkimage_imx8.patch \
    file://0011-iMX8-9-redirect-more-messages-to-stderr.patch \
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
