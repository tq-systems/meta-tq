SUMMARY = "Linux with PREEMPT_RT support for TQ-Systems GmbH Freescale / NXP LS102xA based modules"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

DEPENDS += "lzop-native bc-native openssl-native"

PROVIDES += "linux-rt linux-tq"

inherit kernel

SRC_URI = "\
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://defconfig \
  file://0001-ARM-8933-1-replace-Sun-Solaris-style-flag-on-section.patch \
  file://0002-ata-ahci-Disable-SXS-for-Hisilicon-Kunpeng920.patch \
  file://0003-ata-ahci-Match-EM_MAX_SLOTS-with-SATA_PMP_MAX_PORTS.patch \
  file://0004-ata-ahci-fix-enum-constants-for-gcc-13.patch \
"

SRCBRANCH = "TQMaxx-linux-v5.4.y-rt"
SRCREV = "828cb3af0a0e565cd986bc0bfc788a29e5591d83"

COMPATIBLE_MACHINE = "tqmls102xa"

S = "${WORKDIR}/git"
