SUMMARY = "Linux for TQ-Systems GmbH Freescale / LS102xA/LS104xA based modules"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

require linux-tq-common.inc

KBRANCH = "TQMaxx-linux-v5.4.y"
SRCREV = "b2ccdeb1ae0c70f26a2a8db15210056856c84557"

# LINUX_VERSION must match version from Makefile
LINUX_RELEASE = "5.4"
LINUX_VERSION = "${LINUX_RELEASE}.87"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

SRC_URI = "\
    ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${KBRANCH} \
    file://defconfig \
    file://0001-ARM-8933-1-replace-Sun-Solaris-style-flag-on-section.patch \
    file://0002-ata-ahci-Disable-SXS-for-Hisilicon-Kunpeng920.patch \
    file://0003-ata-ahci-Match-EM_MAX_SLOTS-with-SATA_PMP_MAX_PORTS.patch \
    file://0004-ata-ahci-fix-enum-constants-for-gcc-13.patch \
"

SRC_URI:append:tqmls102xa = "\
    file://tqmls102x-enable-edac.cfg \
"

COMPATIBLE_MACHINE = "tqmls102xa"
COMPATIBLE_MACHINE:append = "|tqmls10xxa"
