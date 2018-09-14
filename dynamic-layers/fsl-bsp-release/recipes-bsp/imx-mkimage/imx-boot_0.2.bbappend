### IMXBOOT_TARGETS_tqma8xx-mba8xx ?= "flash_ddr3_dcd"

do_deploy_append() {
    if [ "${SOC_TARGET}" = "iMX8QX" ]; then
        if [ "${MACHINE}" = "tqma8qxa0-mba8qx" ]; then
            install -m 0644 ${S}/${SOC_TARGET}/${DCD_NAME} ${DEPLOYDIR}/${DEPLOYDIR_IMXBOOT}
        fi
    fi

    cd ${DEPLOYDIR}
    if [ "${SOC_TARGET}" = "iMX8QX" ]; then
        if [ "${MACHINE}" = "tqma8qxa0-mba8qx" ]; then
            ln -sf ${BOOT_CONFIG_MACHINE}-${IMAGE_IMXBOOT_TARGET} ${SOC_TARGET}-a0-bootstream.bin
        fi
        if [ "${MACHINE}" = "tqma8qx-mba8qx" ]; then
            ln -sf ${BOOT_CONFIG_MACHINE}-${IMAGE_IMXBOOT_TARGET} ${SOC_TARGET}-b0-bootstream.bin
        fi
    fi
    cd -
}

