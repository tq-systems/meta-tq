SRC_URI_tqmls-atf-common = "${TQ_GIT_BASEURL}/atf.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"
SRCBRANCH_tqmls-atf-common = "TQMLS-Integration"
SRCREV_tqmls-atf-common = "0bed034f4ed9ed9bc572e739ff5913b216df54d5"

STATIC_PBL = "no"

OVERRIDES_prepend_tqmls1012al = "tqmls-atf-common:"
PLATFORM_tqmls1012al = "tqmls1012al"

OVERRIDES_prepend_tqmls1028a = "tqmls-atf-common:"
PLATFORM_tqmls1028a = "tqmls1028a"
STATIC_PBL_tqmls1028a = "yes"

OVERRIDES_prepend_tqmlx2160a = "tqmls-atf-common:"
PLATFORM_tqmlx2160a = "tqmlx2160a"

export STATIC_PBL

do_compile_append_tqmlx2160a () {
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

do_deploy_append_tqmlx2160a () {
    install -d ${DEPLOYDIR}/atf/variants
    for pbl_file in $(ls ${S}/bl2_*_rcw*.pbl); do
        cp -r ${pbl_file} ${DEPLOYDIR}/atf/variants/
    done
}
