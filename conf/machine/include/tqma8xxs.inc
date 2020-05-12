# Common definitions for TQ-Group TQMa8XxS b0 modules

MACHINEOVERRIDES =. "tqma8xxs:"

require conf/machine/include/tqma8xx-common.inc

# temporary, until we port to new U-Boot
UBOOT_MAKE_TARGET = "u-boot.bin"
UBOOT_SUFFIX = "bin"
SPL_BINARY = ""
