# Copyright (C) 2021 - 2022 TQ-Systems GmbH

SUMMARY = "Linux kernel based on linux-imx for TQ-Systems GmbH i.MX / LS based modules"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require linux-imx-tq-common.inc

KBRANCH = "TQM-lf-5.15"
SRCREV = "e27a78db286db0e09cf6b2981ad931de087ab54d"
KBRANCH:imx-nxp-bsp = "TQMa8-fslc-5.15-2.0.x-imx"
SRCREV:imx-nxp-bsp = "89a67700a041f5c2066d0964564626d6d81ad5db"
KBRANCH:mx93-nxp-bsp = "TQMaxx-lf-5.15.y"
SRCREV:mx93-nxp-bsp = "39a3f2dbf4a0af0c14bc772d8f2681d8cd8114c5"

# LINUX_VERSION must match version from Makefile
LINUX_RELEASE = "5.15"
LINUX_VERSION = "${LINUX_RELEASE}.5"
LINUX_VERSION:imx-nxp-bsp = "${LINUX_RELEASE}.60"
LINUX_VERSION:mx93-nxp-bsp = "${LINUX_RELEASE}.52"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"
FILESEXTRAPATHS:prepend:aarch64:imx-nxp-bsp := "${THISDIR}/${PN}-${LINUX_RELEASE}/imx-arm64:"

SRC_URI = "\
    ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${KBRANCH} \
    file://defconfig \
"

DEFAULT_PREFERENCE = "0"

KBUILD_DEFCONFIG:mx6-nxp-bsp = "imx_v7_defconfig"
KBUILD_DEFCONFIG:aarch64:imx-nxp-bsp = "imx_v8_defconfig"

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

SRC_URI:aarch64:imx-nxp-bsp = "\
    ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${KBRANCH} \
    file://local-version.cfg \
    file://display-support.cfg \
    file://network-support.cfg \
    file://tq-generic-devices.cfg \
    file://acpi-removal.cfg \
    file://sdma.cfg \
    file://input-devices.cfg \
    file://bpf-support.cfg \
    file://audio-support.cfg \
    file://usb-special-drivers.cfg \
    file://adc-hwmon-support.cfg \
    file://rtc.cfg \
    file://nonimx-drm-removal.cfg \
    file://wifi-support.cfg \
    file://v4l2.cfg \
    file://features/devicetree.cfg \
    file://usb-gadget-support.cfg \
"

SRC_URI:append:mx93-nxp-bsp = "\
    file://0001-drm-bridge-it6161-add-missing-gpio-consumer-header.patch \
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
COMPATIBLE_MACHINE .= "|tqma93xx"
COMPATIBLE_MACHINE .= "|tqmls1012al"
COMPATIBLE_MACHINE .= "|tqmls1028a"
COMPATIBLE_MACHINE .= "|mx93-nxp-bsp"
COMPATIBLE_MACHINE .= ")$"
