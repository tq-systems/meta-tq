require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "u-boot for TQ-Group Freescale LS1012A based modules"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

PROVIDES = "u-boot"

SRCBRANCH = "TQMLS1012AL-bringup-v2017.11-LSDK-18.03"
SRCREV = "52ca4e278a690ea176311fc0245b8785d51eacec"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "tqmls1012al-mbls1012al"
