SUMMARY = "Linux kernel for TQ-Group TI AM57 based modules"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel

DEPENDS += "lzop-native bc-native"
#
# append linux-mainline if we provide mainline kernel versions
#
PROVIDES += "linux-tq"

# TODO: 
# - rename recipe to linux-tq or linux-tq-rt (in case we support also kernel w/o rt)
SRC_URI = " \
    ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
    file://defconfig \
"

SRCBRANCH = "TQMa57xx-TI-linux-v4.9"
SRCREV = "5795016a5c4cf28997f530f7da4ab05b3988cba6"

# we still need this, since meta-ti sets KERNEL_IMAGETYPES to contain uImage
# and forces building this ancient image which forces us to provide this var
KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

COMPATIBLE_MACHINE = "tqma57xx"

S = "${WORKDIR}/git"
