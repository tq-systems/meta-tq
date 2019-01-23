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
           file://disable-vivante-galcore.cfg \
           file://0001-arm64-dt-add-support-for-tqma8mq.patch \
           file://0002-arm64-dt-fsl-imx8mq-tqma8mq-add-i2c-eeproms.patch \
           file://0003-arm64-dt-fsl-imx8mq-tqma8mq-mba8mx-add-i2c-port-expa.patch \
           file://0004-arm-dt-tqma8mq-mba8mx-add-ethernet-support.patch \
           file://0005-arm64-dt-fsl-imx8mq-tqma8mq-add-regulators-for-e-MMC.patch \
           file://0006-arm64-dt-fsl-imx8mq-tqma8mq-mba8mx-regulators-dont-n.patch \
           file://0007-arm64-dt-fsl-imx8mq-tqma8mq-mba8mx-fixup-SD-Card.patch \
           file://0009-arm64-dt-fsl-imx8mq-tqma8mq-mba8mx-disable-SD-CARD-V.patch \
           file://0010-arm64-dt-fsl-imx8mq-tqma8mq-restrict-reserved-mem-fo.patch \
           file://0011-arm64-dt-fsl-imx8mq-tqma8mq-disable-e-MMC-HS-modi.patch \
           file://0011-arm64-dt-fsl-imx8mq-tqma8mq-enable-gpu-including-pow.patch \
           file://0012-arm64-dt-fsl-imx8mq-tqma8mq-enable-vpu-including-pow.patch \
           file://0013-arm64-dt-fsl-imx8mq-tqma8mq-mba8mx-add-hdmi-support.patch \
           "

SRCBRANCH = "TQMa8xx-bringup-imx_4.9.88_imx8qxp_beta2"
SRCREV = "ae872be1823271f04aa68dfb158084fff969e79c"

COMPATIBLE_MACHINE = "tqma8qx-mba8qx|tqma8qxs-mb-smarc-2"
COMPATIBLE_MACHINE .= "|tqma8mq-mba8mx"
