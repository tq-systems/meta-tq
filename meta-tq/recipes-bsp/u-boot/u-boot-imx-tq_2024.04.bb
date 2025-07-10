# SPDX-License-Identifier: MIT
#
# Copyright (c) 2024 TQ-Systems GmbH <oss@ew.tq-group.com>,
# D-82229 Seefeld, Germany.

DESCRIPTION = "U-Boot for TQ-Systems GmbH NXP i.MX8MP / i.MX9 based SOM"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"

SRCREV = "2a1e3659ec15dd9e3b4fc97b1f8e9ce1b1d6a740"
SRCBRANCH = "TQM-lf_v2024.04"

SRC_URI = "\
    ${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
"

SRC_URI:append:nxp-ahab = " ${@bb.utils.contains('DISTRO_FEATURES', 'secure', 'file://imx-hab.cfg', '', d)}"
SRC_URI:append:nxp-hab4 = " ${@bb.utils.contains('DISTRO_FEATURES', 'secure', 'file://imx-hab.cfg', '', d)}"

require recipes-bsp/u-boot/u-boot.inc
require u-boot-tq.inc
require u-boot-bootstream-deploy.inc

DEPENDS:append = "\
    bc-native \
    bison-native \
    dtc-native \
    python3-native \
"

UBOOT_NAME:mx8-generic-bsp = "u-boot-${MACHINE}.bin-${UBOOT_CONFIG}"
UBOOT_NAME:mx9-generic-bsp = "u-boot-${MACHINE}.bin-${UBOOT_CONFIG}"

UBOOT_INITIAL_ENV = "u-boot-initial-env"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:tqma8mpxl = "tqma8mpxl"
COMPATIBLE_MACHINE:tqma8mpxs = "tqma8mpxs"
COMPATIBLE_MACHINE:tqma91xx = "tqma91xx"
COMPATIBLE_MACHINE:tqma93xx = "tqma93xx"
