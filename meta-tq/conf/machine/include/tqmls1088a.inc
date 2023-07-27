# Common definitions for TQ-Systems GmbH TQMLS1088A modules

MACHINEOVERRIDES =. "fsl-lsch3:ls1088a:tqmlsx:tqmls10xxa:tqmls1088a:"

require conf/machine/include/tqmls10xxa.inc

UBOOT_ENTRYPOINT:ls1088a = "0x80008000"

# Image Options
#

# DPAA2 manament utility
MACHINE_EXTRA_RRECOMMENDS += "restool"
