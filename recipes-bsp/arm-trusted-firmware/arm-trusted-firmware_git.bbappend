require include/provisioning.inc

SEC_MODULE_LIBDIR = "${HOME}/private/secure/lib/"

SRC_URI_append += " \
	${@oe.utils.conditional("SECURE_BOOT", "1", "file://0001-rzg-add-support-SECURE-BOOT-for-RZ-G2-Platform.patch", "",d)} \
	file://0100-add-TQMaRZG2x-modules.patch \
	file://0101-tqmarzg2h-add-fix-from-Renesas-for-booting-from-emmc.patch \
	file://0102-tqmarzg2x-add-fix-from-Renesas-for-accessing-SPI-NOR.patch \
	file://0103-tqmarzg2x-set-all-GPIOs-to-input-without-PU-PD.patch \
"

ATFW_OPT_r8a774b1 = "LSI=G2N RCAR_AVS_SETTING_ENABLE=0 RZG_TQMARZG2N_B=1 PMIC_ROHM_BD9571=0 RCAR_SYSTEM_SUSPEND=0 SPD="none""
ATFW_OPT_r8a774a1 = "LSI=G2M RCAR_DRAM_SPLIT=2 RCAR_AVS_SETTING_ENABLE=0 RZG_TQMARZG2M_E=1 PMIC_ROHM_BD9571=0 RCAR_SYSTEM_SUSPEND=0 RCAR_SECURE_BOOT=0 SPD="none""
ATFW_OPT_r8a774e1 = "LSI=G2H RCAR_DRAM_SPLIT=2 RCAR_DRAM_LPDDR4_MEMCONF=1 RCAR_DRAM_CHANNEL=5 RCAR_AVS_SETTING_ENABLE=0 RZG_TQMARZG2H_C=1 PMIC_ROHM_BD9571=0 RCAR_SYSTEM_SUSPEND=0 SPD="none""

ATFW_OPT_append += " \
	${@oe.utils.conditional("SECURE_BOOT", "1", "SPD=\"opteed\"", "",d)} \
	${@oe.utils.conditional("SECURE_BOOT", "1", "RZG2_SECURE_BOOT=1 SEC_MODULE_LIBDIR=${SEC_MODULE_LIBDIR}", "",d)} \
"

do_compile() {
	for d in ${BOOTTYPE}; do
		case $d in
		spi_nor)
			bootsrc="RCAR_SA6_TYPE=0"
			;;
		emmc)
			bootsrc="RCAR_SA6_TYPE=1"
			;;
		esac

		rm -f ${S}/tools/renesas/rzg_layout_create/sa6.o
		echo "compiling for " ${d} " boot"
		oe_runmake distclean
		oe_runmake bl2 bl31 rzg PLAT=${PLATFORM} ${ATFW_OPT} ${bootsrc}

		if [ "$d" = "spi_nor" ] ; then
			mv ${S}/tools/renesas/rzg_layout_create/cert_header_sa6.srec ${S}/tools/renesas/rzg_layout_create/cert_header_sa6_${d}.srec
		fi

		if [ "$d" = "emmc" ] ; then
			mv ${S}/tools/renesas/rzg_layout_create/cert_header_sa6.bin ${S}/tools/renesas/rzg_layout_create/cert_header_sa6_${d}.bin
		fi
	done

	if [ 1 -eq ${SECURE_BOOT} ] ; then
		oe_runmake sec_module PLAT=${PLATFORM} ${ATFW_OPT}
	fi

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
    install -m 0644 ${S}/tools/renesas/rzg_layout_create/bootparam_sa0.srec ${DEPLOYDIR}/bootparam_sa0.srec
    install -m 0644 ${S}/tools/renesas/rzg_layout_create/bootparam_sa0.bin ${DEPLOYDIR}/bootparam_sa0.bin
    install -m 0644 ${S}/tools/renesas/rzg_layout_create/cert_header_sa6_spi_nor.srec ${DEPLOYDIR}/cert_header_sa6_spi_nor.srec
    install -m 0644 ${S}/tools/renesas/rzg_layout_create/cert_header_sa6_emmc.bin ${DEPLOYDIR}/cert_header_sa6_emmc.bin

	if [ 1 -eq ${SECURE_BOOT} ] ; then
		install -m 0644 ${S}/build/${PLATFORM}/release/sec_module.srec ${DEPLOYDIR}/sec_module_${MACHINE}.srec
		do_deploy_provisioning 0x44000000 "${DEPLOYDIR}/bl31-${MACHINE}.srec"

		ENC_KEY_BIN="${ENCRYPTED_KEYRING}"
		objcopy -I binary -O srec --adjust-vma=0x440FE000 --srec-forceS3 "${ENC_KEY_BIN}" "${DEPLOYDIR}/${ENC_KEY_BIN##*/}.srec"
	fi
}
addtask deploy before do_build after do_compile
