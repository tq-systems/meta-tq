# SPDX-License-Identifier: MIT
#
# Copyright (C) 2023 TQ-Systems GmbH <oss@ew.tq-group.com>, D-82229 Seefeld, Germany.
#
# Helpers for i.MX High Assurance Boot signature generation

# Set in local.conf or distro config to enable signing.
# The key selected using this variable must be provided by the
# imx-cst-keys-native package
# (see meta-tq/dynamic-layers/freescale-layer/recipes-security/imx-cst-keys)
IMX_HAB_KEY_NAME ?= ""

# Because of the way the code signing tool works, the filenames of the keys
# and certificates always need to match
IMX_HAB_CSF_CERT = "crts/CSF_1_v3_usr_crt.pem"
IMX_HAB_CSF_KEY = "keys/CSF_1_v3_usr_key.pem"
IMX_HAB_IMG_CERT = "crts/IMG_1_v3_usr_crt.pem"
IMX_HAB_IMG_KEY = "keys/IMG_1_v3_usr_key.pem"
IMX_HAB_SRK_KEY = "keys/SRK_1_v3_usr_key.pem"
IMX_HAB_SRK_CERT = "crts/SRK_1_v3_usr_crt.pem"
IMX_HAB_SRK_TABLE = "crts/SRK_1_2_3_4_table.bin"
IMX_HAB_KEY_PASS = "keys/key_pass.txt"

IMX_HAB_CERT_FILES = " \
    ${IMX_HAB_CSF_CERT} \
    ${IMX_HAB_IMG_CERT} \
    ${IMX_HAB_SRK_TABLE} \
"

IMX_HAB_KEY_FILES = " \
    ${IMX_HAB_CSF_KEY} \
    ${IMX_HAB_IMG_KEY} \
    ${IMX_HAB_KEY_PASS} \
"

IMX_HAB_CERT_FILES:nxp-ahab = " \
    ${IMX_HAB_SRK_CERT} \
    ${IMX_HAB_SRK_TABLE} \
"

IMX_HAB_KEY_FILES:nxp-ahab = " \
    ${IMX_HAB_SRK_KEY} \
    ${IMX_HAB_KEY_PASS} \
"

# Search & replace of strings in a file
#
# Search and replacement string need to be in POSIX awk format, with embedded
# newlines escaped as \n and \ escaped as \\.
#
# Arguments:
# $1 - search string
# $2 - replacement string
# $3 - filename
imx_hab_replace_template_string() {
    awk --posix -v old="$1" -v new="$2" '{ gsub(old, new); print }' $3 > $3.new
    mv $3.new $3
}

# Builds a CSF input file based on a given template by filling in filenames
# and HAB block data
#
# Arguments:
# $1 - output filename
# $2 - input template filename
# $3 - HAB block list
imx_hab_generate_csf_hab4() {
    local output="$1" template="$2" hab_blocks="$3"

    cp ${template} ${output}
    imx_hab_replace_template_string @SRK_TABLE@ "${IMX_HAB_SRK_TABLE}" ${output}
    imx_hab_replace_template_string @CSF_CERT@ "${IMX_HAB_CSF_CERT}" ${output}
    imx_hab_replace_template_string @IMG_CERT@ "${IMX_HAB_IMG_CERT}" ${output}
    imx_hab_replace_template_string @HAB_BLOCKS@ "${hab_blocks}" ${output}
}

# Builds a CSF input file based on a given template by filling in filenames
# and AHAB block offsets
#
# Arguments:
# $1 - output filename
# $2 - input template filename
# $3 - AHAB block offsets
# $4 - input filename (file to be signed)
imx_hab_generate_csf_ahab() {
    local output="$1" template="$2" ahab_offsets="$3" img_file="$4"

    cp ${template} ${output}
    imx_hab_replace_template_string @SRK_TABLE@ "${IMX_HAB_SRK_TABLE}" ${output}
    imx_hab_replace_template_string @SRK_CERT@ "${IMX_HAB_SRK_CERT}" ${output}
    imx_hab_replace_template_string @AHAB_OFFSETS@ "${ahab_offsets}" ${output}
    imx_hab_replace_template_string @IMG_FILE@ "${img_file}" ${output}
}

# Checks whether signing keys are configured for installation by
# imx_hab_install_keys
imx_hab_check_keys_configured() {
    if [ -z "${IMX_HAB_KEY_NAME}" ]; then
        return 1
    fi
}

# Copies key data to a destination directory to be found by the
# code signing tool
#
# Arguments:
# $1 - destination directory
imx_hab_install_keys() {
    local dest="$1"

    imx_hab_check_keys_configured

    local src="${STAGING_DATADIR_NATIVE}/imx-cst-keys/${IMX_HAB_KEY_NAME}"

    for file in ${IMX_HAB_CERT_FILES}; do
        install -DT -m644 ${src}/${file} ${dest}/${file}
    done
    for file in ${IMX_HAB_KEY_FILES}; do
        install -DT -m600 ${src}/${file} ${dest}/${file}
    done
}

# Sets HAB_TYPE variable based on MACHINEOVERRIDES. The variable value can
# be checked from inside a shell function
HAB_TYPE ??= "INVALID"
HAB_TYPE:nxp-ahab = "ahab"
HAB_TYPE:nxp-hab4 = "hab4"
