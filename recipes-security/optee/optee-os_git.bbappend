SRC_URI = "git://git.ti.com/optee/ti-optee-os.git;branch=${BRANCH} \
           file://0001-allow-setting-sysroot-for-libgcc-lookup.patch \
"

BRANCH = "ti-optee-os"
SRCREV = "f414104d552c8ab96ccc3c4182c395c20e3a0a34"

do_compile() {
    export TI_SECURE_DEV_PKG=${TI_SECURE_DEV_PKG}
    oe_runmake all PLATFORM=${OPTEEMACHINE} PLATFORM_FLAVOR=${OPTEEFLAVOR}
    ( cd out/arm-plat-${OPTEEOUTPUTMACHINE}/core/; \
        ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh tee.bin tee.bin.signed; \
        normfl=`echo ${OPTEEFLAVOR} | tr "_" "-"`
        mv tee.bin.signed ${B}/$normfl.optee; \
    )

    if [ "${OPTEEPAGER}" = "y" ]; then
#        oe_runmake clean PLATFORM=${OPTEEMACHINE} PLATFORM_FLAVOR=${OPTEEFLAVOR}
        rm -rf out/
        oe_runmake all PLATFORM=${OPTEEMACHINE} PLATFORM_FLAVOR=${OPTEEFLAVOR} CFG_WITH_PAGER=y
        ( cd out/arm-plat-${OPTEEOUTPUTMACHINE}/core/; \
            ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh tee.bin tee.bin.signed; \
            normfl=`echo ${OPTEEFLAVOR} | tr "_" "-"`
            mv tee.bin.signed ${B}/$normfl-pager.optee; \
        )
    fi
}

do_compile_aarch64() {
    oe_runmake all PLATFORM=${OPTEEMACHINE} PLATFORM_FLAVOR=${OPTEEFLAVOR}
    ( cd out/arm-plat-${OPTEEOUTPUTMACHINE}/core/; \
        cp tee-pager_v2.bin ${B}/bl32.bin; \
        cp tee.elf ${B}/bl32.elf; \
    )
}

do_compile_am65xx-hs-evm() {
    export TI_SECURE_DEV_PKG=${TI_SECURE_DEV_PKG}
    oe_runmake all PLATFORM=${OPTEEMACHINE} PLATFORM_FLAVOR=${OPTEEFLAVOR}
    ( cd out/arm-plat-${OPTEEOUTPUTMACHINE}/core/; \
        ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh tee-pager_v2.bin tee-pager.bin.signed; \
        mv tee-pager.bin.signed ${B}/bl32.bin; \
        cp tee.elf ${B}/bl32.elf; \
    )
}

do_compile_j7-hs-evm() {
    export TI_SECURE_DEV_PKG=${TI_SECURE_DEV_PKG}
    oe_runmake all PLATFORM=${OPTEEMACHINE} PLATFORM_FLAVOR=${OPTEEFLAVOR}
    ( cd out/arm-plat-${OPTEEOUTPUTMACHINE}/core/; \
        ${TI_SECURE_DEV_PKG}/scripts/secure-binary-image.sh tee-pager_v2.bin tee-pager.bin.signed; \
        mv tee-pager.bin.signed ${B}/bl32.bin; \
        cp tee.elf ${B}/bl32.elf; \
    )
}

do_install() {
    #install core on boot directory
    install -d ${D}/boot
    install -m 644 ${B}/*.optee ${D}/boot || true
    install -m 644 ${B}/bl32.bin ${D}/boot || true
    install -m 644 ${B}/bl32.elf ${D}/boot || true

    #install TA devkit
    install -d ${D}/usr/include/optee/export-user_ta/
    for f in  ${B}/out/arm-plat-${OPTEEOUTPUTMACHINE}/export-ta_${OPTEE_ARCH}/* ; do
        cp -aR $f ${D}/usr/include/optee/export-user_ta/
    done
}

do_deploy() {
    install -d ${DEPLOYDIR}
    install -m 644 ${B}/*.optee ${DEPLOYDIR} || true
    install -m 644 ${B}/bl32.bin ${DEPLOYDIR} || true
    install -m 644 ${B}/bl32.elf ${DEPLOYDIR} || true
}

FILES_${PN} = "/boot"
SYSROOT_DIRS += "/boot"

# This is needed for bl32.elf
INSANE_SKIP_${PN} += "textrel"
