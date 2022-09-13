# SPDX-License-Identifier: MIT
# Copyright (C) 2022 TQ-Systems GmbH <oss@ew.tq-group.com>
#
# This class converts a wic file (for instance for TI SOC) that has as first
# partition a (VFAT) boot partition with images and firmware needed to boot
# into the bootloader. It generates a SD-CARD image consisting only of the
# partition table and all data up to the end of said first partition.
# Other partitions are deleted from the partition table as well.
# The image therefore contains the bare minimum needed to boot such a CPU 
# and start the bootloader from SD / e-MMC
#
# ATTENTION: it is expected, that partitions are numbered according to
# their placement on disk, that the first partition is a primary partition
# and that there is no data stored in unpartitioned area between partitions.

inherit image-artifact-names

IMAGE_TYPES += "wic.bootonly"

CONVERSIONTYPES += "bootonly"

#
# Since this is a special type of conversion we use a script to
# handle intermediate variables and keep readability.
# The script has to be supplied with the ${type} variable to have
# the correct file names
#
generate_bootonly_image() {
    local type=${1}
    local sector="0"
    local wicfile="${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.${type}"
    local outfile="${wicfile}.bootonly"
    local reverse_part_list=""

    if [ -z ${type} ] || [ "${type}" != "wic" ]; then
        bberror "bootonly image can only be generated from wic image."
        exit 1
    fi

    reverse_part_list=$(partx --show --noheadings  --output NR "${wicfile}" | tac)
    if [ "$(echo ${reverse_part_list} | wc -w)" -le "1" ]; then
        bberror "Unsupported wic image structure."
        exit 1
    fi

    if [ "$(partx  --show --noheadings --nr 1 --output SCHEME "${wicfile}")" != "dos" ]; then
        bberror "wic image with gpt is not handled."
        exit 1
    fi

    sector=$(expr $(partx --nr 1 --bytes --noheadings --output END "${wicfile}") + 1)
    if [ -z ${sector} ] || [ "${sector}" -le "0" ]; then
        bberror "Unsupported wic image structure."
        exit 1
    fi

    dd if="${wicfile}" of="${outfile}" bs=512 count=${sector}
    # Remove all but first partition from partition table.
    reverse_part_list=${reverse_part_list% *}
    for part in ${reverse_part_list}; do
        bbdebug 1 "delete P${part}  - ${outfile}";
        sfdisk --delete "${outfile}" ${part};
    done
}

CONVERSION_CMD:bootonly = "generate_bootonly_image ${type}"

# we need dd / partx and sfdisk, so add dependencies
# note: this is not an override, hence the underscore is needed
CONVERSION_DEPENDS_bootonly += "\
    coreutils-native \
    util-linux-native \
"
