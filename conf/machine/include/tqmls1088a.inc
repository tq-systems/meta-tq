# Common definitions for TQ-Systems GmbH TQMLS1088A modules

MACHINEOVERRIDES =. "fsl-lsch3:ls1088a:tqmls10xxa:tqmls1088a:"

# TQ-Systems U-Boot and kernel has to be set before qoriq-arm64.inc
# to allow weak settings
#
PREFERRED_PROVIDER_u-boot ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/kernel ?= "linux-lsdk-tq"

require conf/machine/include/tqmls10xxa.inc

UBOOT_ENTRYPOINT:ls1088a = "0x80008000"

# Image Options
#
MKUBIFS_ARGS = " -m 1 -e 65408 -c 640 "
UBINIZE_ARGS = " -p 65536 -m 1 -s 1 "

# DPAA2 manament utility
MACHINE_EXTRA_RRECOMMENDS += "restool"

# needs to be set to have it consistent for kernel command line
UBI_VOLNAME = "root"
