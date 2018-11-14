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
           file://0001-arm64-dt-fsl-imx8qxp-tqma8qx-mba8qx-add-usb-otg-1.patch \
           file://0002-arm64-dt-fsl-imx8qxp-tqma8qx-mba8qx-populate-gpio-ex.patch \
           file://0003-DT-Vendor-add-prefix-for-TQ-Systems.patch \
           file://0004-of-Add-vendor-prefix-for-Tianma-Micro-electronics.patch \
           file://0005-drm-panel-simple-Add-support-for-Tianma-TM070JDHG30.patch \
           file://0006-dt-bindings-drm-bridge-Document-sn65dsi86-bridge-bin.patch \
           file://0007-drm-bridge-add-support-for-sn65dsi86-bridge-driver.patch \
           file://0008-drm-bridge-ti-sn65dsi86-backport-to-4.9.y.patch \
           file://0009-drm-bridge-ti-sn65dsi86-Fixup-register-names.patch \
           file://0010-drm-bridge-ti-sn65dsi86-Implement-AUX-channel.patch \
           file://0011-drm-bridge-ti-sn65dsi86-Move-panel_prepare-to-pre_en.patch \
           file://0012-drm-bridge-ti-sn65dsi86-Poll-for-DP-PLL-Lock.patch \
           file://0013-drm-bridge-ti-sn65dsi86-Poll-for-training-complete.patch \
           file://0014-drm-bridge-ti-sn65dsi86-Fix-0-day-build-error.patch \
           file://0015-arm64-dt-fsl-imx8qxp-tqma8qx-mba8qx-add-12V-baseboar.patch \
           file://0016-arm64-dt-fsl-imx8qxp-tqma8qx-mba8qx-add-device-tree-.patch \
           file://0017-arm64-dt-fsl-imx8qxp-tqma8qx-mba8qx-enable-gpu-prg-a.patch \
           file://0018-arm64-dt-fsl-imx8qxp-tqma8qx-mba8qx-fix-console-uart.patch \
           file://0019-arm64-dt-fsl-imx8qxp-tqma8qx-mba8qx-cosmetic-add-sta.patch \
           file://0020-arm64-dt-fsl-imx8qxp-tqma8qx-mba8qx-fix-led-gpio-pol.patch \
           file://0021-arm64-dt-fsl-imx8qxp-tqma8qx-mba8qx-add-can.patch \
           file://0022-arm64-dt-fsl-imx8qxp-tqma8qx-mba8qx-add-hog-pins-for.patch \
           "

SRCBRANCH = "TQMa8xx-bringup-imx_4.9.88_imx8qxp_beta2"
SRCREV = "ea63398e3571b41f651122a202cf9733514c1bc8"

COMPATIBLE_MACHINE = "tqma8qx-mba8qx|tqma8qxa0-mba8qx|tqma8qxs-mb-smarc-2|tqma8qxsa0-mb-smarc-2"
