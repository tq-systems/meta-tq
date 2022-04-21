# Common definitions for TQ-Systems GmbH TQMLS1043A modules on MBLS10xxA

require conf/machine/include/tqmls1043a.inc
require conf/machine/include/tqmls104xa-mbls10xxa.inc

KERNEL_DEVICETREE ?= " \
    freescale/fsl-tqmls1043a-mbls10xxa.dtb \
"

IMAGE_BOOT_FILES = "\
    ${KERNEL_IMAGETYPE};linuximage \
    fsl-tqmls1043a-mbls10xxa.dtb \
"
