SRC_URI:tqmlsx = "${TQ_GIT_BASEURL}/atf.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

SRCBRANCH:tqmlsx = "TQM-v2.4"
SRCREV:tqmlsx = "9b7d9e274c2f956064f1ba4341d28633da9c0db0"

PLATFORM_ADDTIONAL_TARGETS_EXTRA = ""

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
    for d in ${BOOTTYPE}; do
        case $d in
        nor)
            rcwimg="${RCWNOR}${RCW_SUFFIX}"
            ;;
        nand)
            rcwimg="${RCWNAND}${RCW_SUFFIX}"
            ;;
        qspi)
            rcwimg="${RCWQSPI}${RCW_SUFFIX}"
            ;;
        auto)
            rcwimg="${RCWAUTO}${RCW_SUFFIX}"
            ;;
        sd)
            rcwimg="${RCWSD}${RCW_SUFFIX}"
            ;;
        emmc)
            rcwimg="${RCWEMMC}${RCW_SUFFIX}"
            ;;
        flexspi_nor)
            rcwimg="${RCWXSPI}${RCW_SUFFIX}"
            ;;
        esac

	if [ -f ${DEPLOY_DIR_IMAGE}/rcw/${RCW_FOLDER}/$rcwimg ]; then
            for plat in ${PLATFORM_ADDITIONAL_TARGETS_EXTRA}; do
                make V=1 realclean
                oe_runmake V=1 all fip pbl PLAT=$plat BOOT_MODE=${d} RCW=${DEPLOY_DIR_IMAGE}/rcw/${RCW_FOLDER}/${rcwimg} BL33=${UBOOT_BINARY}
                cp build/$plat/release/bl2_${d}${SECURE_EXTENTION}.pbl bl2_${d}${SECURE_EXTENTION}_$plat.pbl
                cp build/$plat/release/fip.bin fip_uboot${SECURE_EXTENTION}_$plat.bin
                if [ -e build/$plat/release/fuse_fip.bin ]; then
                    cp build/$plat/release/fuse_fip.bin fuse_fip_$plat.bin
                fi
            done
        fi
        rcwimg=""
    done

    for plat in ${PLATFORM} ${PLATFORM_ADDITIONAL_TARGET} ${PLATFORM_ADDITIONAL_TARGETS_EXTRA}; do
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
