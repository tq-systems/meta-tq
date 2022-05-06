require recipes-bsp/u-boot/u-boot.inc
inherit gitpkgv-nonexact

PROVIDES += "u-boot"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

# use "tq" as default value - poky default assignment is empty
UBOOT_LOCALVERSION = "-tq"

SCMVERSION ?= "y"

###
# default compile task for u-boot from oe / poky generates the .scmversion file
# only if it is not present in ${B} and ${S}. If we create the files before, they
# won't be touched. Let's bring in a consistent version to the bootloader image
# consisting of Version from Makefile, 'tq' and the git short hash leading to
# <U-Boot vers>-tq+g<short hash>
# TODO: we should verify, if we can use a localversion-tq file later on to minimize
# interdependencies to oe / poky and meta-freescale
###
do_compile:prepend() {
    head=""
    suffix=""

    if [ "${SCMVERSION}" = "y" ]; then
        # Add GIT revision to the local version
        head=`cd ${S} ; git rev-parse --short HEAD 2> /dev/null`
        suffix="-g"
    fi

    printf "%s%s%s" "${UBOOT_LOCALVERSION}" ${suffix} ${head} > ${S}/.scmversion
    printf "%s%s%s" "${UBOOT_LOCALVERSION}" ${suffix} ${head} > ${B}/.scmversion
}
