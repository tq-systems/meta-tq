COMPATIBLE_MACHINE = "(tqmls1012al|tqmls1028a)"

STATIC_PBL = "no"

OVERRIDES_prepend_tqmls1028a = "tqmls-atf-common:"
OVERRIDES_prepend_tqmls1012al = "tqmls-atf-common:"

SRC_URI_tqmls-atf-common = "${TQ_GIT_BASEURL}/atf.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"
SRCBRANCH_tqmls-atf-common = "TQM-v2.4"
SRCREV_tqmls-atf-common = "ea967df114f4458389133e3b5b0f331c6c35dd13"

RCW_FOLDER_tqmls1012al = "tqmls1012al"
RCW_SUFFIX_tqmls1012al = "${@bb.utils.contains('DISTRO_FEATURES', 'secure', '_sben.bin', '.bin', d)}"
PLATFORM_tqmls1012al = "tqmls1012al"
PLATFORM_ADDITIONAL_TARGET_tqmls1012al = "tqmls1012al_1gb"

RCW_FOLDER_tqmls1028a = "tqmls1028a"
RCW_SUFFIX_tqmls1028a = "${@bb.utils.contains('DISTRO_FEATURES', 'secure', '_sben.bin', '.bin', d)}"
PLATFORM_tqmls1028a = "tqmls1028a_1gb"
PLATFORM_ADDITIONAL_TARGET_tqmls1028a = "tqmls1028a_4gb"
STATIC_PBL_tqmls1028a = "yes"

export STATIC_PBL

do_compile_append_tqmls1028a () {
    for plat in ${PLATFORM} ${PLATFORM_ADDITIONAL_TARGET}; do
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
                oe_runmake -C ${S} pbl PLAT=${plat} BOOT_MODE=${bootmode} RCW=${DEPLOY_DIR_IMAGE}/rcw/${RCW_FOLDER}/${rcw_file} ${bl32opt} ${spdopt} ${secureopt} ${fuseopt} ${otaopt}
                install -d ${S}/atf-variants
                cp ${S}/build/${plat}/release/bl2_${bootmode}.pbl ${S}/atf-variants/bl2_${plat}_$(basename ${rcw_file} .bin).pbl
        done
    done
}

do_deploy_append () {
    if [ -e ${S}/atf-variants/ ]; then
        install -d ${DEPLOYDIR}/atf/variants
        install ${S}/atf-variants/*.pbl ${DEPLOYDIR}/atf/variants/
    fi
}
