SUMMARY = "Linux kernel based on linux stable 6.12.y for TQ-Systems GmbH i.MX and Layerscape SoM"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require linux-tq-common.inc

KBRANCH = "linux-6.12.y"
SRCREV = "77f85ccd3618f324d221f0faaed6d9cdc118c74a"

# LINUX_VERSION must match version from Makefile
LINUX_RELEASE = "6.12"
LINUX_VERSION = "${LINUX_RELEASE}.8"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

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

GIT_URL ?= "git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git"
GIT_PROTOCOL ?= "https"

SRC_URI = "\
    git://${GIT_URL};protocol=${GIT_PROTOCOL};branch=${KBRANCH} \
    ${KERNEL_CONFIG_FILES} \
    file://0001-arm64-dts-imx8mp-Add-TC9595-DSI-DP-bridge-on-TQMa8MP.patch \
    file://0002-drm-bridge-samsung-dsim-Initialize-bridge-on-attach.patch \
    file://0003-drm-bridge-tc358767-Reset-chip-again-on-attach.patch \
"

def kbuild_defconfig(d):
    overrides = d.getVar('MACHINEOVERRIDES').split(':')
    if 'armv7a' in overrides:
        return "imx_v6_v7_defconfig"
    elif 'armv7ve' in overrides:
        return "imx_v6_v7_defconfig"
    else:
        return "defconfig"

KBUILD_DEFCONFIG = "${@kbuild_defconfig(d)}"
KBUILD_DEFCONFIG[vardeps] = "MACHINEOVERRIDES"

DEFAULT_PREFERENCE = "0"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:tqma8mpxl = "tqma8mpxl"
