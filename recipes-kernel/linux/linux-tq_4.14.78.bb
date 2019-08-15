require linux-tq-common.inc

SRC_URI = "${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
           file://tqma8x-display-support.cfg \
           file://tqma8x-network-support.cfg \
           file://tqma8x-i2c-devices.cfg \
           file://tqma8x-input-devices.cfg \
           file://tqma8x-bpf-support.cfg \
           file://tqma8x-audio-support.cfg \
           file://0001-arm64-dt-fsl-imx8mq-tqma8mq-mba8mx-prevent-a-lot-of-.patch \
           file://0002-arm64-dt-fsl-imx8mq-tqma8mq-add-regulator-for-CPU-ov.patch \
           file://0003-arm64-dt-fsl-imx8mq-tqma8mq-add-cpu-dvfs.patch \
           file://0004-arm64-dt-fsl-imx8mq-tqma8mq-add-settling-delay-to-dv.patch \
           file://0005-arm64-dt-fsl-imx8mq-tqma8mq-dvfs-regulator-should-be.patch \
           file://0006-arm64-dt-fsl-imx8mq-tqma8mq-add-overdrive-regulator.patch \
           file://0007-overdrive-split-dt-for-test.patch \
           "

################
#           file://optimize-filesystem-selection.cfg \
#           file://usb-serial-port.cfg \
#           file://gpio-enablement.cfg \
#           file://tqma8qx-regulator-support.cfg \
#           file://enable-led-features.cfg \
####################


SRCBRANCH = "TQMa8xx-bringup-rel_imx_4.14.78_1.0.0_ga"
SRCREV = "3b5696da06759745c9791d40c11d7a1479bfef75"

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
