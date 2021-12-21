require conf/machine/include/tqmarzg2x.inc

SOC_FAMILY = "r8a774b1"
MACHINEOVERRIDES =. "r8a774b1:tqmarzg2n_b:"

DEFAULTTUNE ?= "cortexa57"
TUNE_CONF ?= "conf/machine/include/tune-cortexa57.inc"
require conf/machine/include/${SOC_FAMILY}.inc
require ${TUNE_CONF}
