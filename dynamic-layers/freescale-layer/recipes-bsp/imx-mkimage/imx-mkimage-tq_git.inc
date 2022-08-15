# Copyright 2017-2021 NXP
# Copyright 2021-2022 TQ-Systems GmbH

DEPENDS = "\
    openssl-native \
    zlib-native \
"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# TODO: update to lf-5.15.5_1.0.0
SRCBRANCH = "lf-5.10.y_2.0.0"
SRC_URI = "\
    git://source.codeaurora.org/external/imx/imx-mkimage.git;protocol=https;branch=${SRCBRANCH} \
    file://0001-mkimage_fit_atf-fix-fit-generator-node-naming.patch \
    file://0001-Add-LDFLAGS-to-link-step.patch \
    file://0001-iMX8M-soc.mak-use-native-mkimage-from-sysroot.patch \
"

SRCREV = "5138add7602a50d1730a54d0b67219f0ce0666b4"
S = "${WORKDIR}/git"
