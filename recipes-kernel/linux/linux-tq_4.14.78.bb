require linux-tq-common.inc

SRC_URI = "${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
           file://tqma8x-display-support.cfg \
           file://tqma8x-network-support.cfg \
           file://tqma8x-i2c-devices.cfg \
           file://tqma8x-input-devices.cfg \
           file://0001-arm64-dt-fsl-imx8-dx-dxp-qxp-tqma8-xd-xdp-xqp-mba8xx.patch \
           file://0002-arm64-dt-mba8xx-common.dtsi-fix-pcie-GPIO.patch \
           file://0003-arm64-dt-mb-smarc-2-common-regulators-dont-need-simp.patch \
           file://0004-arm64-dt-mb-smarc-2-common-add-more-regulators.patch \
           file://0005-arm64-dt-fsl-imx8-dx-dxp-qxp-tqma8-xd-xdp-xqp-mba8xx.patch \
           file://0006-arm64-dt-rename-tqma8qxps-tqma8xqps.patch \
           "

################
#           file://optimize-filesystem-selection.cfg \
#           file://usb-serial-port.cfg \
#           file://gpio-enablement.cfg \
#           file://tqma8qx-regulator-support.cfg \
#           file://enable-led-features.cfg \
####################


SRCBRANCH = "TQMa8xx-bringup-rel_imx_4.14.78_1.0.0_ga"
SRCREV = "2e908d22ddb10584581c2fd68f934897b6336e66"

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

COMPATIBLE_MACHINE = "tqma8xx"
COMPATIBLE_MACHINE_append = "|tqma8xxs"
COMPATIBLE_MACHINE_append = "|tqma8mq"

EXTRA_OEMAKE_append_mx8 = " ARCH=arm64"
