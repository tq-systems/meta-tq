# This class converts wic file (for instance for TI SOC) that has as first
# partition a (VFAT) boot partition with images and firmware needed to boot
# into the bootloader. It generates a SD-CARD image consisting only of the
# partition table and all data up to the end of said fist partition.
# other partitions are deleted from the partition table as well
# The image therefore contains the bare minimum needed to boot such a CPU 
# and start the bootloader from SD / e-MMC

inherit image-artifact-names

IMAGE_TYPES += "wic.bootonly"

CONVERSIONTYPES += "bootonly"

CONVERSION_CMD_bootonly() {
	wicfile="${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.${type}"
	outfile="${wicfile}.bootonly"

	SECTORS=$(partx --nr 2 --bytes --noheadings --output START "${wicfile}")
	bbnote "SECTORS ${SECTORS}"
	dd if="${wicfile}" of="${outfile}" bs=512 count=${SECTORS}
	# remove second partition from partition table.
	sfdisk --delete ${outfile} 2
}

# we need dd / partx and sfdisk, so add dependencies
CONVERSION_DEPENDS_bootonly = "\
    coreutils-native \
    util-linux-native \
"
