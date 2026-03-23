SUMMARY = "Linux kernel based on linux stable 6.6.y for TQ-Systems GmbH i.MX and Layerscape SoM"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require linux-tq-common.inc

# LINUX_VERSION must match version from Makefile
LINUX_RELEASE = "6.6"

FILESEXTRAPATHS:prepend := "${THISDIR}/linux-tq-${LINUX_RELEASE}:"

KERNEL_CONFIG_FILES ?= ""
KERNEL_EXTRA_CONFIG_FILES ?= ""

KERNEL_CONFIG_FILES:imx ?= "\
    file://imx.cfg \
    file://nonimx-drm-removal.cfg \
    file://rm-non-tq-platforms.cfg \
    file://usb.cfg \
    file://ubi.cfg \
    file://features/devicetree.cfg \
    file://features/network.cfg \
    file://features/ptp-1588.cfg \
    file://features/spidev.cfg \
    file://features/usb-gadget-support.cfg \
"

SRC_URI = "\
    ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${KBRANCH} \
    ${KERNEL_CONFIG_FILES} \
    ${KERNEL_EXTRA_CONFIG_FILES} \
"

KBUILD_DEFCONFIG_DEFAULT = ""
KBUILD_DEFCONFIG_DEFAULT:imx:armv7a = "imx_v6_v7_defconfig"
KBUILD_DEFCONFIG_DEFAULT:imx:armv7ve = "imx_v6_v7_defconfig"
KBUILD_DEFCONFIG_DEFAULT:qoriq-arm:armv7a = "imx_v6_v7_defconfig"
KBUILD_DEFCONFIG_DEFAULT:qoriq-arm:armv7ve = "imx_v6_v7_defconfig"
KBUILD_DEFCONFIG_DEFAULT:aarch64 = "defconfig"
KBUILD_DEFCONFIG ?= "${KBUILD_DEFCONFIG_DEFAULT}"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:tqma6x = "tqma6x"
COMPATIBLE_MACHINE:tqma6ulx = "tqma6ulx"
COMPATIBLE_MACHINE:tqma6ullx = "tqma6ullx"
COMPATIBLE_MACHINE:tqma7x = "tqma7x"
COMPATIBLE_MACHINE:tqma8mpxl = "tqma8mpxl"
COMPATIBLE_MACHINE:tqma8mq = "tqma8mq"
COMPATIBLE_MACHINE:tqma8mxml = "tqma8mxml"
COMPATIBLE_MACHINE:tqma8mxnl = "tqma8mxnl"
