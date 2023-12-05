# SPDX-License-Identifier: MIT
#
# Copyright (c) 2020-2023 TQ-Systems GmbH <oss@ew.tq-group.com>,
# D-82229 Seefeld, Germany.

inherit gitpkgv-nonexact
inherit pkgconfig

SECTION = "bootloaders"

PROVIDES += "u-boot"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM ?= "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

do_configure[cleandirs] = "${B}"

#
# We provide a default fw_env.config for linux userspace access to U-Boot
# environment. Since this is (nearly) version agnostic, the config templates
# are located in the files folder for now. If a machine provides it's own version,
# the SOM override or the machine name can be used as subfolder to place the
# config file.
#
SRC_URI += "file://fw_env.config"

# use "tq" as default value - poky default assignment is empty
UBOOT_LOCALVERSION = "-tq"

SCMVERSION ?= "y"

###
# default compile task for u-boot from oe / poky generates the .scmversion file
# only if it is not present in ${B} and ${S}. If we create the files before, they
# won't be touched. Let's bring in a consistent version to the bootloader image
# consisting of Version from Makefile, 'tq' and the git short hash leading to
# <U-Boot vers>-tq+g<short hash>+p<#patches>
# TODO: we should verify, if we can use a localversion-tq file later on to minimize
# interdependencies to oe / poky and meta-freescale
###
do_compile:prepend() {
    if [ "${SCMVERSION}" = "y" ]; then
        # Add GIT revision to the local version
        head=`cd ${S} ; git rev-parse --verify --short ${SRCREV} 2> /dev/null`
        patches=`cd ${S} ; git rev-list --count ${SRCREV}..HEAD 2> /dev/null`
        printf "%s%s%s%s%s" "${UBOOT_LOCALVERSION}" +g $head +p $patches > ${S}/.scmversion
        printf "%s%s%s%s%s" "${UBOOT_LOCALVERSION}" +g $head +p $patches > ${B}/.scmversion
    else
        printf "%s" "${UBOOT_LOCALVERSION}" > ${S}/.scmversion
        printf "%s" "${UBOOT_LOCALVERSION}" > ${B}/.scmversion
    fi
}
