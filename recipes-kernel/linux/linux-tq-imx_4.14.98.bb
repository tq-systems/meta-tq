require linux-tq-imx-common.inc

SRC_URI = "\
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://defconfig \
"

SRCBRANCH = "TQMaxx-imx_4.14.98_2.2.0"
SRCREV = "d07fa4a7e29f3ca4c454f83685efd6cb2791588f"

KERNEL_TREE_DEFCONFIG_mx8 = "defconfig"

SRC_URI_mx8 = "\
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
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
