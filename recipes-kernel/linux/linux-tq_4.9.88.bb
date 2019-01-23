require linux-tq-common.inc

SRC_URI = "${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
           file://defconfig \
           file://disable-non-fsl-architecture.cfg \
           file://disable-non-used-network-devices.cfg \
           file://optimize-filesystem-selection.cfg \
           file://disable-bluetooth.cfg \
           file://disable-efi.cfg \
           file://usb-serial-port.cfg \
           file://gpio-enablement.cfg \
           file://tqma8qx-regulator-support.cfg \
           file://enable-led-features.cfg \
           file://tqma8qx-i2c-devices.cfg \
           file://tqma8qx-input-support.cfg \
           file://tqma8qx-display-support.cfg \
           "

SRCBRANCH = "TQMa8xx-bringup-imx_4.9.88_imx8qxp_beta2"
SRCREV = "ae872be1823271f04aa68dfb158084fff969e79c"

COMPATIBLE_MACHINE = "tqma8qx-mba8qx|tqma8qxs-mb-smarc-2"
COMPATIBLE_MACHINE .= "|tqma8mq-mba8mx"
