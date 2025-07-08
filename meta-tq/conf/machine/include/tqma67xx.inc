# SPDX-License-Identifier: MIT
# Copyright (C) 2025 TQ-Systems GmbH <oss@ew.tq-group.com>, D-82229 Seefeld, Germany.

# Common definitions for TQ-Systems GmbH TQMa67xx[L] modules

# needed for easier compatible / preferred_provider
MACHINEOVERRIDES =. "tqma67xx:"

# Default providers, may need to override for specific machines
# needs to be set before inclusion of j722s.inc
PREFERRED_PROVIDER_virtual/kernel ?= "linux-ti-tq"
PREFERRED_PROVIDER_u-boot ?= "u-boot-tq"
PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-tq"

TQ_FW_ENV_CONFIGS = "mmc0 mmc1 spi"

# From meta-ti-bsp: PREFERRED_PROVIDER and tuning
require conf/machine/include/j722s.inc

# Available variants: j722s-hs-fs j722s-hs
SYSFW_DEFAULT_VARIANT ?= "j722s-hs-fs"

KERNEL_CLASSES += "ti-secdev"
KERNEL_IMAGETYPE = "fitImage"

# Both tiboot3 variants are installed to the boot partition
# The default variant is additionally installed as tiboot3.bin
IMAGE_BOOT_FILES = " \
    u-boot.${UBOOT_SUFFIX} \
    tiboot3-j722s-hs-fs-tqma67xx.bin \
    tiboot3-j722s-hs-tqma67xx.bin \
    tiboot3-${SYSFW_DEFAULT_VARIANT}-tqma67xx.bin;tiboot3.bin \
"

UBOOT_ENTRYPOINT = "0x81000000"
UBOOT_LOADADDRESS = "0x81000000"
UBOOT_DTB_LOADADDRESS = "0x83000000"
UBOOT_DTBO_LOADADDRESS = "0x83080000"
UBOOT_RD_LOADADDRESS = "0x84000000"
UBOOT_RD_ENTRYPOINT = "0x84000000"

# TQ u-boot and kernel

require conf/machine/include/tqmxx.inc

MACHINE_FEATURES:remove = "efi"
EFI_PROVIDER = ""

WIC_BOOTPART_SIZE = "12M"
WKS_FILE = "tqmaxx-ti-sdimg${@bb.utils.contains('DISTRO_FEATURES', 'rauc', '-rauc-ab', '', d)}.wks.in"

# List common SoC features
MACHINE_FEATURES += "\
    apm \
    can \
    ext2 \
    pci \
    ubi \
    usbgadget \
    usbhost \
    vfat \
"

# Common image configuration
require conf/machine/include/tq-image.inc

# TI image configuration
require conf/machine/include/tq-image-ti.inc
