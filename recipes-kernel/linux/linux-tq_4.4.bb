SUMMARY = "Linux for TQ-Group Freescale LS102xA based modules"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

DEPENDS += "lzop-native bc-native"

#
# append linux-mainline if we provide mainine kernel versions
#
PROVIDES = "virtual/kernel linux-mainline"

inherit kernel

require recipes-kernel/linux/linux-dtb.inc


SRC_URI = "git://github.com/tq-systems/linux-tqmaxx.git;protocol=https;branch=${SRCBRANCH} \
           file://defconfig"

SRCBRANCH = "TQMLS102x-linux-v4.4-BSP0103"
SRCREV = "2e65193381f55789111d2a459bf7c14a59abac8b"

COMPATIBLE_MACHINE = "(tqmls102xa-mbls102xa)"

S = "${WORKDIR}/git"
