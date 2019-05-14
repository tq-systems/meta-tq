require linux-tq-common.inc

SRC_URI = "${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
           file://tqma8x-display-support.cfg \
           file://tqma8x-network-support.cfg \
           file://tqma8x-i2c-devices.cfg \
           file://tqma8x-input-devices.cfg \
           file://tqma8x-bpf-support.cfg \
           file://0001-arm64-dt-add-dt-variant-for-TQMa8Xx-prototypes-witho.patch \
           file://0002-arm64-dt-tqma8xxs-cosmetic-use-tqma8xxs-in-iomux-gro.patch \
           file://0003-arm64-dt-tqma8xx-cosmetic-use-tqma8xx-in-iomux-group.patch \
           file://0004-arm64-dt-mba8xx-common-fix-compatible-for-i2c-temp-s.patch \
           file://0005-arm64-dt-tqma8xx-common-cosmetic-remove-superflous-p.patch \
           file://0006-arm64-dt-tqma8xxs-common-fix-i2c-eeprom-address.patch \
           file://0007-arm64-dt-mb-smarc-2-common-add-eeprom-24c32.patch \
           file://0008-arm64-dt-add-support-for-fsl-imx8qxp-tqma8xqp-mbpa8x.patch \
           file://0009-arm64-dt-fsl-imx8qxp-tqma8xqp-mbpa8xx-add-led-suppor.patch \
           file://0010-arm64-dt-mbpa8xx-common-add-temp-sensor-support.patch \
           file://0011-arm64-dt-mbpa8xx-common-add-revision-dt-gpio-as-hog-.patch \
           file://0012-arm64-dt-mbpa8xx-common-add-CAN0.patch \
           file://0013-arm64-dt-mbpa8xx-common-add-UART0-2-with-RX-TX.patch \
           file://0014-arm64-dt-mbpa8xx-common-add-reset-GPIOs-as-hog-pins.patch \
           file://0015-arm64-dt-mbpa8xx-common-SD-Card-gpio-fixes.patch \
           file://0016-arm64-dt-mbpa8xx-common-add-UART3.patch \
           file://0017-arm64-dt-mbpa8xx-common-fix-ethernet-PHY-leds.patch \
           file://0018-arm64-dt-mbpa8xx-common-enable-USB-host-support.patch \
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
