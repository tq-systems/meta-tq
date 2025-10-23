# SPDX-License-Identifier: MIT
#
# Copyright (c) 2022-2023 TQ-Systems GmbH <oss@ew.tq-group.com>,
# D-82229 Seefeld, Germany.

DESCRIPTION = "U-Boot for TQ-Systems GmbH NXP i.MX8 based SOM"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

SRCREV = "32a07fab26c134d763fb8427c6bd171dfa6a3a71"
SRCBRANCH = "TQMa8-v2020.04_imx_5.4.70_2.3.0"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

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

UBOOT_INITIAL_ENV = "u-boot-initial-env"

COMPATIBLE_MACHINE = "tqma8xx"
COMPATIBLE_MACHINE:append = "|tqma8xxs"
COMPATIBLE_MACHINE:append = "|tqma8qm"
COMPATIBLE_MACHINE:append = "|tqma8mq"
COMPATIBLE_MACHINE:append = "|tqma8mxml"
COMPATIBLE_MACHINE:append = "|tqma8mxnl"
