# SPDX-License-Identifier: MIT
# Copyright (C) 2023 TQ-Systems GmbH <oss@ew.tq-group.com>
#
# This class creates a second bootonly wic image and replaces its bl2 pbl
# with an alternative pbl. This class expects a bootonly image generated
# from image_type_bootonly class and works for Layerscape modules only.
# It is useful to generate bootonly images for different memory
# configurations.

inherit image-artifact-names

IMAGE_TYPEDEP:pblvariant = "bootonly"

IMAGE_TYPES += "pblvariant"
CONVERSIONTYPES += "pblvariant"

PBL_VARIANT ?= ""

generate_pblvariant_image() {
    local type=${1}
    local pbl_file=${DEPLOY_DIR_IMAGE}/atf/${2}.pbl
    local bootonlyfile="${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.${type}"
    local outfile="${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.${type}.pblvariant"

    if [ -z ${type} ] || [ "${type}" != "wic.bootonly" ]; then
        bberror "pblvariant image can only be generated from wic.bootonly image."
        exit 1
    fi

    if [ ! -e ${pbl_file} ]; then
        bberror "PBL file ${pbl_file} not found."
        exit 1
    fi

    if [ "$(expr ${IMAGE_OFFSET_PBL} + ${IMAGE_MAXSIZE_PBL})" -gt "${IMAGE_OFFSET_FIP}" ]; then
        bberror "PBL file offset or size to big."
        exit 1
    fi

    # Make copy and replace bl2 pbl with variant
    dd if=${bootonlyfile} of=${outfile}
    dd if=/dev/zero of=${outfile} bs=1k seek=${IMAGE_OFFSET_PBL} count=${IMAGE_MAXSIZE_PBL} conv=notrunc
    dd if=${pbl_file} of=${outfile} bs=1k seek=${IMAGE_OFFSET_PBL} conv=notrunc
}

CONVERSION_CMD:pblvariant = "generate_pblvariant_image ${type} ${PBL_VARIANT}"

CONVERSION_DEPENDS_bootonly += "\
    coreutils-native \
"
