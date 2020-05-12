# Common definitions for TQ-Group TQMa8Xx b0 modules

MACHINEOVERRIDES =. "mx8qxpc0:tqma8xx:"

require conf/machine/include/tqma8xx-common.inc

UBOOT_MAKE_TARGET ?= "all"
SPL_BINARY ?= "spl/u-boot-spl.bin"
UBOOT_SUFFIX ?= "bin"
