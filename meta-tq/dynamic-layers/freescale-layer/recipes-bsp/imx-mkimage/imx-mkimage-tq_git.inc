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

SRCBRANCH = "lf-6.1.55_2.2.0"
BRANCH_SPEC = "branch=${SRCBRANCH}"
SRCREV = "c4365450fb115d87f245df2864fee1604d97c06a"

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
"

SRC_URI:append:mx8m-generic-bsp = "\
    file://csf_fit.txt.in \
    file://csf_spl.txt.in \
"

S = "${WORKDIR}/git"
