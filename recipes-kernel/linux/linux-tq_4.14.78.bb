require linux-tq-common.inc

SRC_URI = "${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
           file://tqma8qx-display-support.cfg \
           file://tqma8x-network-support.cfg \
           "

################
#           file://optimize-filesystem-selection.cfg \
#           file://usb-serial-port.cfg \
#           file://gpio-enablement.cfg \
#           file://tqma8qx-regulator-support.cfg \
#           file://enable-led-features.cfg \
#           file://tqma8qx-i2c-devices.cfg \
#           file://tqma8qx-input-support.cfg \
####################


SRCBRANCH = "TQMa8xx-bringup-rel_imx_4.14.78_1.0.0_ga"
SRCREV = "01070f7d695c4e2b51e243740b5a105143e0b24a"

#####
# copies the defconfig from the kernel tree
# fsl kernel has a do_preconfigure step from fsl-kernel-localversion class
#####
addtask copy_defconfig after do_unpack before do_configure
do_copy_defconfig () {
    install -d ${B}

    # copy latest defconfig to use for mx8
    mkdir -p ${B}
    cp ${S}/arch/arm64/configs/defconfig ${B}/.config
    cp ${S}/arch/arm64/configs/defconfig ${B}/../defconfig
}

COMPATIBLE_MACHINE = "tqma8qx|tqma8qxs"
COMPATIBLE_MACHINE .= "|tqma8mq"

EXTRA_OEMAKE_append_mx8 = " ARCH=arm64"
