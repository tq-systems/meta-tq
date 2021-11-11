LIC_FILES_CHKSUM = "file://LICENSE.md;md5=7f62d8fc087d1e90350a140c9f8c8e99"
LICENSE="BSD-3-Clause"
PV = "1.02+git${SRCPV}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

FLASH_WRITER_URL = "git://github.com/renesas-rz/rzg2_flash_writer.git"
BRANCH = "master"

SRC_URI = "${FLASH_WRITER_URL};branch=${BRANCH}"
SRCREV = "a93b599de1f8b571c1e3868685e944436449c756"

SRC_URI_append = " \
	${@oe.utils.conditional("SECURE_BOOT", "1", "file://0001-Add-Secure-Boot-Injection.patch", "",d)} \
"

inherit deploy
require include/provisioning.inc

S = "${WORKDIR}/git"

do_compile() {
        if [ "${MACHINE}" = "hihope-rzg2n" -o "${MACHINE}" = "hihope-rzg2m" -o "${MACHINE}" = "hihope-rzg2h" ]; then
                BOARD="HIHOPE";
        elif [ "${MACHINE}" = "ek874" ]; then
                BOARD="EK874";
        elif [ "${MACHINE}" = "tqmarzg2h_c-mbarzg2x" ]; then
                BOARD="TQMARZG2H_C";
        elif [ "${MACHINE}" = "tqmarzg2m_aa-mbarzg2x" ]; then
                BOARD="TQMARZG2M_AA";
        elif [ "${MACHINE}" = "tqmarzg2m_e-mbarzg2x" ]; then
                BOARD="TQMARZG2M_E";
        elif [ "${MACHINE}" = "tqmarzg2n_b-mbarzg2x" ]; then
                BOARD="TQMARZG2N_B";
        fi
        cd ${S}

	if [ 1 -eq ${SECURE_BOOT} ]; then
		oe_runmake BOARD=${BOARD} ENC_PROVISIONING_KEY=${ENCRYPTED_PROVISIONING_KEY}
	else
		oe_runmake BOARD=${BOARD}
	fi
}

do_install[noexec] = "1"

do_deploy() {
        install -d ${DEPLOYDIR}
        install -m 644 ${S}/AArch64_output/*.mot ${DEPLOYDIR}
}
PARALLEL_MAKE = "-j 1"
SECURITY_STACK_PROTECTOR = ""
addtask deploy after do_compile
