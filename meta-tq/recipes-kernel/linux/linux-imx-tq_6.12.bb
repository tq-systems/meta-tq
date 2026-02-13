# SPDX-License-Identifier: MIT
#
# Copyright (c) 2021-2025 TQ-Systems GmbH <oss@ew.tq-group.com>,
# D-82229 Seefeld, Germany.

SUMMARY = "Linux kernel based on linux-imx for TQ-Systems GmbH i.MX / LS based modules"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require linux-imx-tq-common.inc

KBRANCH = "TQMa-fslc-6.12-2.0.x-imx"
SRCREV = "f8bb32936b7af2989d5dc2dd258352212824e64a"

# LINUX_VERSION must match version from Makefile
LINUX_RELEASE = "6.12"
LINUX_VERSION = "${LINUX_RELEASE}.55"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"
FILESEXTRAPATHS:prepend:aarch64 := "${THISDIR}/${PN}-${LINUX_RELEASE}/imx-arm64:"

SRC_URI = "\
    ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${KBRANCH} \
"

KBUILD_DEFCONFIG:aarch64 = "imx_v8_defconfig"

SRC_URI:aarch64 = "\
    ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${KBRANCH} \
    file://acpi-removal.cfg \
    file://adc-hwmon-support.cfg \
    file://audio-support.cfg \
    file://bpf-support.cfg \
    file://display-support.cfg \
    file://enable-led-features.cfg \
    file://features/debug.cfg \
    file://features/devicetree.cfg \
    file://features/iio.cfg \
    file://features/network.cfg \
    file://features/pwm.cfg \
    file://features/rauc.cfg \
    file://features/spidev.cfg \
    file://features/trusted-keys.cfg \
    file://features/ubi.cfg \
    file://features/usb.cfg \
    file://gpio-enablement.cfg \
    file://imx8m-platform-support.cfg \
    file://imx9-platform-support.cfg \
    file://input-devices.cfg \
    file://local-version.cfg \
    file://network-support.cfg \
    file://nonimx-drm-removal.cfg \
    file://optimize-filesystem-selection.cfg \
    file://quectel-wwan-support.cfg \
    file://regulator-support.cfg \
    file://rfkill-support.cfg \
    file://rtc.cfg \
    file://sdma.cfg \
    file://tq-generic-devices.cfg \
    file://unused-media.cfg \
    file://usb-gadget-support.cfg \
    file://usb-special-drivers.cfg \
    file://v4l2.cfg \
    file://wifi-support.cfg \
"

SRC_URI:append = " \
    ${@bb.utils.contains('COMBINED_FEATURES', 'bluetooth', 'file://features/bluetooth-support.cfg', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'verity', 'file://features/dm-verity.cfg', '', d)} \
"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:tqma95xx = "tqma95xx"
