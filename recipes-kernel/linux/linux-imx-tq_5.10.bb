SUMMARY = "Linux kernel based on linux-fslc-imx for TQ-Systems GmbH i.MX / LS based modules"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require linux-imx-tq-common.inc

KBRANCH = "TQMa8-fslc-5.10-2.1.x-imx"
SRCREV = "a988e804a17dcc33ae7cbfb6a03a217d81bfa908"
# LINUX_VERSION must match version from Makefile
LINUX_RELEASE = "5.10"
LINUX_VERSION = "${LINUX_RELEASE}.74"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

SRC_URI = "\
    ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${KBRANCH} \
    file://defconfig \
"

DEFAULT_PREFERENCE = "1"

KBUILD_DEFCONFIG_mx6 = "imx_v7_defconfig"
KBUILD_DEFCONFIG_mx8 = "imx_v8_defconfig"

SRC_URI_mx6 = "\
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

SRC_URI_mx8 = "\
    ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${KBRANCH} \
    file://local-version.cfg \
    file://tqma8x-display-support.cfg \
    file://tqma8x-network-support.cfg \
    file://tqma8x-i2c-devices.cfg \
    file://acpi-removal.cfg \
    file://sdma.cfg \
    file://tqma8x-input-devices.cfg \
    file://tqma8x-bpf-support.cfg \
    file://tqma8x-audio-support.cfg \
    file://tqma8x-usb-special-drivers.cfg \
    file://tqma8x-adc-hwmon-support.cfg \
    file://tqma8x-rtc.cfg \
    file://tqma8x-nonimx-drm-removal.cfg \
    file://tqma8x-wifi-support.cfg \
    file://tqma8x-v4l2.cfg \
"

COMPATIBLE_MACHINE = "^("
# COMPATIBLE_MACHINE .= "tqma7x"
COMPATIBLE_MACHINE .= "|tqma6x"
# COMPATIBLE_MACHINE .= "|tqma6ulx"
# COMPATIBLE_MACHINE .= "|tqma6ullx"
# COMPATIBLE_MACHINE .= "|tqmls1012al"
# COMPATIBLE_MACHINE .= "|tqmls1028a"
COMPATIBLE_MACHINE .= "|tqma8x"
COMPATIBLE_MACHINE .= "|tqma8xx"
COMPATIBLE_MACHINE .= "|tqma8xxs"
COMPATIBLE_MACHINE .= "|tqma8mpxl"
COMPATIBLE_MACHINE .= "|tqma8mq"
COMPATIBLE_MACHINE .= "|tqma8mxml"
COMPATIBLE_MACHINE .= "|tqma8mxnl"
COMPATIBLE_MACHINE .= ")$"

EXTRA_OEMAKE_append_mx8 = " ARCH=arm64"
