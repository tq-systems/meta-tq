# SPDX-License-Identifier: MIT
#
# Copyright (C) 2023 TQ-Systems GmbH <oss@ew.tq-group.com>, D-82229 Seefeld, Germany.

inherit imx-hab native

DESCRIPTION = "Signing keys for i.MX High Assurance Boot"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
SECTION = "security"

SRC_URI = " \
    file://example/CSF1_1_sha256_2048_65537_v3_usr_crt.pem \
    file://example/CSF1_1_sha256_2048_65537_v3_usr_key.pem \
    file://example/IMG1_1_sha256_2048_65537_v3_usr_crt.pem \
    file://example/IMG1_1_sha256_2048_65537_v3_usr_key.pem \
    file://example/SRK_1_2_3_4_table.bin \
    file://example/key_pass.txt \
"

# This recipe can be extended with additional signing keys from a bbappend. Each
# set of keys should be installed to a separate directory below ${datadir}/${BPN},
# so it can be selected for a build using IMX_HAB_KEY_NAME.

do_install() {
   local in="${WORKDIR}/example" out="${D}${datadir}/${BPN}/example"

   install -DT -m644 ${in}/CSF1_1_sha256_2048_65537_v3_usr_crt.pem ${out}/${IMX_HAB_CSF_CERT}
   install -DT -m600 ${in}/CSF1_1_sha256_2048_65537_v3_usr_key.pem ${out}/${IMX_HAB_CSF_KEY}
   install -DT -m644 ${in}/IMG1_1_sha256_2048_65537_v3_usr_crt.pem ${out}/${IMX_HAB_IMG_CERT}
   install -DT -m600 ${in}/IMG1_1_sha256_2048_65537_v3_usr_key.pem ${out}/${IMX_HAB_IMG_KEY}
   install -DT -m644 ${in}/SRK_1_2_3_4_table.bin ${out}/${IMX_HAB_SRK_TABLE}
   install -DT -m600 ${in}/key_pass.txt ${out}/${IMX_HAB_KEY_PASS}
}
