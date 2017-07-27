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

SRCBRANCH = "TQMLS102x-linux-v4.4"
SRCREV = "d732ef64066f74b98604bcf068ca8717ea91a761"

COMPATIBLE_MACHINE = "(tqmls102xa-mbls102xa)"

S = "${WORKDIR}/git"
