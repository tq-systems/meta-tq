# Copyright 2017-2021 NXP
# Copyright 2021-2022 TQ-Systems GmbH

DEPENDS = "\
    openssl-native \
    zlib-native \
"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRCBRANCH = "lf-5.15.52_2.1.0"
BRANCH_SPEC = "branch=${SRCBRANCH}"
SRCREV = "37648c8ba5094420faa8206d8cb8e0c6f17d72fc"

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
