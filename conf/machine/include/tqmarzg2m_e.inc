require conf/machine/include/tqmarzg2x.inc

SOC_FAMILY = "r8a774a1"
MACHINEOVERRIDES =. "r8a774a1:tqmarzg2m_e:"

DEFAULTTUNE ?= "cortexa57-cortexa53"
TUNE_CONF ?= "conf/machine/include/tune-cortexa57-cortexa53.inc"
require conf/machine/include/${SOC_FAMILY}.inc
require ${TUNE_CONF}
