require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "u-boot for TQ-Group Freescale i.MX based modules"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

PROVIDES = "u-boot"

SRCREV = "9a69dae7c0e3baebea1eba671aaa67602e5861c8"
SRCBRANCH = "TQMaxx2-v2016.03-rel_imx_4.1.15_2.0.0_ga"

SRC_URI = "git://github.com/tq-systems/u-boot-tqmaxx.git;protocol=https;branch=${SRCBRANCH}"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "(tqma7-mba7)"
