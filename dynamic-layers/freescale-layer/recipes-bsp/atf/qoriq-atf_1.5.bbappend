SRC_URI = "${TQ_GIT_BASEURL}/atf.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
           file://0001-common-runtime_svc.c-HACK-workaround-GCC11-out-of-bo.patch \
           "
SRCBRANCH = "TQMLS-Integration"
SRCREV = "0bed034f4ed9ed9bc572e739ff5913b216df54d5"

STATIC_PBL = "no"

PLATFORM:tqmls1012al = "tqmls1012al"
PLATFORM:tqmls1012al-1gb = "tqmls1012al_1gb"
RCW_FOLDER:tqmls1012al-1gb = "tqmls1012al_1gb"
RCW_FOLDER:tqmls1012al = "${PLATFORM:tqmls1012al}"

# fix override by qoriq-atf_1.5 from freescale layer
rcw_ls1012a = ""

PLATFORM:tqmls1028a = "tqmls1028a"
RCW_FOLDER:tqmls1028a = "${PLATFORM:tqmls1028a}"
STATIC_PBL:tqmls1028a = "yes"

PLATFORM:tqmlx2160a = "tqmlx2160a"
RCW_FOLDER:tqmlx2160a = "${PLATFORM:tqmlx2160a}"

export STATIC_PBL

do_compile:append:tqmlx2160a () {
    for rcw_file in $(ls -1 ${DEPLOY_DIR_IMAGE}/rcw/${PLATFORM}/${MACHINE}); do
	for d in ${btype}; do
		oe_runmake distclean
		oe_runmake V=1 -C ${S} pbl PLAT=${PLATFORM} BOOT_MODE=${d} \
			RCW=${DEPLOY_DIR_IMAGE}/rcw/${PLATFORM}/${MACHINE}/${rcw_file} \
			BL33=${bl33} ${bl32opt} ${spdopt} ${secureopt} ${fuseopt} ${otaopt}

		cp -r ${S}/build/${PLATFORM}/release/bl2_${d}*.pbl ${S}/bl2_${d}_$(basename ${rcw_file} .bin).pbl
         done
    done
}

do_compile:append:tqmls1028a () {
    for rcw_file in ${ATF_RCW_VARIANTS}; do
        case $rcw_file in
            *sd*)
            bootmode="sd";
            ;;
            *nor*)
            bootmode="flexspi_nor"
            ;;
        esac

        oe_runmake distclean
        oe_runmake V=1 -C ${S} pbl PLAT=${PLATFORM} BOOT_MODE=${bootmode} RCW=${DEPLOY_DIR_IMAGE}/rcw/${PLATFORM}/${rcw_file} ${bl32opt} ${spdopt} ${secureopt} ${fuseopt} ${otaopt}
	install -d ${S}/atf-variants
        cp ${S}/build/${PLATFORM}/release/bl2_${bootmode}.pbl ${S}/atf-variants/bl2_$(basename ${rcw_file} .bin).pbl
    done
}

do_deploy:append:tqmlx2160a () {
    install -d ${DEPLOYDIR}/atf/variants
    for pbl_file in $(ls ${S}/bl2_*_rcw*.pbl); do
        cp -r ${pbl_file} ${DEPLOYDIR}/atf/variants/
    done
}

do_deploy:append:tqmls1028a () {
    install -d ${DEPLOYDIR}/atf/variants
    for pbl_file in ${ATF_RCW_VARIANTS}; do
        cp ${S}/atf-variants/bl2_$(basename ${pbl_file} .bin).pbl ${DEPLOYDIR}/atf/variants/
    done
}
