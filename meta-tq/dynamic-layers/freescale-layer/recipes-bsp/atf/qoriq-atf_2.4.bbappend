SRC_URI:tqmlsx = "${TQ_GIT_BASEURL}/atf.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"
SRCBRANCH:tqmlsx = "TQM-v2.4"
SRCREV:tqmlsx = "f8932b6b3ec737684fd4d0c7a0730b6c8adcd395"

RCW_FOLDER:tqmls1012al = "tqmls1012al"
RCW_SUFFIX:tqmls1012al = "${@bb.utils.contains('DISTRO_FEATURES', 'secure', '_sben.bin', '.bin', d)}"
PLATFORM:tqmls1012al = "tqmls1012al"
PLATFORM_ADDITIONAL_TARGET:tqmls1012al = "tqmls1012al_1gb"

RCW_FOLDER:tqmls1028a = "tqmls1028a"
RCW_SUFFIX:tqmls1028a = "${@bb.utils.contains('DISTRO_FEATURES', 'secure', '_sben.bin', '.bin', d)}"
PLATFORM:tqmls1028a = "tqmls1028a_1gb"
PLATFORM_ADDITIONAL_TARGET:tqmls1028a = "tqmls1028a_4gb"

RCW_FOLDER:tqmls1043a = "tqmls1043a"
PLATFORM:tqmls1043a = "tqmls1043a"
PLATFORM_ADDITIONAL_TARGET:tqmls1043a = "tqmls1043a_2gb"

RCW_FOLDER:tqmls1046a = "tqmls1046a"
PLATFORM:tqmls1046a = "tqmls1046a"
PLATFORM_ADDITIONAL_TARGET:tqmls1046a = "tqmls1046a_8gb"

RCW_FOLDER:tqmlx2160a = "tqmlx2160a"
RCW_SUFFIX:tqmlx2160a = "${@bb.utils.contains('DISTRO_FEATURES', 'secure', '_sben.bin', '.bin', d)}"
PLATFORM:tqmlx2160a = "tqmlx2160a"
PLATFORM_ADDITIONAL_TARGET:tqmlx2160a = "tqmlx2160a_16gb"

ATF_RCW_VARIANTS ??= ""
DDR_ECC_EN ?= "yes"

EXTRA_OEMAKE += "DDR_ECC_EN=${DDR_ECC_EN}"

do_compile:prepend () {
    rm -f ${S}/*.pbl ${S}/*.bin
    rm -rf ${S}/atf-variants
}

do_compile:append () {
    for plat in ${PLATFORM} ${PLATFORM_ADDITIONAL_TARGET}; do
        for rcw_file in ${ATF_RCW_VARIANTS}; do
            case $rcw_file in
                *_sd)
                rcw_bootmode="sd"
                ;;
                *_qspi)
                rcw_bootmode="qspi"
                ;;
                *)
                rcw_bootmode=""
                ;;
            esac

            for bootmode in ${BOOTTYPE}; do
                if [ -n "${rcw_bootmode}" ] && [ "${rcw_bootmode}" != "${bootmode}" ]; then
	            continue
                fi
                make V=1 realclean
                oe_runmake V=1 pbl PLAT=${plat} BOOT_MODE=${bootmode} RCW=${DEPLOY_DIR_IMAGE}/rcw/${RCW_FOLDER}/${rcw_file}${RCW_SUFFIX}
                install -d ${S}/atf-variants
                cp build/${plat}/release/bl2_${bootmode}${SECURE_EXTENTION}.pbl \
                    ${S}/atf-variants/bl2_${bootmode}_${plat}${SECURE_EXTENTION}_$(basename ${rcw_file}).pbl
            done
        done
    done
}

do_deploy:append () {
    if [ -e ${S}/atf-variants/ ]; then
        install -d ${DEPLOYDIR}/atf/variants
        cp ${S}/atf-variants/*.pbl ${DEPLOYDIR}/atf/variants/
    fi
}
