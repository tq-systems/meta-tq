# Copyright 2017-2021 NXP
# Copyright 2021 TQ-Systems GmbH

DEPENDS = "zlib-native openssl-native"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRCBRANCH = "lf-5.10.y_2.0.0"
SRC_URI = "git://source.codeaurora.org/external/imx/imx-mkimage.git;protocol=https;branch=${SRCBRANCH} \
           file://0001-mkimage_fit_atf-fix-fit-generator-node-naming.patch \
"

SRCREV = "5138add7602a50d1730a54d0b67219f0ce0666b4"
S = "${WORKDIR}/git"
