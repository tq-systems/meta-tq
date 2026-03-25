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

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

DEPENDS += "bison-native"

B = "${UNPACKDIR}/build"

do_configure[cleandirs] = "${B}"

#
# We provide a default fw_env.config for linux userspace access to U-Boot
# environment. Since this is (nearly) version agnostic, the config templates
# are located in the files folder for now. If a machine provides it's own version,
# the SOM override or the machine name can be used as subfolder to place the
# config file.
#
# Machines can also opt to install multiple configs under alternative names by setting
# the TQ_FW_ENV_CONFIGS variable. An example script for selecting the correct version
# automatically on boot in provided in meta-dumpling.
#
TQ_FW_ENV_CONFIGS ?= ""
TQ_FW_ENV_CONFIG ?= "${@ \
    'file://fw_env.config' \
        if d.getVar('TQ_FW_ENV_CONFIGS') == '' else \
    ' '.join([ f'file://fw_env.config-{config}' for config in d.getVar('TQ_FW_ENV_CONFIGS').split()]) \
}"
TQ_FW_ENV_CONFIG[vardeps] += "TQ_FW_ENV_CONFIGS"

SRC_URI += "${TQ_FW_ENV_CONFIG}"

# use "tq" as default value - poky default assignment is empty
UBOOT_LOCALVERSION = "-tq"

SCMVERSION ?= "y"

###
# default compile task for u-boot from oe / poky generates the .scmversion file
# only if it is not present in ${B} and ${S}. If we create the files before, they
# won't be touched. Let's bring in a consistent version to the bootloader image
# consisting of Version from Makefile, 'tq' and the git short hash leading to
# <U-Boot vers>-tq+g<short hash>+p<#patches>
# This needs 'git' as patchtool - default is quilt
# TODO: we should verify, if we can use a localversion-tq file later on to minimize
# interdependencies to oe / poky and meta-freescale
###

PATCHTOOL = "git"

do_compile:prepend() {
    if [ "${SCMVERSION}" = "y" ]; then
        # Add GIT revision to the local version
        if [ "${SRCREV}" = "INVALID" ]; then
                hash=${SRCREV_machine}
        else
                hash=${SRCREV}
        fi
        if [ "$hash" = "AUTOINC" ]; then
                branch=`git --git-dir=${S}/.git  symbolic-ref --short -q HEAD`
                head=`git --git-dir=${S}/.git rev-parse --verify --short origin/${branch} 2> /dev/null`
        else
                head=`git --git-dir=${S}/.git rev-parse --verify --short $hash 2> /dev/null`
        fi
        patches=`git --git-dir=${S}/.git rev-list --count $head..HEAD 2> /dev/null`
        printf "%s%s%s%s" +g $head +p $patches > ${S}/.scmversion
        printf "%s%s%s%s" +g $head +p $patches > ${B}/.scmversion
    else
        printf "%s" "${UBOOT_LOCALVERSION}" > ${S}/.scmversion
        printf "%s" "${UBOOT_LOCALVERSION}" > ${B}/.scmversion
    fi
}

do_install:append() {
    for config in ${TQ_FW_ENV_CONFIGS}; do
        install -m 644 "${UNPACKDIR}/fw_env.config-${config}" "${D}${sysconfdir}/fw_env.config-${config}"
    done

    i=0
    for config in ${UBOOT_MACHINE}; do
        i=$(expr $i + 1)
        j=0
        for type in ${UBOOT_CONFIG}; do
            j=$(expr $j + 1)
            if [ $j -eq $i ]; then
                (cd "${B}/${config}-${type}" && uboot_tq_install_config "${type}")
            fi
        done
    done
    unset i j
}

uboot_tq_install_config() {
    type="$1"

    if [ -e "${UBOOT_DTB_BINARY}" ]; then
        install -D -m 644 "${UBOOT_DTB_BINARY}" "${D}/boot/${UBOOT_DTB_BINARY}-${type}"
    fi
    if [ -e "${UBOOT_DTB_SIGNED}" ]; then
        install -D -m 644 "${UBOOT_DTB_SIGNED}" "${D}/boot/${UBOOT_DTB_SIGNED}-${type}"
    fi
    if [ -e "${UBOOT_NODTB_BINARY}" ]; then
        install -m 0644 "${UBOOT_NODTB_BINARY}" "${D}/boot/${UBOOT_NODTB_BINARY}-${type}"
    fi
}

FILES:${PN}-env += "${sysconfdir}/fw_env.config-*"
