SUMMARY = "Linux kernel for TQ-Group TI AM57 based modules"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel

require recipes-kernel/linux/linux-dtb.inc

SRC_URI = " \
    ${TQ_GIT}/linux-tqmaxx;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
    file://defconfig \
"

SRCBRANCH = "private/gateware_graefe_konrad/TQMa57xx_devel"
SRCREV = "45787c7ab5865fb2ff50a65b1924c95015f9fbbf"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

COMPATIBLE_MACHINE = "tqma572x-mba57xx|tqma571x-mba57xx"

S = "${WORKDIR}/git"
