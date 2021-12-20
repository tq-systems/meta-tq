DESCRIPTION = "ARM Trusted Firmware"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = " \
   file://${WORKDIR}/git/docs/license.rst;md5=189505435dbcdcc8caa63c46fe93fa89 \
   file://${WORKDIR}/mbedtls/LICENSE;md5=302d50a6369f5f22efdb674db908167a \
"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy
require include/multimedia-control.inc
require include/ecc-control.inc

S = "${WORKDIR}/git"

BRANCH = "TQMaRZG2x-VLP_1.0.6_2.3.0"
BRANCH_mbedtls = "mbedtls-2.16"

SRC_URI = " \
	${TQ_GIT_BASEURL}/atf.git;branch=${BRANCH};protocol=${TQ_GIT_PROTOCOL} \
	git://github.com/ARMmbed/mbedtls.git;branch=${BRANCH_mbedtls};name=mbedtls;destsuffix=mbedtls \
"

SRCREV = "9d314ac335f7e363ddb5c08b8302fa9a6fc88c56"
SRCREV_mbedtls = "04a049bda1ceca48060b57bc4bcf5203ce591421"


PV = "v2.3+renesas+git"

COMPATIBLE_MACHINE = "(ek874|hihope-rzg2m|hihope-rzg2n|hihope-rzg2h|tqmarzg2n_b-mbarzg2x|tqmarzg2m_e-mbarzg2x|tqmarzg2m_aa-mbarzg2x|tqmarzg2h_c-mbarzg2x)"
PLATFORM = "rzg"
ATFW_OPT_LOSSY = "${@oe.utils.conditional("USE_MULTIMEDIA", "1", "RCAR_LOSSY_ENABLE=1", "", d)}"
ATFW_OPT_r8a774c0 = "LSI=G2E RCAR_SA0_SIZE=0 RCAR_AVS_SETTING_ENABLE=0 RZG_EK874=1 PMIC_ROHM_BD9571=0 RCAR_SYSTEM_SUSPEND=0 RCAR_DRAM_DDR3L_MEMCONF=1 RCAR_DRAM_DDR3L_MEMDUAL=1 SPD="none""
ATFW_OPT_r8a774a1 = "LSI=G2M RCAR_DRAM_SPLIT=2 RCAR_AVS_SETTING_ENABLE=0 RZG_HIHOPE_RZG2M=1 PMIC_ROHM_BD9571=0 RCAR_SYSTEM_SUSPEND=0 RCAR_SECURE_BOOT=0 SPD="none""
ATFW_OPT_r8a774b1 = "LSI=G2N RCAR_AVS_SETTING_ENABLE=0 RZG_HIHOPE_RZG2N=1 PMIC_ROHM_BD9571=0 RCAR_SYSTEM_SUSPEND=0 SPD="none""
ATFW_OPT_r8a774e1 = "LSI=G2H RCAR_DRAM_SPLIT=2 RCAR_DRAM_LPDDR4_MEMCONF=1 RCAR_DRAM_CHANNEL=5 RCAR_AVS_SETTING_ENABLE=0 RZG_HIHOPE_RZG2H=1 PMIC_ROHM_BD9571=0 RCAR_SYSTEM_SUSPEND=0 SPD="none""

ATFW_OPT_append_r8a774c0 = "${@oe.utils.conditional("USE_ECC", "1", " LIFEC_DBSC_PROTECT_ENABLE=0 RZG_DRAM_ECC=1 ", "",d)}"

ATFW_OPT_append_r8a774a1 = "${@oe.utils.conditional("USE_ECC", "1", " LIFEC_DBSC_PROTECT_ENABLE=0 RCAR_DRAM_SPLIT=0 RZG_DRAM_ECC=1 ", " ${ATFW_OPT_LOSSY} ",d)}"

ATFW_OPT_append_r8a774b1 = "${@oe.utils.conditional("USE_ECC", "1", " LIFEC_DBSC_PROTECT_ENABLE=0 RZG_DRAM_ECC=1 ", " ${ATFW_OPT_LOSSY} ",d)}"

ATFW_OPT_append_r8a774e1 = "${@oe.utils.conditional("USE_ECC", "1", " LIFEC_DBSC_PROTECT_ENABLE=0 RCAR_DRAM_SPLIT=0 RZG_DRAM_ECC=1 ", " ${ATFW_OPT_LOSSY} ",d)}"

ATFW_OPT_append += " RZG_DRAM_ECC_FULL=${ECC_FULL} "
ATFW_OPT_append += " RCAR_RPC_HYPERFLASH_LOCKED=0 MBEDTLS_DIR=../mbedtls "

# requires CROSS_COMPILE set by hand as there is no configure script
export CROSS_COMPILE="${TARGET_PREFIX}"

# Let the Makefile handle setting up the CFLAGS and LDFLAGS as it is a standalone application
CFLAGS[unexport] = "1"
LDFLAGS[unexport] = "1"
AS[unexport] = "1"
LD[unexport] = "1"

do_compile() {
    oe_runmake distclean
    oe_runmake bl2 bl31 rzg PLAT=${PLATFORM} ${ATFW_OPT}
}

# do_install() nothing
do_install[noexec] = "1"

do_deploy() {
    # Create deploy folder
    install -d ${DEPLOYDIR}

    # Copy IPL to deploy folder
    install -m 0644 ${S}/build/${PLATFORM}/release/bl2/bl2.elf ${DEPLOYDIR}/bl2-${MACHINE}.elf
    install -m 0644 ${S}/build/${PLATFORM}/release/bl2.bin ${DEPLOYDIR}/bl2-${MACHINE}.bin
    install -m 0644 ${S}/build/${PLATFORM}/release/bl2.srec ${DEPLOYDIR}/bl2-${MACHINE}.srec
    install -m 0644 ${S}/build/${PLATFORM}/release/bl31/bl31.elf ${DEPLOYDIR}/bl31-${MACHINE}.elf
    install -m 0644 ${S}/build/${PLATFORM}/release/bl31.bin ${DEPLOYDIR}/bl31-${MACHINE}.bin
    install -m 0644 ${S}/build/${PLATFORM}/release/bl31.srec ${DEPLOYDIR}/bl31-${MACHINE}.srec
    install -m 0644 ${S}/tools/renesas/rzg_layout_create/bootparam_sa0.srec ${DEPLOYDIR}/bootparam_sa0.srec
    install -m 0644 ${S}/tools/renesas/rzg_layout_create/cert_header_sa6.srec ${DEPLOYDIR}/cert_header_sa6.srec
}

addtask deploy before do_build after do_compile
