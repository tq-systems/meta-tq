require linux-tq-common.inc

SRC_URI = "${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
           file://defconfig \
           file://disable-non-fsl-architecture.cfg \
           file://disable-non-used-network-devices.cfg \
           file://optimize-filesystem-selection.cfg \
           file://disable-bluetooth.cfg \
           file://tqma8qx-mba8qx/disable-efi.cfg \
           file://gpio-enablement.cfg \
           file://tqma8qx-mba8qx/enable-led-features.cfg \
           file://tqma8qx-mba8qx/tqma8qx-i2c-devices.cfg \
           file://0001-arm64-dt-rewrite-trees-for-TQMa8QX.patch \
           file://0002-arm64-dt-fsl-imx8qxp-tqma8qx-mba8qx-enable-ethernet.patch \
           file://0003-arm64-platforms-select-CLKSRC_MMIO-and-RESET_CONTROL.patch \
           file://0004-net-phy-dp83867-add-support-for-MAC-impedance-config.patch \
           file://0005-net-phy-dp83867-Add-lane-swapping-support-in-the-DP8.patch \
           file://0006-net-phy-dp83867-add-workaround-for-incorrect-RX_CTRL.patch \
           file://0007-net-phy-dp83867-support-led-configuration.patch \
           file://0008-arm64-dt-fsl-imx8qxp-tqma8qx-mba8qx-add-strap-quirk-.patch \
           file://0009-arm64-dt-fsl-imx8qxp-tqma8qx-mba8qx-configure-phy-le.patch \
           file://0010-arm64-dt-fsl-imx8qxp-tqma8qx-add-and-populate-i2c1.patch \
           file://0011-arm64-dt-fsl-imx8qxp-tqma8qx-mba8qx-add-usb-otg-1.patch \
           file://0012-arm64-dt-fsl-imx8qxp-tqma8qx-mba8qx-add-jc42-on-mba8.patch \
           file://0013-arm64-dt-fsl-imx8qxp-tqma8qx-mba8qx-add-gpio-expande.patch \
           "

SRCBRANCH = "TQMa8xx-bringup-imx_4.9.88_imx8qxp_beta2"
SRCREV = "618794a8fc3fd49bfe8b99f3bedea5cc6da6205c"

COMPATIBLE_MACHINE = "tqma8qx-mba8qx|tqma8qxa0-mba8qx"
