LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

python () {
    if d.getVar("PREFERRED_PROVIDER_virtual/kernel") == d.getVar("PN"):
        bb.warn("%s %s: this recipe and the BSP setup get no further updates from TQ-Systems GmbH. Please merge updates from kernel.org and include meta-lts-collab in your BSP or update to a maintained LTS version." % (d.getVar("PN"), d.getVar("PV")))
}

require linux-tq-common.inc

# LINUX_VERSION must match version from Makefile
LINUX_RELEASE = "6.1"

FILESEXTRAPATHS:prepend := "${THISDIR}/linux-tq-${LINUX_RELEASE}:"

KERNEL_CONFIG_FILES ?= ""
KERNEL_EXTRA_CONFIG_FILES ?= ""

KERNEL_CONFIG_FILES:imx ?= "\
    file://imx.cfg \
    file://nonimx-drm-removal.cfg \
    file://rm-non-tq-platforms.cfg \
    file://imx8-usb.cfg \
    file://ubi.cfg \
"

# TQ SOM with 64 Bit Layerscape ()
KERNEL_CONFIG_FILES:tqmlsx = "\
    file://defconfig \
"

KERNEL_CONFIG_FILES:tqmls1088a = "\
    file://defconfig \
    file://disable_suspend.cfg \
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
KBUILD_DEFCONFIG_DEFAULT:tqmlsx = ""
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
COMPATIBLE_MACHINE:tqmls10xxa = "tqmls10xxa"
COMPATIBLE_MACHINE:tqmlx2160a = "tqmlx2160a"
