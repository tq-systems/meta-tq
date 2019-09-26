SUMMARY = "Linux kernel for TQ-Group Freescale i.MX based modules"

# optional include from meta-freescale
include recipes-kernel/linux/linux-imx.inc

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

inherit kernel

DEPENDS += "lzop-native bc-native"
#
# append linux-mainline if we provide mainline kernel versions
#
PROVIDES += "linux-imx linux-tq-imx"

SRC_URI = "\
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://defconfig \
"

SRCBRANCH = "TQMa8-bringup-rel_imx_4.19.35_1.0.0_ga"
SRCREV ?= "5f02a1e1af11c0f98b59b9bb57296b602a61ff06"

COMPATIBLE_MACHINE = "tqma7x"
COMPATIBLE_MACHINE_append = "|tqma6x"
COMPATIBLE_MACHINE_append = "|tqma6ulx"
COMPATIBLE_MACHINE_append = "|tqma6ullx"

S = "${WORKDIR}/git"
