# Common definitions for TQ-Systems GmbH TQMa8Xx modules (C0 CPU step)

MACHINEOVERRIDES =. "tqma8xx:"

require conf/machine/include/tqma8xx-common.inc

# Debug UART index. To activate 'ATF_IMX_DEBUG_CONSOLE' must be set to '1'
ATF_IMX_DEBUG_UART ?= "1"
