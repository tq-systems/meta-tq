LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require linux-tq-common.inc

KBRANCH = "TQM-linux-v6.12.y"
SRCREV = "7af74ea494e58ba2457b7df79c189752aa665c0f"

# LINUX_VERSION must match version from Makefile
LINUX_RELEASE = "6.12"
LINUX_VERSION = "${LINUX_RELEASE}.18"

FILESEXTRAPATHS:prepend := "${THISDIR}/linux-tq-${LINUX_RELEASE}:"

KERNEL_CONFIG_FILES ?= ""

KERNEL_CONFIG_FILES:imx ?= "\
    file://imx.cfg \
    file://nonimx-drm-removal.cfg \
    file://rm-non-tq-platforms.cfg \
    file://usb.cfg \
    file://ubi.cfg \
    file://features/devicetree.cfg \
    file://features/network.cfg \
"

GIT_URL ?= "${TQ_GIT_BASEURL}/linux-tqmaxx.git"
GIT_PROTOCOL ?= "${TQ_GIT_PROTOCOL}"

SRC_URI = "\
    ${GIT_URL};protocol=${GIT_PROTOCOL};branch=${KBRANCH} \
    ${KERNEL_CONFIG_FILES} \
"

KBUILD_DEFCONFIG_DEFAULT = ""
KBUILD_DEFCONFIG_DEFAULT:imx:armv7a = "imx_v6_v7_defconfig"
KBUILD_DEFCONFIG_DEFAULT:imx:armv7ve = "imx_v6_v7_defconfig"
KBUILD_DEFCONFIG_DEFAULT:qoriq-arm:armv7a = "imx_v6_v7_defconfig"
KBUILD_DEFCONFIG_DEFAULT:qoriq-arm:armv7ve = "imx_v6_v7_defconfig"
KBUILD_DEFCONFIG_DEFAULT:aarch64 = "defconfig"
KBUILD_DEFCONFIG ?= "${KBUILD_DEFCONFIG_DEFAULT}"
