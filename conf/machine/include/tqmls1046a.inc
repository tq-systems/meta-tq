# Common definitions for TQ-Systems GmbH TQMLS1046A modules

MACHINEOVERRIDES =. "fsl-lsch2:ls1046a:tqmls10xxa:"

require conf/machine/include/tqmls10xxa.inc

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

UBOOT_ENTRYPOINT:ls1046a = "0x80008000"

# Image Options
#
MKUBIFS_ARGS = " -m 1 -e 65408 -c 640 "
UBINIZE_ARGS = " -p 65536 -m 1 -s 1 "
# needs to be set to have it consistent for kernel command line
UBI_VOLNAME = "root"

IMAGE_OFFSET_FMAN_UCODE ?= "9216"
FMAN_UCODE ?= "fsl_fman_ucode_ls1046_r1.0_106_4_18.bin"
