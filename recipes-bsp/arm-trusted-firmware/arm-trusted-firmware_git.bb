DESCRIPTION = "ARM Trusted Firmware"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://license.rst;md5=e927e02bca647e14efd87e9e914b2443"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy
require include/multimedia-control.inc
require include/ecc-control.inc

S = "${WORKDIR}/git"

BRANCH = "rcar_gen3"
SRC_URI = "git://github.com/renesas-rcar/arm-trusted-firmware.git;branch=${BRANCH}"
SRCREV = "af9f429a48b438e314289f17947ad5d8036f398e"

SRC_URI += "${@'file://0001-Fix-ld-error-unrecognized-option-with-old-binutils.patch' \
           if '${BINUVERSION}' == '2.25' else ''}"

SRC_URI += "file://0002-plat-renesas-add-support-for-EK874-RZG2E.patch \
            file://0003-plat-renesas-bl2-add-ECC-support-for-DRAM.patch \
            file://0004-plat-renesas-add-support-for-HIHOPE-RZG2M.patch \
            file://0006-plat-renesas-rcar-qos-E3-mstat390.h-Modify-default-s.patch \
            file://0007-plat-renesas-rcar-qos-M3-mstat195.h-Modify-default-s.patch \
            file://0009-plat-renesas-add-support-for-HIHOPE-RZG2N.patch \
            file://0010-plat-renesas-rcar-qos-M3N-mstat195.h-Modify-default-.patch \
            file://0011-plat-renesas-rcar-bl2_fusa-Add-option-to-enable-ECC-.patch \
            file://0012-plat-renesas-bl2_fusa-add-HIHOPE-RZG2M-ECC-support.patch \
            file://0013-plat-renesas-bl2_fusa-Add-ECC-support-for-HIHOPE-RZG.patch \
            file://0014-plat-renesas-add-support-emmc-boot.patch \
            file://0015-plat-renesas-bugfix-RCAR_SECURE_BOOT_Disable.patch \
            file://0016-plat-renesas-bl2_fusa-hihope-rzg2m-do-not-enable-ECC.patch \
            file://0017-plat-renesas-bl2_fusa-Add-mem-initialization-b-f-and.patch \
            file://0018-plat-rcar-M3-qos_init_m3_v11-prevent-DRAM-Split-set-.patch \
            file://0019-plat-rcar-timer-bl2_swdt-increase-WDT-count-when-ECC.patch \
            file://0020-HiHope-Rev3.0-board-support.patch \
            file://0021-Plat-renesas-add-support-for-Hihope-RZG2H-board.patch \
            file://0022-plat-renesas-rcar-Change-value-condition-of-RZG_DRAM.patch \
            file://0023-plat-renesas-rcar-bl2_fusa-Add-ECC-support-for-RZ-G2.patch \
"

PV = "v1.5+renesas+git${SRCPV}"

COMPATIBLE_MACHINE = "(ek874|hihope-rzg2m|hihope-rzg2n|hihope-rzg2h|tqmarzg2n_b-mbarzg2x|tqmarzg2m_e-mbarzg2x|tqmarzg2h_c-mbarzg2x)"
PLATFORM = "rcar"
ATFW_OPT_LOSSY = "${@oe.utils.conditional("USE_MULTIMEDIA", "1", "RCAR_LOSSY_ENABLE=1", "", d)}"
ATFW_OPT_r8a774c0 = "LSI=G2E RCAR_SA0_SIZE=0 RCAR_AVS_SETTING_ENABLE=0 RZG_EK874=1 PMIC_ROHM_BD9571=0 RCAR_SYSTEM_SUSPEND=0 RCAR_DRAM_DDR3L_MEMCONF=1 RCAR_DRAM_DDR3L_MEMDUAL=1 SPD="none""
ATFW_OPT_r8a774a1 = "LSI=G2M RCAR_DRAM_SPLIT=2 RCAR_AVS_SETTING_ENABLE=0 RZG_HIHOPE_RZG2M=1 PMIC_ROHM_BD9571=0 RCAR_SYSTEM_SUSPEND=0 RCAR_SECURE_BOOT=0 SPD="none""
ATFW_OPT_r8a774b1 = "LSI=G2N RCAR_AVS_SETTING_ENABLE=0 RZG_HIHOPE_RZG2N=1 PMIC_ROHM_BD9571=0 RCAR_SYSTEM_SUSPEND=0 SPD="none""
ATFW_OPT_r8a774e1 = "LSI=G2H RCAR_DRAM_SPLIT=2 RCAR_DRAM_LPDDR4_MEMCONF=1 RCAR_DRAM_CHANNEL=5 RCAR_AVS_SETTING_ENABLE=0 RZG_HIHOPE_RZG2H=1 PMIC_ROHM_BD9571=0 RCAR_SYSTEM_SUSPEND=0 SPD="none""

ATFW_OPT_append_r8a774c0 = "${@oe.utils.conditional("USE_ECC", "1", " LIFEC_DBSC_PROTECT_ENABLE=0 RZG_DRAM_EK874_ECC=1 ", "",d)}"

ATFW_OPT_append_r8a774a1 = "${@oe.utils.conditional("USE_ECC", "1", " LIFEC_DBSC_PROTECT_ENABLE=0 RZG_DRAM_HIHOPE_RZG2M_ECC=1 RCAR_DRAM_SPLIT=0", " ${ATFW_OPT_LOSSY} ",d)}"

ATFW_OPT_append_r8a774b1 = "${@oe.utils.conditional("USE_ECC", "1", " LIFEC_DBSC_PROTECT_ENABLE=0 RZG_DRAM_HIHOPE_RZG2N_ECC=1", " ${ATFW_OPT_LOSSY} ",d)}"

ATFW_OPT_append_r8a774e1 = "${@oe.utils.conditional("USE_ECC", "1", " LIFEC_DBSC_PROTECT_ENABLE=0 RZG_DRAM_HIHOPE_RZG2H_ECC=1 RCAR_DRAM_SPLIT=0", " ${ATFW_OPT_LOSSY} ",d)}"

ATFW_OPT_append = " RZG_DRAM_ECC_FULL=${ECC_FULL} "

# requires CROSS_COMPILE set by hand as there is no configure script
export CROSS_COMPILE="${TARGET_PREFIX}"

# Let the Makefile handle setting up the CFLAGS and LDFLAGS as it is a standalone application
CFLAGS[unexport] = "1"
LDFLAGS[unexport] = "1"
AS[unexport] = "1"
LD[unexport] = "1"

do_compile() {
    oe_runmake distclean
    oe_runmake bl2 bl31 dummytool PLAT=${PLATFORM} ${ATFW_OPT}
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
    install -m 0644 ${S}/tools/dummy_create/bootparam_sa0.srec ${DEPLOYDIR}/bootparam_sa0.srec
    install -m 0644 ${S}/tools/dummy_create/cert_header_sa6.srec ${DEPLOYDIR}/cert_header_sa6.srec
}
addtask deploy before do_build after do_compile
