# Common definitions for TQ-Systems GmbH TQMLS1046A modules

MACHINEOVERRIDES =. "fsl-lsch2:ls1046a:tqmlsx:tqmls10xxa:tqmls104xa:tqmls1046a:"

# TQ-Systems U-Boot and kernel has to be set before qoriq-arm64.inc
# to allow weak settings
#
PREFERRED_PROVIDER_u-boot ?= "u-boot-lsdk-tq"
PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-lsdk-tq"
PREFERRED_PROVIDER_virtual/kernel ?= "linux-tq"

require conf/machine/include/tqmls10xxa.inc

UBOOT_ENTRYPOINT:ls1046a = "0x80008000"

# Image Options
#

IMAGE_OFFSET_FMAN_UCODE ?= "9216"
FMAN_UCODE ?= "fsl_fman_ucode_ls1046_r1.0_106_4_18.bin"
