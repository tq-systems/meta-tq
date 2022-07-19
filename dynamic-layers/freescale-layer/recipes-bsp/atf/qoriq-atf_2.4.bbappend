STATIC_PBL = "no"

OVERRIDES:prepend:tqmls1028a = "tqmls-atf-common:"
OVERRIDES:prepend:tqmls1012al = "tqmls-atf-common:"
OVERRIDES:prepend:tqmlx2160a = "tqmls-atf-common:"

SRC_URI:tqmls-atf-common = "${TQ_GIT_BASEURL}/atf.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"
SRCBRANCH:tqmls-atf-common = "TQM-v2.4"
SRCREV:tqmls-atf-common = "ea967df114f4458389133e3b5b0f331c6c35dd13"


RCW_FOLDER:tqmls1012al = "tqmls1012al"
RCW_SUFFIX:tqmls1012al = "${@bb.utils.contains('DISTRO_FEATURES', 'secure', '_sben.bin', '.bin', d)}"
PLATFORM:tqmls1012al = "tqmls1012al"
PLATFORM_ADDITIONAL_TARGET:tqmls1012al = "tqmls1012al_1gb"

RCW_FOLDER:tqmls1028a = "tqmls1028a"
RCW_SUFFIX:tqmls1028a = "${@bb.utils.contains('DISTRO_FEATURES', 'secure', '_sben.bin', '.bin', d)}"
PLATFORM:tqmls1028a = "tqmls1028a_1gb"
PLATFORM_ADDITIONAL_TARGET:tqmls1028a = "tqmls1028a_4gb"
STATIC_PBL:tqmls1028a = "yes"

RCW_FOLDER:tqmlx2160a = "tqmlx2160a"
RCW_SUFFIX:tqmlx2160a = "${@bb.utils.contains('DISTRO_FEATURES', 'secure', '_sben.bin', '.bin', d)}"
PLATFORM:tqmlx2160a = "tqmlx2160a"

export STATIC_PBL

do_compile:append:tqmls1028a () {
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

do_compile:append:tqmlx2160a () {
    install -d ${S}/atf-variants
    for rcw_file in ${ATF_RCW_VARIANTS}; do
        for bootmode in ${BOOTTYPE}; do
            oe_runmake distclean
            oe_runmake -C ${S} pbl PLAT=${PLATFORM} BOOT_MODE=${bootmode} RCW=${DEPLOY_DIR_IMAGE}/rcw/${RCW_FOLDER}/${rcw_file} ${bl32opt} ${spdopt} ${secureopt} ${fuseopt} ${otaopt}
            cp ${S}/build/${PLATFORM}/release/bl2_${bootmode}.pbl ${S}/atf-variants/bl2_${bootmode}_$(basename ${rcw_file} .bin).pbl
        done
    done
}

do_deploy:append () {
    if [ -e ${S}/atf-variants/ ]; then
        install -d ${DEPLOYDIR}/atf/variants
        install ${S}/atf-variants/*.pbl ${DEPLOYDIR}/atf/variants/
    fi
}

COMPATIBLE_MACHINE = "^("
COMPATIBLE_MACHINE .= "|tqmls1012al"
COMPATIBLE_MACHINE .= "|tqmls1028a"
COMPATIBLE_MACHINE .= "|tqmlx2160a"
COMPATIBLE_MACHINE .= ")$"
