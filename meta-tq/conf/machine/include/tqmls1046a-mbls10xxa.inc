# Common definitions for TQ-Systems GmbH TQMLS1046A modules on MBLS10xxA

require conf/machine/include/tqmls1046a.inc
require conf/machine/include/tqmls104xa-mbls10xxa.inc

KERNEL_DEVICETREE ?= " \
    freescale/fsl-ls1046a-tqmls1046a-mbls10xxa.dtb \
"

UBOOT_CONFIG ?= "tfa"
UBOOT_CONFIG[tfa] = "tqmls1046a_mbls10xxa_tfa_defconfig"

RCWSD ?= "default/rcw_1800_3333_5559_sd"
RCWQSPI ?= "default/rcw_1800_3333_5559_qspi"
ATF_RCW_VARIANTS ?= "\
    default/rcw_1800_1040_5506_qspi \
    default/rcw_1800_1040_5506_sd \
    default/rcw_1800_1040_5559_qspi \
    default/rcw_1800_1040_5559_sd \
    default/rcw_1800_1040_5577_qspi \
    default/rcw_1800_1040_5577_sd \
    default/rcw_1800_1040_5A06_qspi \
    default/rcw_1800_1040_5A06_sd \
    default/rcw_1800_1040_5A59_qspi \
    default/rcw_1800_1040_5A59_sd \
    default/rcw_1800_1133_5506_qspi \
    default/rcw_1800_1133_5506_sd \
    default/rcw_1800_1133_5559_qspi \
    default/rcw_1800_1133_5559_sd \
    default/rcw_1800_1133_5577_qspi \
    default/rcw_1800_1133_5577_sd \
    default/rcw_1800_1133_5A06_qspi \
    default/rcw_1800_1133_5A06_sd \
    default/rcw_1800_1133_5A59_qspi \
    default/rcw_1800_1133_5A59_sd \
    default/rcw_1800_3333_5506_qspi \
    default/rcw_1800_3333_5506_sd \
    default/rcw_1800_3333_5559_qspi \
    default/rcw_1800_3333_5559_sd \
    default/rcw_1800_3333_5577_qspi \
    default/rcw_1800_3333_5577_sd \
    default/rcw_1800_3333_5A06_qspi \
    default/rcw_1800_3333_5A06_sd \
    default/rcw_1800_3333_5A59_qspi \
    default/rcw_1800_3333_5A59_sd \
"
