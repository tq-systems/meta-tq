SUMMARY = "Linux kernel based on linux stable 6.1.y for TQ-Systems GmbH i.MX and Layerscape SoM"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require linux-tq-common.inc

KBRANCH = "TQM-linux-v6.1.y"
SRCREV = "d2a9f95315838ea89d4136796b0421e024d570ff"

# LINUX_VERSION must match version from Makefile
LINUX_RELEASE = "6.1"
LINUX_VERSION = "${LINUX_RELEASE}.82"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${LINUX_RELEASE}:"

KERNEL_CONFIG_FILES ?= ""

KERNEL_CONFIG_FILES:imx ?= "\
    file://imx.cfg \
    file://nonimx-drm-removal.cfg \
    file://rm-non-tq-platforms.cfg \
    file://imx8-usb.cfg \
"

KERNEL_CONFIG_FILES:tqmlsx ?= "\
    file://defconfig \
"

KERNEL_CONFIG_FILES:append:tqmls1088a = "\
    file://disable_suspend.cfg \
"

SRC_URI = "\
    ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${KBRANCH} \
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
KBUILD_DEFCONFIG:tqmlsx = ""

DEFAULT_PREFERENCE = "0"

COMPATIBLE_MACHINE = "^("
COMPATIBLE_MACHINE .= "tqma6x"
COMPATIBLE_MACHINE .= "|tqma6ulx"
COMPATIBLE_MACHINE .= "|tqma6ullx"
COMPATIBLE_MACHINE .= "|tqma7x"
COMPATIBLE_MACHINE .= "|tqma8mq"
COMPATIBLE_MACHINE .= "|tqma8mxml"
COMPATIBLE_MACHINE .= "|tqma8mxnl"
COMPATIBLE_MACHINE .= "|tqma8mpxl"
COMPATIBLE_MACHINE .= "|tqmls10xxa"
COMPATIBLE_MACHINE .= "|tqmlx2160a"
COMPATIBLE_MACHINE .= ")$"
