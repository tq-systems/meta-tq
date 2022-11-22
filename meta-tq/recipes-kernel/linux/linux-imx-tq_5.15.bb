SUMMARY = "Linux kernel based on linux-imx for TQ-Systems GmbH i.MX / LS based modules"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require linux-imx-tq-common.inc

KBRANCH = "TQM-lf-5.15"
SRCREV = "e27a78db286db0e09cf6b2981ad931de087ab54d"
KBRANCH:imx-nxp-bsp = "TQMa8-fslc-5.15-2.0.x-imx"
SRCREV:imx-nxp-bsp = "89a67700a041f5c2066d0964564626d6d81ad5db"

# LINUX_VERSION must match version from Makefile
LINUX_RELEASE = "5.15"
LINUX_VERSION = "${LINUX_RELEASE}.5"
LINUX_VERSION:imx-nxp-bsp = "${LINUX_RELEASE}.60"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

SRC_URI = "\
    ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${KBRANCH} \
    file://defconfig \
"

DEFAULT_PREFERENCE = "0"

KBUILD_DEFCONFIG:mx6-nxp-bsp = "imx_v7_defconfig"
KBUILD_DEFCONFIG:mx8-nxp-bsp = "imx_v8_defconfig"

SRC_URI:mx6-nxp-bsp = "\
    ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${KBRANCH} \
    file://disable-highpte.cfg \
    file://dynamic-debug.cfg \
    file://enable-led-features.cfg \
    file://general-optimizations.cfg \
    file://gpio-enablement.cfg \
    file://kallsyms.cfg \
    file://local-version.cfg \
    file://neon.cfg \
    file://optimize-filesystem-selection.cfg \
    file://pcie.cfg \
    file://sdma.cfg \
    file://tqma6-audio-support.cfg \
    file://tqma6-bpf-support.cfg \
    file://tqma6-disable-unused-devices.cfg \
    file://tqma6-i2c-devices.cfg \
    file://tqma6-input-devices.cfg \
    file://tqma6-network-support.cfg \
    file://tqma6-nonimx-drm-removal.cfg \
    file://tqma6-regulator-support.cfg \
    file://tqma6-rtc.cfg \
    file://tqma6-spi.cfg \
    file://tqma6-wifi-support.cfg \
    file://tqma6x-display-support.cfg \
    file://usb-serial-port.cfg \
"

SRC_URI:mx8-nxp-bsp = "\
    ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${KBRANCH} \
    file://local-version.cfg \
    file://tqma8-display-support.cfg \
    file://tqma8-network-support.cfg \
    file://tq-generic-devices.cfg \
    file://acpi-removal.cfg \
    file://sdma.cfg \
    file://tqma8-input-devices.cfg \
    file://bpf-support.cfg \
    file://tqma8-audio-support.cfg \
    file://tqma8-usb-special-drivers.cfg \
    file://tqma8-adc-hwmon-support.cfg \
    file://tqma8-rtc.cfg \
    file://tqma8-nonimx-drm-removal.cfg \
    file://tqma8-wifi-support.cfg \
    file://tqma8-v4l2.cfg \
    file://features/devicetree.cfg \
    file://usb-gadget-support.cfg \
"

SRC_URI:append = " \
    ${@bb.utils.contains('COMBINED_FEATURES', 'bluetooth', 'file://features/bluetooth-support.cfg', '', d)} \
"
SRC_URI:append = " \
    ${@bb.utils.contains('IMAGE_FEATURES', 'debug-tweaks', 'file://features/debug.cfg', '', d)} \
"

COMPATIBLE_MACHINE = "^("
COMPATIBLE_MACHINE .= "|tqma6x"
COMPATIBLE_MACHINE .= "|tqma8mpxl"
COMPATIBLE_MACHINE .= "|tqma8mq"
COMPATIBLE_MACHINE .= "|tqma8mxml"
COMPATIBLE_MACHINE .= "|tqma8mxnl"
COMPATIBLE_MACHINE .= "|tqma8x"
COMPATIBLE_MACHINE .= "|tqma8xx"
COMPATIBLE_MACHINE .= "|tqma8xxs"
COMPATIBLE_MACHINE .= "|tqmls1012al"
COMPATIBLE_MACHINE .= "|tqmls1028a"
COMPATIBLE_MACHINE .= ")$"
