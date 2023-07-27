# Common definitions for TQ-Systems GmbH TQMLS1043A modules

MACHINEOVERRIDES =. "fsl-lsch2:ls1043a:tqmlsx:tqmls10xxa:tqmls104xa:tqmls1043a:"

require conf/machine/include/tqmls10xxa.inc

UBOOT_ENTRYPOINT:ls1043a = "0x80008000"

# Image Options
#

IMAGE_OFFSET_FMAN_UCODE ?= "9216"
FMAN_UCODE ?= "fsl_fman_ucode_ls1043_r1.1_106_4_18.bin"
