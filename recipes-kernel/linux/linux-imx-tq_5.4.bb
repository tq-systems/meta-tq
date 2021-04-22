require linux-imx-tq-common.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"


SRC_URI = "\
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://defconfig \
"

SRCBRANCH = "TQMa8-rel_imx_5.4.70_2.3.0"
SRCREV = "cafddf9a58df68883e9f8ceccf5e5dd9079db670"

KERNEL_TREE_DEFCONFIG_mx8 = "imx_v8_defconfig"

SRC_URI_mx8 = "\
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://local-version.cfg \
  file://tqma8x-display-support.cfg \
  file://tqma8x-network-support.cfg \
  file://tqma8x-i2c-devices.cfg \
  file://tqma8x-input-devices.cfg \
  file://tqma8x-bpf-support.cfg \
  file://tqma8x-audio-support.cfg \
  file://tqma8x-usb-special-drivers.cfg \
  file://tqma8x-adc-hwmon-support.cfg \
  file://tqma8x-rtc.cfg \
  file://tqma8x-nonimx-drm-removal.cfg \
  file://tqma8x-wifi-support.cfg \
"

################
#           file://optimize-filesystem-selection.cfg \
#           file://usb-serial-port.cfg \
#           file://gpio-enablement.cfg \
#           file://tqma8qx-regulator-support.cfg \
#           file://enable-led-features.cfg \
####################

COMPATIBLE_MACHINE_append = "tqma8xx"
COMPATIBLE_MACHINE_append = "|tqma8xxs"
COMPATIBLE_MACHINE_append = "|tqma8mpxl"
COMPATIBLE_MACHINE_append = "|tqma8mq"
COMPATIBLE_MACHINE_append = "|tqma8mxml"
COMPATIBLE_MACHINE_append = "|tqma8mxnl"
COMPATIBLE_MACHINE_append = "|tqma8qm"

EXTRA_OEMAKE_append_mx8 = " ARCH=arm64"

#####
# copies the defconfig from the kernel tree
# fsl kernel has a do_preconfigure step from fsl-kernel-localversion class
#####
addtask copy_defconfig after do_unpack before do_configure
do_copy_defconfig_mx8 () {
    install -d ${B}

    # copy latest defconfig to use for mx8
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
