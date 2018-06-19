SUMMARY = "Linux kernel for TQ-Group TI AM57 based modules"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel

DEPENDS += "lzop-native bc-native"
#
# append linux-mainline if we provide mainline kernel versions
#
PROVIDES = "virtual/kernel"

require recipes-kernel/linux/linux-dtb.inc


# TODO: 
# - rename recipe to linux-tq or linux-tq-rt (in case we support also kernel w/o rt)
SRC_URI = " \
    ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
    file://defconfig \
"

# TODO: adopt SRCREV and SRCBRANCH when available on github */
SRCBRANCH = "TQMa57xx-linux-ti-v4.9.77-rt61"
SRCREV = "45787c7ab5865fb2ff50a65b1924c95015f9fbbf"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

COMPATIBLE_MACHINE = "tqma572x-mba57xx|tqma571x-mba57xx"

S = "${WORKDIR}/git"
