SUMMARY = "ARM Trusted Firmware"
DESCRIPTION = "ARM Trusted Firmware provides a reference implementation of \
Secure World software for ARMv8-A, including Exception Level 3 (EL3) software. \
It provides implementations of various ARM interface standards such as the \
Power State Coordination Interface (PSCI), Trusted Board Boot Requirements \
(TBBR) and Secure monitor code."
HOMEPAGE = "http://infocenter.arm.com/help/topic/com.arm.doc.dui0928e/CJHIDGJF.html"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://docs/license.rst;md5=189505435dbcdcc8caa63c46fe93fa89"

inherit deploy

DEPENDS += "dtc-native openssl-native"

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"

PV_append = "+git${SRCPV}"

BRANCH = "ti-atf"
SRC_URI = "git://git.ti.com/atf/arm-trusted-firmware.git;branch=${BRANCH}"

SRCREV ?= "e516a389ac12fbe1597f61fea80ed3f230b9c5fd"

# Make ATF "aware" of OPTEE, no build dependency
#PACKAGECONFIG[optee] = "SPD=opteed"

COMPATIBLE_MACHINE = "tqma65xx"
ATFPLATFORM_tqma65xx = "k3"
ATFBOARD_tqma65xx = "generic"

#PACKAGECONFIG_tqma65xx = "optee"

CFLAGS[unexport] = "1"
LDFLAGS[unexport] = "1"
AS[unexport] = "1"
LD[unexport] = "1"

do_configure[noexec] = "1"

EXTRA_OEMAKE = 'CROSS_COMPILE="${TARGET_PREFIX}" PLAT="${ATFPLATFORM}" TARGET_BOARD="${ATFBOARD}" ${PACKAGECONFIG_CONFARGS}'

do_compile() {
	oe_runmake all
}

do_install() {
	install -d ${D}/boot
	install -m 0644 ${S}/build/${ATFPLATFORM}/${ATFBOARD}/release/bl31.bin ${D}/boot/
	install -m 0644 ${S}/build/${ATFPLATFORM}/${ATFBOARD}/release/bl31/bl31.elf ${D}/boot/
}

do_deploy() {
	install -d ${DEPLOYDIR}
	install -m 0644 ${S}/build/${ATFPLATFORM}/${ATFBOARD}/release/bl31.bin ${DEPLOYDIR}/
	install -m 0644 ${S}/build/${ATFPLATFORM}/${ATFBOARD}/release/bl31/bl31.elf ${DEPLOYDIR}/
}
addtask deploy before do_build after do_compile

FILES_${PN} = "/boot"
SYSROOT_DIRS += "/boot"
