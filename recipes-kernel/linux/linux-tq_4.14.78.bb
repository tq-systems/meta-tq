require linux-tq-common.inc

SRC_URI = "${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
           file://tqma8x-display-support.cfg \
           file://tqma8x-network-support.cfg \
           file://tqma8x-i2c-devices.cfg \
           file://tqma8x-input-devices.cfg \
           file://0001-arm64-dt-add-dt-variant-for-TQMa8Xx-prototypes-witho.patch \
           file://0002-arm64-dt-tqma8xxs-cosmetic-use-tqma8xxs-in-iomux-gro.patch \
           file://0003-arm64-dt-tqma8xx-cosmetic-use-tqma8xx-in-iomux-group.patch \
           file://0004-arm64-dt-mba8xx-common-fix-compatible-for-i2c-temp-s.patch \
           file://0005-arm64-dt-tqma8xxs-common-fix-i2c-eeprom-address.patch \
           file://0006-arm64-dt-mb-smarc-2-common-add-eeprom-24c32.patch \
           file://0007-arm64-dt-add-support-for-fsl-imx8qxp-tqma8xqp-mbpa8x.patch \
           "

################
#           file://optimize-filesystem-selection.cfg \
#           file://usb-serial-port.cfg \
#           file://gpio-enablement.cfg \
#           file://tqma8qx-regulator-support.cfg \
#           file://enable-led-features.cfg \
####################


SRCBRANCH = "TQMa8xx-bringup-rel_imx_4.14.78_1.0.0_ga"
SRCREV = "7c468d8ea83d5a106904c98af5414236aa40d847"

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
