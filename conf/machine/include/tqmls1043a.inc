# Common definitions for TQ-Group TQMLS1043A modules

MACHINEOVERRIDES =. "fsl-lsch2:ls1043a:tqmls10xxa:"

require conf/machine/include/tqmls10xxa.inc

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

UBOOT_ENTRYPOINT_ls1043a = "0x80008000"

# Image Options
#
MKUBIFS_ARGS = " -m 1 -e 65408 -c 640 "
UBINIZE_ARGS = " -p 65536 -m 1 -s 1 "
# needs to be set to have it consistent for kernel command line
UBI_VOLNAME = "root"

WKS_FILE ?= "tqmls1043a-sdimg.wks"
