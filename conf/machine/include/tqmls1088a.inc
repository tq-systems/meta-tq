# Common definitions for TQ-Group TQMLS1088A modules

require conf/machine/include/tqmls10xxa.inc

MACHINEOVERRIDES =. "fsl-lsch3:ls1088a:tqmls10xxa:"

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

UBOOT_ENTRYPOINT_ls1088a = "0x80008000"
MC_BOARD_tqmls10xxa = "${MACHINE}"

# Image Options
#
MKUBIFS_ARGS = " -m 1 -e 65408 -c 640 "
UBINIZE_ARGS = " -p 65536 -m 1 -s 1 "

# DPAA2 manament utility
MACHINE_EXTRA_RRECOMMENDS_append = " restool"

# needs to be set to have it consistent for kernel command line
UBI_VOLNAME = "root"

# different RCW files for booting CPU from SD card and eMMC require two
# wks-files for image creation:
# tqmls1088a-sdimg.wks -> to boot from SD-Card
# tqmls1088a-emmcimg.wks -> to boot from eMMC
WKS_FILE ?= "tqmls1088a-sdimg.wks"
