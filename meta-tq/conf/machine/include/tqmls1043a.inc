# Common definitions for TQ-Systems GmbH TQMLS1043A modules

MACHINEOVERRIDES =. "fsl-lsch2:ls1043a:tqmlsx:tqmls10xxa:tqmls104xa"

# TQ-Systems U-Boot and kernel has to be set before qoriq-arm64.inc
# to allow weak settings
#
PREFERRED_PROVIDER_u-boot ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/kernel ?= "linux-tq"

require conf/machine/include/tqmls10xxa.inc

UBOOT_ENTRYPOINT:ls1043a = "0x80008000"

# Image Options
#
MKUBIFS_ARGS = " -m 1 -e 65408 -c 640 "
UBINIZE_ARGS = " -p 65536 -m 1 -s 1 "
# needs to be set to have it consistent for kernel command line
UBI_VOLNAME = "root"

IMAGE_OFFSET_FMAN_UCODE ?= "9216"
FMAN_UCODE ?= "fsl_fman_ucode_ls1043_r1.1_106_4_18.bin"
