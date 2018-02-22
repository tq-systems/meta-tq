SUMMARY = "Linux kernel for TQ-Group TI AM57 based modules"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel

require recipes-kernel/linux/linux-dtb.inc

# TODO: Test and use following SRC_URI with github meta-tq layer
# SRC_URI = "git://github.com/tq-systems/linux-tqmaxx.git;protocol=https;branch=${SRCBRANCH} \
#	     file://defconfig"
# Also, TQMa57xx defconfig must be put to
# meta-tq/recipes-kernel/linux/linux-tq-xxxx/tqma57xx/defconfig
# then.
# Currently existing file is only a link to
# /opt/tqma57xx-bsp/linux-tqmaxx/arch/arm/configs/tqma57xx_defconfig

SRC_URI = "git:///opt/tqma57xx-bsp/linux-tqmaxx/.git;branch=${SRCBRANCH} \
	   file://defconfig"

# TODO: adopt SRCREV and SRCBRANCH when available on github */
SRCBRANCH = "private/gateware_lange_stefan/TQMa57xx_devel"
SRCREV = "3598123589fe7d2cbc4e1b3ab4fa4ce8c0ed0419"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

COMPATIBLE_MACHINE = "(tqma572x-mba57xx)"

S = "${WORKDIR}/git"
