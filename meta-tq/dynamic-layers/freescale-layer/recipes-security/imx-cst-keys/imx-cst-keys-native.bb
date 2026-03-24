# SPDX-License-Identifier: MIT
#
# Copyright (C) 2023 TQ-Systems GmbH <oss@ew.tq-group.com>, D-82229 Seefeld, Germany.

inherit imx-hab native

DESCRIPTION = "Signing keys for i.MX High Assurance Boot"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
SECTION = "security"

SRC_URI = " \
    file://hab4/CSF1_1_sha256_2048_65537_v3_usr_crt.pem \
    file://hab4/CSF1_1_sha256_2048_65537_v3_usr_key.pem \
    file://hab4/IMG1_1_sha256_2048_65537_v3_usr_crt.pem \
    file://hab4/IMG1_1_sha256_2048_65537_v3_usr_key.pem \
    file://hab4/SRK_1_2_3_4_table.bin \
    file://hab4/key_pass.txt \
    file://ahab/SRK1_sha512_secp521r1_v3_usr_crt.pem \
    file://ahab/SRK1_sha512_secp521r1_v3_usr_key.pem \
    file://ahab/SRK_1_2_3_4_table.bin \
    file://ahab/key_pass.txt \
"

# This recipe can be extended with additional signing keys from a bbappend. Each
# set of keys should be installed to a separate directory below ${datadir}/${BPN},
# so it can be selected for a build using IMX_HAB_KEY_NAME.

do_install() {
   local in="${UNPACKDIR}/hab4" out="${D}${datadir}/${BPN}/hab4"

   install -DT -m644 ${in}/CSF1_1_sha256_2048_65537_v3_usr_crt.pem ${out}/${IMX_HAB_CSF_CERT}
   install -DT -m600 ${in}/CSF1_1_sha256_2048_65537_v3_usr_key.pem ${out}/${IMX_HAB_CSF_KEY}
   install -DT -m644 ${in}/IMG1_1_sha256_2048_65537_v3_usr_crt.pem ${out}/${IMX_HAB_IMG_CERT}
   install -DT -m600 ${in}/IMG1_1_sha256_2048_65537_v3_usr_key.pem ${out}/${IMX_HAB_IMG_KEY}
   install -DT -m644 ${in}/SRK_1_2_3_4_table.bin ${out}/${IMX_HAB_SRK_TABLE}
   install -DT -m600 ${in}/key_pass.txt ${out}/${IMX_HAB_KEY_PASS}

   local in="${UNPACKDIR}/ahab" out="${D}${datadir}/${BPN}/ahab"

   install -DT -m644 ${in}/SRK1_sha512_secp521r1_v3_usr_crt.pem ${out}/${IMX_HAB_SRK_CERT}
   install -DT -m644 ${in}/SRK1_sha512_secp521r1_v3_usr_key.pem ${out}/${IMX_HAB_SRK_KEY}
   install -DT -m644 ${in}/SRK_1_2_3_4_table.bin ${out}/${IMX_HAB_SRK_TABLE}
   install -DT -m600 ${in}/key_pass.txt ${out}/${IMX_HAB_KEY_PASS}
}
