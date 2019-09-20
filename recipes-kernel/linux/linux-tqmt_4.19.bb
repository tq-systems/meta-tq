inherit kernel

SUMMARY = "Linux for TQ-Group Freescale T104x based modules"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

DEPENDS += " lzop-native bc-native openssl-native"
#
# append linux-mainline if we provide mainline kernel versions
#
PROVIDES += "linux-mainline linux-tqmt"

SRC_URI = "\
	${TQ_GIT_BASEURL}/linux-tqmt.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
	file://defconfig \
"
SRCBRANCH = "TQMT104x-linux-v4.19"
SRCREV = "77a7e3bc2d4388a2a9bf4dd089f1a5ab76bd8269"

KERNEL_DEFCONFIG = "${WORKDIR}/defconfig"

KERNEL_IMAGETYPE = "uImage"

LINUX_VERSION = "4.19.67"
LOCALVERSION = "-tqmt104x"

S = "${WORKDIR}/git"

DEPENDS_append = " libgcc"
# Do not put Images into /boot of rootfs, install kernel-image if needed
RDEPENDS_${KERNEL_PACKAGE_NAME}-base = ""

do_configure_prepend() {
	echo "" > ${B}/.config
	echo "CONFIG_LOCALVERSION=\"${LOCALVERSION}\"" >> ${B}/.config
	echo "CONFIG_LOCALVERSION_AUTO=y" >> ${B}/.config
	cat "${KERNEL_DEFCONFIG}" | sed \
		-e '/CONFIG_LOCALVERSION[ =]/d' \
		-e '/CONFIG_LOCALVERSION_AUTO[ =]/d' \
	>> ${B}/.config || exit 1
}

COMPATIBLE_MACHINE = "tqmt104x"
