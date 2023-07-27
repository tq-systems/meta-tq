# Common definitions for TQ-Systems GmbH TQMLS1046A modules

MACHINEOVERRIDES =. "fsl-lsch2:ls1046a:tqmlsx:tqmls10xxa:tqmls104xa:tqmls1046a:"

require conf/machine/include/tqmls10xxa.inc

UBOOT_ENTRYPOINT:ls1046a = "0x80008000"

# Image Options
#

IMAGE_OFFSET_FMAN_UCODE ?= "9216"
FMAN_UCODE ?= "fsl_fman_ucode_ls1046_r1.0_106_4_18.bin"
