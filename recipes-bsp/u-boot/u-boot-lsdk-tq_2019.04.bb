require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "u-boot for TQ-Group LX2160A based modules"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

DEPENDS_append = " bison-native"

PROVIDES += "u-boot"

SRCREV = "74382e0e5b140d3808ae3b5bc23fc497d02998f5"
SRCBRANCH = "TQMLX2160A-v2019.04-lx2160a-early-access-bsp0.7"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SCMVERSION ??= "y"
LOCALVERSION ??= "+tq"

UBOOT_LOCALVERSION = "${LOCALVERSION}"

do_compile_prepend() {
    if [ "${SCMVERSION}" = "y" ]; then
        # Add GIT revision to the local version
        head=`cd ${S} ; git describe --tags --always 2> /dev/null`
        printf "%s%s" "+" $head > ${S}/.scmversion
        printf "%s%s" "+" $head > ${B}/.scmversion
    else
        printf "%s" "${UBOOT_LOCALVERSION}" > ${S}/.scmversion
        printf "%s" "${UBOOT_LOCALVERSION}" > ${B}/.scmversion
    fi
}

COMPATIBLE_MACHINE = "tqmlx2160a"
