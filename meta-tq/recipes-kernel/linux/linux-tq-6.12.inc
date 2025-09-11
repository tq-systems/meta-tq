LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require linux-tq-common.inc

KBRANCH = "TQM-linux-v6.12.y"
KMETA = "kernel-meta"
SRCREV_machine = "c5600566fa9b3c4a07f903dfe8def74e6a34f269"
SRCREV_meta = "cf35a8d4adea70d86608241d50e1372d50a1484f"

# LINUX_VERSION must match version from Makefile
LINUX_RELEASE = "6.12"
LINUX_VERSION = "${LINUX_RELEASE}.45"

FILESEXTRAPATHS:prepend := "${THISDIR}/linux-tq-${LINUX_RELEASE}:"

KERNEL_CONFIG_FILES ?= ""

KERNEL_CONFIG_FILES:imx ?= "\
    file://imx.cfg \
    file://features/nonimx-drm-removal.cfg \
    file://features/debug.cfg \
    file://features/devicetree.cfg \
    file://features/disable-hibernate.cfg \
    file://features/network.cfg \
    file://features/rauc.cfg \
    file://features/spidev.cfg \
    file://features/rm-non-tq-platforms.cfg \
    file://features/ubi.cfg \
    file://features/usb.cfg \
"

KERNEL_CONFIG_FILES:tqma335x ?= "\
    file://tqma335x.cfg \
    file://can.cfg \
    file://display.cfg \
    file://sound.cfg \
    file://spi.cfg \
    file://features/devicetree.cfg \
    file://features/network.cfg \
    file://features/spidev.cfg \
    file://features/ubi.cfg \
"

KERNEL_CONFIG_FILES:qoriq ?= "\
    file://disable-imx-platforms.cfg \
    file://disable_suspend.cfg \
    file://dpaa2.cfg \
    file://smmu-bypass.cfg \
    file://features/nonimx-drm-removal.cfg \
    file://features/debug.cfg \
    file://features/devicetree.cfg \
    file://features/network.cfg \
    file://features/rm-non-tq-platforms.cfg \
    file://features/ubi.cfg \
    file://features/usb.cfg \
"

KERNEL_CONFIG_FILES:append:tqmls102xa ?= "\
    file://ls1021a.cfg \
"

GIT_URL ?= "${TQ_GIT_BASEURL}/linux-tqmaxx.git"
GIT_PROTOCOL ?= "${TQ_GIT_PROTOCOL}"

SRC_URI = "\
    ${GIT_URL};protocol=${GIT_PROTOCOL};name=machine;branch=${KBRANCH} \
    git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=yocto-${LINUX_RELEASE};destsuffix=${KMETA};protocol=https \
    ${KERNEL_CONFIG_FILES} \
"

KBUILD_DEFCONFIG_DEFAULT = ""
KBUILD_DEFCONFIG_DEFAULT:imx:armv7a = "imx_v6_v7_defconfig"
KBUILD_DEFCONFIG_DEFAULT:imx:armv7ve = "imx_v6_v7_defconfig"
KBUILD_DEFCONFIG_DEFAULT:qoriq-arm:armv7a = "imx_v6_v7_defconfig"
KBUILD_DEFCONFIG_DEFAULT:qoriq-arm:armv7ve = "imx_v6_v7_defconfig"
KBUILD_DEFCONFIG_DEFAULT:aarch64 = "defconfig"
KBUILD_DEFCONFIG ?= "${KBUILD_DEFCONFIG_DEFAULT}"

KMACHINE:tqma335x ?= "ti-am335x"

KERNEL_EXTRA_FEATURES ?= ""
KERNEL_EXTRA_FEATURES:tqma335x ?= "features/leds/leds.scc"
KERNEL_FEATURES:append = " ${KERNEL_EXTRA_FEATURES}"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:tqma335x = "tqma335x"
COMPATIBLE_MACHINE:tqma8mpxl = "tqma8mpxl"
COMPATIBLE_MACHINE:tqma8mpxs = "tqma8mpxs"
COMPATIBLE_MACHINE:tqma93xx = "tqma93xx"
COMPATIBLE_MACHINE:tqmls102xa = "tqmls102xa"
COMPATIBLE_MACHINE:tqmls10xxa = "tqmls10xxa"
COMPATIBLE_MACHINE:tqmlx2160a = "tqmlx2160a"
