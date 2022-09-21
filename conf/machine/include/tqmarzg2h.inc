require conf/machine/include/tqmarzg2x.inc

SOC_FAMILY = "r8a774e1"
MACHINEOVERRIDES =. "r8a774e1:tqmarzg2h:"

DEFAULTTUNE ?= "cortexa57-cortexa53"
TUNE_CONF ?= "conf/machine/include/tune-cortexa57-cortexa53.inc"
require conf/machine/include/${SOC_FAMILY}.inc
require ${TUNE_CONF}
