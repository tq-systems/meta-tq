# Common definitions for TQ-Group TQMLS1046A modules

require conf/machine/include/tqmls10xxa.inc

MACHINEOVERRIDES =. "fsl-lsch2:ls1046a:tqmls10xxa:"

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

PREFERRED_PROVIDER_u-boot_ls1046a ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader_ls1046a ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/kernel_ls1046a ?= "linux-tq"

UBOOT_ENTRYPOINT_ls1046a = "0x80008000"

# Image Options
#
MKUBIFS_ARGS = " -m 1 -e 65408 -c 640 "
UBINIZE_ARGS = " -p 65536 -m 1 -s 1 "
# needs to be set to have it consistent for kernel command line
UBI_VOLNAME = "root"

WKS_FILE ?= "tqmls1046a-emmcimg.wks"

WKS_FILE_DEPENDS = "\
	mtools-native \
	dosfstools-native \
	parted-native \
	virtual/kernel \
	u-boot \
	"

