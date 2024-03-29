require recipes-bsp/u-boot/u-boot.inc
require u-boot-tq.inc

DESCRIPTION = "Mainline U-Boot for TQ-Systems GmbH modules"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=5a7450c57ffe5ae63fd732446b988025"

DEPENDS += "xxd-native bison-native"

SRCREV = "69d9eda4da13ecabb8002fce0dded4bba3bff9f9"

SRC_URI = "git://git.denx.de/u-boot.git;branch=master;protocol=git"

COMPATIBLE_MACHINE = "tqma6s|tqma6q"
