# Common definitions for TQ-Systems GmbH TQMa6 SOM

# optionally source the machine specific features for multimedia etc. from
# meta-freescale

MACHINEOVERRIDES =. "imx:mx6:tqma6x:"

include conf/machine/include/imx-base.inc

require conf/machine/include/arm/armv7a/tune-cortexa9.inc

# TQ u-boot and kernel
#
require conf/machine/include/tqmxx.inc

# Machine specific options

MACHINE_EXTRA_RRECOMMENDS += "\
    firmware-imx-vpu-imx6d \
    firmware-imx-vpu-imx6q \
    kernel-devicetree \
    kernel-modules \
    linux-firmware-imx-sdma-imx6q \
"

MACHINE_FEATURES += "\
    alsa \
    can \
    ext2 \
    pci \
    rtc \
    serial \
    ubi \
    usbhost \
    vfat \
    vpu \
"

KERNEL_IMAGETYPE = "zImage"

PREFERRED_PROVIDER_u-boot:mx6-generic-bsp ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader:mx6-generic-bsp ?= "u-boot-tq"
UBOOT_MAKE_TARGET:mx6-generic-bsp ?= "u-boot-with-spl.imx"
UBOOT_SUFFIX:mx6-generic-bsp = "imx"
UBOOT_BINARY:mx6-generic-bsp = "${UBOOT_MAKE_TARGET}"
IMX_DEFAULT_BOOTLOADER ?= "u-boot-tq"

# note: These settings are only usable with larger SPI NOR
# ubinize PEB_SIZE="65536"
# ubinize MINIMUM_IO_UNIT_SIZE="1"
# ubinize SUB_PAGE_SIZE="1"
# mkubifs VID_HEADER_OFFSET="64"
# mkubifs LEB_SIZE="65408"

MKUBIFS_ARGS = " -m 1 -e 65408 -c 896 "
UBINIZE_ARGS = " -p 65536 -m 1 -s 1 "
# needs to be set to have it consistent for kernel command line
UBI_VOLNAME = "root"

PREFERRED_VERSION_linux-imx-tq ?= "5.15%"

# Common image configuration
require conf/machine/include/tq-image.inc
