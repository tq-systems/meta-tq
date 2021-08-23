# Common definitions for TQ-Group TQMLS1088A modules

MACHINEOVERRIDES =. "fsl-lsch3:ls1088a:tqmls10xxa:tqmls1088a:"

require conf/machine/include/tqmls10xxa.inc

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

PREFERRED_PROVIDER_virtual/kernel_tqmls1088a ?= "linux-lsdk-tq"

UBOOT_ENTRYPOINT_ls1088a = "0x80008000"

# Image Options
#
MKUBIFS_ARGS = " -m 1 -e 65408 -c 640 "
UBINIZE_ARGS = " -p 65536 -m 1 -s 1 "

# DPAA2 manament utility
IMAGE_INSTALL_append = " restool"

# needs to be set to have it consistent for kernel command line
UBI_VOLNAME = "root"

# different RCW files for booting CPU from SD card and eMMC require two
# wks-files for image creation:
# tqmls1088a-sdimg.wks.in -> to boot from SD-Card
# tqmls1088a-emmcimg.wks.in -> to boot from eMMC
WKS_FILE_${MACHINE} ?= "tqmls1088a-sdimg.wks.in"
