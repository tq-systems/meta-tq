require recipes-bsp/u-boot/u-boot.inc
inherit gitpkgv-nonexact

PROVIDES += "u-boot"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

UBOOT_LOCALVERSION = "-${GITPKGVTAG}"
