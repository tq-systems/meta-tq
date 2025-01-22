SUMMARY = "Linux kernel based on linux stable 6.12.y for TQ-Systems GmbH i.MX and Layerscape SoM"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require linux-tq-common.inc

KBRANCH = "TQM-linux-v6.12.y"
SRCREV = "14510edbe56de4e8a1cbab1272c6625c229e06d3"

# LINUX_VERSION must match version from Makefile
LINUX_RELEASE = "6.12"
LINUX_VERSION = "${LINUX_RELEASE}.10"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

KERNEL_CONFIG_FILES ?= ""

KERNEL_CONFIG_FILES:imx ?= "\
    file://imx.cfg \
    file://nonimx-drm-removal.cfg \
    file://rm-non-tq-platforms.cfg \
    file://usb.cfg \
    file://ubi.cfg \
    file://features/devicetree.cfg \
"

GIT_URL ?= "${TQ_GIT_BASEURL}/linux-tqmaxx.git"
GIT_PROTOCOL ?= "${TQ_GIT_PROTOCOL}"

SRC_URI = "\
    ${GIT_URL};protocol=${GIT_PROTOCOL};branch=${KBRANCH} \
    ${KERNEL_CONFIG_FILES} \
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
