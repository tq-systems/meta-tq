# DCD_NAME is optional - provide RAM DCD name if RAM bringup not implemented in SCFW

DEPENDS += " \
    virtual/bootloader \
"

##
# create link to real bootstream image in deploy dir, needed for wic image
##
do_deploy_append() {
    cd ${DEPLOYDIR}
    ln -sf ${BOOT_CONFIG_MACHINE}-${IMAGE_IMXBOOT_TARGET} ${BOOT_NAME}
    cd -
}
