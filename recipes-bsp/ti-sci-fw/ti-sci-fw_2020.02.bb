SUMMARY = "TI SCI firmware"

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://LICENSE.ti;md5=b5aebf0668bdf95621259288c4a46d76"

DEPENDS = "openssl-native u-boot-mkimage-native dtc-native"

CLEANBROKEN = "1"
PR = "r0"

COMPATIBLE_MACHINE = "k3r5"

PACKAGE_ARCH = "${MACHINE_ARCH}"

TI_SECURE_DEV_PKG ?= ""
export TI_SECURE_DEV_PKG

SRCREV_imggen = "c0d6e6ebc85d9e7e4e02a7e6364cadf31c6fad0d"
SRCREV_FORMAT = "imggen"

SRC_URI = " \
        git://git.ti.com/k3-image-gen/k3-image-gen.git;protocol=git;branch=master;destsuffix=imggen;name=imggen \
"

S = "${WORKDIR}/git"

SYSFW_SOC = "am65x_sr2"
SYSFW_CONFIG = "evm"

SYSFW_PREFIX = "ti-sci-firmware"

SYSFW_BINARY = "sysfw-${SYSFW_SOC}-${SYSFW_CONFIG}.itb"
SYSFW_IMAGE = "sysfw-${PV}.itb"
SYSFW_SYMLINK = "sysfw.itb"

CFLAGS[unexport] = "1"
LDFLAGS[unexport] = "1"
AS[unexport] = "1"
LD[unexport] = "1"

do_configure[noexec] = "1"
do_populate_lic[noexec] = "1"

EXTRA_OEMAKE = "\
    CROSS_COMPILE="${TARGET_PREFIX}" SOC="${SYSFW_SOC}"\
 \
"
do_compile() {
        cd ${WORKDIR}/imggen/
        oe_runmake
}

do_install() {
        install -d ${D}/boot
        install -m 644 ${WORKDIR}/imggen/${SYSFW_BINARY} ${D}/boot/${SYSFW_IMAGE}
        ln -sf ${SYSFW_IMAGE} ${D}/boot/${SYSFW_SYMLINK}
}

FILES_${PN} = "/boot"

inherit deploy

do_deploy () {
        install -d ${DEPLOYDIR}
        install -m 644 ${WORKDIR}/imggen/${SYSFW_BINARY} ${DEPLOYDIR}/${SYSFW_IMAGE}
        rm -f ${DEPLOYDIR}/${SYSFW_SYMLINK}
        ln -sf ${SYSFW_IMAGE} ${DEPLOYDIR}/${SYSFW_SYMLINK}
}

addtask deploy before do_build after do_compile
