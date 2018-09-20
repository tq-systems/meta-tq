
# declare bootstream to be built. See target names in soc.mak provided by
# imx-mkimage for target processor
IMXBOOT_TARGETS_tqma8qxa0-mba8qx = "flash_a0"
IMXBOOT_TARGETS_tqma8qx-mba8qx = "flash"

# optionally provide RAM DCD name if RAM bringup not implemented in SCFW
DCD_NAME_tqma8qxa0-mba8qx = "tqma8xx_ddr3_dcd_933MHz_ecc.cfg.tmp"
DCD_NAME_tqma8qx-mba8qx = "tqma8xx_ddr3_dcd_933MHz_ecc.cfg.tmp"


do_deploy_append() {
#    if [ "${SOC_TARGET}" = "iMX8QX" ]; then
#        if [ "${MACHINE}" = "tqma8qxa0-mba8qx" ]; then
#            install -m 0644 ${S}/${SOC_TARGET}/${DCD_NAME} ${DEPLOYDIR}/${DEPLOYDIR_IMXBOOT}
#        fi
#    fi

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

