# Common definitions for TQ-Systems GmbH TQMa6 modules

# optionally source the machine specific features for multimedia etc. from
# meta-freescale

MACHINEOVERRIDES =. "imx:mx6:tqma6x:"

include conf/machine/include/imx-base.inc

DEFAULTTUNE ?= "cortexa9thf-neon"
require conf/machine/include/arm/armv7a/tune-cortexa9.inc

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

# TQ u-boot and kernel

require conf/machine/include/tqmxx.inc

KERNEL_IMAGETYPE = "zImage"

PREFERRED_PROVIDER_u-boot:tqma6x ?= "u-boot-imx-tq"
PREFERRED_PROVIDER_virtual/bootloader:tqma6x ?= "u-boot-imx-tq"

UBOOT_SUFFIX:tqma6x ?= "imx"
UBOOT_MAKE_TARGET:tqma6x ?= "u-boot.imx"

# Image Options

EXTRA_IMAGEDEPENDS += "\
    virtual/bootloader \
    virtual/kernel \
"

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

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

PREFERRED_VERSION_linux-imx-tq = "5.15%"
