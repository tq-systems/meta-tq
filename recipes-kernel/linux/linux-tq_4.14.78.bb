require linux-tq-common.inc

SRC_URI = "${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
           file://tqma8qx-display-support.cfg \
           file://tqma8x-network-support.cfg \
           file://0001-arm64-dt-fsl-imx8qxp-tqma8qx-drop-a0-stepping-suppor.patch \
           file://0002-arm64-dt-fsl-imx8qxp-tqma8qx-mba8qx-split-out-common.patch \
           file://0003-arm64-dt-prepare-fsl-imx8dx-p-tqma8dx-p-mba8xx.patch \
           file://0004-arm64-dt-fsl-imx8qxp-tqma8qx-mba8qx-switch-to-use-co.patch \
           file://0005-arm64-dt-fsl-imx8qxp-tqma8qx-mba8qx-switch-to-use-co.patch \
           file://0006-arm64-dt-fsl-imx8qxp-tqma8qx-mba8qx-switch-to-use-co.patch \
           file://0007-arm64-dt-fsl-imx8qxp-tqma8qx-mba8qx-switch-to-use-co.patch \
           file://0008-arm64-dt-fsl-imx8dx-p-tqma8dx-p-mba8xx-add-lvds-vari.patch \
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
SRCREV = "651575fdcdb4aa9e587e34167da256e11435494b"

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
