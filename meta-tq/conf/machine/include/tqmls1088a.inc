# Common definitions for TQ-Systems GmbH TQMLS1088A modules

MACHINEOVERRIDES =. "fsl-lsch3:ls1088a:tqmlsx:tqmls10xxa:tqmls1088a:"

# TQ-Systems U-Boot and kernel has to be set before qoriq-arm64.inc
# to allow weak settings
#
PREFERRED_PROVIDER_u-boot ?= "u-boot-lsdk-tq"
PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-lsdk-tq"
PREFERRED_PROVIDER_virtual/kernel ?= "linux-lsdk-tq"

require conf/machine/include/tqmls10xxa.inc

UBOOT_ENTRYPOINT:ls1088a = "0x80008000"

# Image Options
#

# DPAA2 manament utility
MACHINE_EXTRA_RRECOMMENDS += "restool"
