SUMMARY = "Linux kernel based on linux-fslc for TQ-Group i.MX / LS based modules"

require linux-imx-tq-common.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

SRC_URI = "\
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://defconfig \
"

SRCBRANCH = "TQMa8-fslc-5.10-2.1.x-imx"
SRCREV = "4defdc745f06555e138b0b8b3c397ecd859fc009"

KERNEL_TREE_DEFCONFIG_mx8 = "imx_v8_defconfig"

SRC_URI_mx8 = "\
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://local-version.cfg \
  file://tqma8x-display-support.cfg \
  file://tqma8x-network-support.cfg \
  file://tqma8x-i2c-devices.cfg \
  file://acpi-removal.cfg \
  file://sdma.cfg \
  file://tqma8x-input-devices.cfg \
  file://tqma8x-bpf-support.cfg \
  file://tqma8x-audio-support.cfg \
  file://tqma8x-usb-special-drivers.cfg \
  file://tqma8x-adc-hwmon-support.cfg \
  file://tqma8x-rtc.cfg \
  file://tqma8x-nonimx-drm-removal.cfg \
  file://tqma8x-wifi-support.cfg \
  file://tqma8x-v4l2.cfg \
"

COMPATIBLE_MACHINE = "^("
# COMPATIBLE_MACHINE .= "tqma7x"
# COMPATIBLE_MACHINE .= "|tqma6x"
# COMPATIBLE_MACHINE .= "|tqma6ulx"
# COMPATIBLE_MACHINE .= "|tqma6ullx"
# COMPATIBLE_MACHINE .= "|tqmls1012al"
# COMPATIBLE_MACHINE .= "|tqmls1028a"
# COMPATIBLE_MACHINE .= "|tqma8x"
# COMPATIBLE_MACHINE .= "|tqma8xx"
# COMPATIBLE_MACHINE .= "|tqma8xxs"
# COMPATIBLE_MACHINE .= "|tqma8mpxl"
COMPATIBLE_MACHINE .= "|tqma8mq"
COMPATIBLE_MACHINE .= "|tqma8mxml"
COMPATIBLE_MACHINE .= "|tqma8mxnl"
# COMPATIBLE_MACHINE .= "|tqma8qm"
COMPATIBLE_MACHINE .= ")$"

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
