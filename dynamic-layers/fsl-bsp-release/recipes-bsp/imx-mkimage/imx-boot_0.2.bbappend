
# declare bootstream to be built. See target names in soc.mak provided by
# imx-mkimage for target processor
IMXBOOT_TARGETS_tqma8qx-mba8qx = "flash"
IMXBOOT_TARGETS_tqma8qxs-mb-smarc-2 = "flash"

IMXBOOT_TARGETS_mx8qxpa0 = "flash_a0 flash_dcd_a0"
IMXBOOT_TARGETS_mx8qxp = "flash flash_dcd"

# DCD_NAME is optional - provide RAM DCD name if RAM bringup not implemented in SCFW

##
# create link to real bootstream image in deply dir, needed for wic image
##
do_deploy_append() {
    cd ${DEPLOYDIR}
    if [ "${SOC_TARGET}" = "iMX8QX" ]; then
        if [ "${MACHINE}" = "tqma8qx-mba8qx" ]; then
            ln -sf ${BOOT_CONFIG_MACHINE}-${IMAGE_IMXBOOT_TARGET} ${SOC_TARGET}-b0-bootstream.bin
        fi
        if [ "${MACHINE}" = "tqma8qxs-mb-smarc-2" ]; then
            ln -sf ${BOOT_CONFIG_MACHINE}-${IMAGE_IMXBOOT_TARGET} ${SOC_TARGET}-b0-bootstream.bin
        fi
    fi
    cd -
}
