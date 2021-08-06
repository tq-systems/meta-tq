FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-Add-support-for-new-TQMaRZG2x-modules.patch"

ATFW_OPT_r8a774b1 = "LSI=G2N RCAR_AVS_SETTING_ENABLE=0 RZG_TQMARZG2N_B=1 PMIC_ROHM_BD9571=0 RCAR_SYSTEM_SUSPEND=0 SPD="none""
ATFW_OPT_r8a774a1 = "LSI=G2M RCAR_DRAM_SPLIT=2 RCAR_AVS_SETTING_ENABLE=0 RZG_TQMARZG2M_E=1 PMIC_ROHM_BD9571=0 RCAR_SYSTEM_SUSPEND=0 RCAR_SECURE_BOOT=0 SPD="none""
ATFW_OPT_r8a774e1 = "LSI=G2H RCAR_DRAM_SPLIT=2 RCAR_DRAM_LPDDR4_MEMCONF=1 RCAR_DRAM_CHANNEL=5 RCAR_AVS_SETTING_ENABLE=0 RZG_TQMARZG2H_C=1 PMIC_ROHM_BD9571=0 RCAR_SYSTEM_SUSPEND=0 SPD="none""


do_compile() {
	for d in ${BOOTTYPE}; do
		case $d in
		spi_nor)
			xyz="RCAR_SA6_TYPE=0"
			;;
		emmc)
			xyz="RCAR_SA6_TYPE=1"
			;;
		esac

		oe_runmake realclean
		oe_runmake bl2 bl31 dummytool PLAT=${PLATFORM} ${ATFW_OPT} ${xyz}

		cp ${S}/tools/dummy_create/cert_header_sa6.srec ${DEPLOY_DIR_IMAGE}/cert_header_sa6_${d}.srec
		cp ${S}/tools/dummy_create/cert_header_sa6.bin ${DEPLOY_DIR_IMAGE}/cert_header_sa6_${d}.bin
		
	done
}

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
#   install -m 0644 ${S}/tools/dummy_create/cert_header_sa6.srec ${DEPLOYDIR}/cert_header_sa6.srec
}
addtask deploy before do_build after do_compile
