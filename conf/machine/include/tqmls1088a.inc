# Common definitions for TQ-Systems GmbH TQMLS1088A modules

MACHINEOVERRIDES =. "fsl-lsch3:ls1088a:tqmls10xxa:tqmls1088a:"

require conf/machine/include/tqmls10xxa.inc

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

PREFERRED_PROVIDER_virtual/kernel ??= "linux-lsdk-tq"

UBOOT_ENTRYPOINT_ls1088a = "0x80008000"

# Image Options
#
MKUBIFS_ARGS = " -m 1 -e 65408 -c 640 "
UBINIZE_ARGS = " -p 65536 -m 1 -s 1 "

# DPAA2 manament utility
MACHINE_EXTRA_RRECOMMENDS += "restool"

# needs to be set to have it consistent for kernel command line
UBI_VOLNAME = "root"
