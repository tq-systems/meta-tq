# Common definitions for TQ-Systems GmbH TQMLS1043A modules on MBLS10xxA

require conf/machine/include/tqmls1043a.inc
require conf/machine/include/tqmls104xa-mbls10xxa.inc

KERNEL_DEVICETREE ?= " \
    freescale/fsl-ls1043a-tqmls1043a-mbls10xxa.dtb \
"

UBOOT_CONFIG ?= "tfa"
UBOOT_CONFIG[tfa] = "tqmls1043a_mbls10xxa_tfa_defconfig"

RCWSD ?= "default/rcw_1000_3358_sd"
RCWQSPI ?= "default/rcw_1000_3358_qspi"
ATF_RCW_VARIANTS ?= " \
    default/rcw_1000_1355_sd \
    default/rcw_1000_1355_qspi \
    default/rcw_1000_1455_sd \
    default/rcw_1000_1455_qspi \
    default/rcw_1000_3335_sd \
    default/rcw_1000_3335_qspi \
    default/rcw_1000_3355_sd \
    default/rcw_1000_3355_qspi \
    default/rcw_1000_3358_sd \
    default/rcw_1000_3358_qspi \
"
