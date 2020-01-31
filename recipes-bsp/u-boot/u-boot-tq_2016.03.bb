DESCRIPTION = "u-boot for TQ-Group Freescale i.MX based modules"

require u-boot-tq-common_${PV}.inc
require recipes-bsp/u-boot/u-boot.inc

SECTION = "bootloader"

PROVIDES += "virtual/bootloader"
PROVIDES += "u-boot"

PACKAGE_ARCH = "${MACHINE_ARCH}"
