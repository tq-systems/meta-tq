SUMMARY = "Linux kernel for TQ-Group Freescale i.MX based modules"

# optional include from meta-freescale
include recipes-kernel/linux/linux-imx.inc

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

FILESEXTRAPATHS_prepend = "${THISDIR}/${PN}-${PV}:"

inherit kernel

DEPENDS += "lzop-native bc-native"
#
# append linux-mainline if we provide mainline kernel versions
#
PROVIDES += "linux-imx linux-tq-imx"

SRCBRANCH = "TQMa8-bringup-rel_imx_4.19.35_1.0.0_ga"
SRCREV ?= "5f02a1e1af11c0f98b59b9bb57296b602a61ff06"

SRC_URI = "\
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://defconfig \
"

# no local defconfig but fragments - defconfig is copied from arm64 defconfig in
# kernel
SRC_URI_mx8 = "\
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://tqma8x-display-support.cfg \
  file://tqma8x-network-support.cfg \
  file://tqma8x-i2c-devices.cfg \
  file://tqma8x-input-devices.cfg \
  file://tqma8x-bpf-support.cfg \
  file://tqma8x-audio-support.cfg \
"

################
#           file://optimize-filesystem-selection.cfg \
#           file://usb-serial-port.cfg \
#           file://gpio-enablement.cfg \
#           file://tqma8qx-regulator-support.cfg \
#           file://enable-led-features.cfg \
####################

COMPATIBLE_MACHINE = "tqma7x"
COMPATIBLE_MACHINE_append = "|tqma6x"
COMPATIBLE_MACHINE_append = "|tqma6ulx"
COMPATIBLE_MACHINE_append = "|tqma6ullx"

COMPATIBLE_MACHINE_append = "|tqma8xx"
COMPATIBLE_MACHINE_append = "|tqma8xxs"
COMPATIBLE_MACHINE_append = "|tqma8mq"
COMPATIBLE_MACHINE_append = "|tqma8qm"

EXTRA_OEMAKE_append_mx8 = " ARCH=arm64"

#####
# copies the defconfig from the kernel tree
# note: fsl kernel has a do_preconfigure step from fsl-kernel-localversion class
#####
addtask copy_defconfig after do_patch before do_configure do_preconfigure
# copy latest defconfig to use for mx8
do_copy_defconfig_mx8 () {
    install -d ${B}
    mkdir -p ${B}
    cp ${S}/arch/arm64/configs/defconfig ${B}/.config
    cp ${S}/arch/arm64/configs/defconfig ${B}/../defconfig
}

# nothing to do for tqma6x / tqma7x / tqma6ulx / tqma6ullx
do_copy_defconfig () {
# nothing to do here
    install -d ${B}
}

S = "${WORKDIR}/git"
