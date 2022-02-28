SUMMARY = "Linux for TQ-Systems GmbH Freescale / NXP i.MX7 and LS102xA/LS104xA based modules"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

require linux-tq-common.inc

KBRANCH = "TQMaxx-linux-v5.4.y"
SRCREV = "5f33bc464d83b11e15fcdf7f507ee83578257bc9"

# LINUX_VERSION must match version from Makefile
LINUX_RELEASE = "5.4"
LINUX_VERSION = "${LINUX_RELEASE}.87"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

SRC_URI = "\
    ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${KBRANCH} \
    file://defconfig \
"

SRC_URI_append_tqmls102xa = "\
    file://tqmls102x-enable-edac.cfg \
"

COMPATIBLE_MACHINE = "tqmls102xa"
COMPATIBLE_MACHINE_append = "|tqma7x"
COMPATIBLE_MACHINE_append = "|tqmls10xxa"
