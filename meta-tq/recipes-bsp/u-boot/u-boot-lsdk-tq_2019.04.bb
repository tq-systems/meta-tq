require recipes-bsp/u-boot/u-boot.inc
require u-boot-tq.inc

DESCRIPTION = "u-boot for TQ-Systems GmbH LX2160A based modules"

PROVIDES += "u-boot"

# target not supported in U-Boot before v2019.07
UBOOT_INITIAL_ENV = ""

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

DEPENDS += "bison-native"

SRCREV = "e2b38dc398203f043d607e7de3fa192b4c99f009"
SRCBRANCH = "TQMLX2160A-v2019.04-lx2160a-early-access-bsp0.7"

COMPATIBLE_MACHINE = "tqmlx2160a"
