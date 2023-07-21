# Common definitions for TQ-Systems GmbH TQMLS1046A modules on MBLS10xxA

require conf/machine/include/tqmls1046a.inc
require conf/machine/include/tqmls104xa-mbls10xxa.inc

KERNEL_DEVICETREE ?= " \
    freescale/fsl-ls1046a-tqmls1046a-mbls10xxa.dtb \
"
