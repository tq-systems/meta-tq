# DCD_NAME is optional - provide RAM DCD name if RAM bringup not implemented in SCFW

DEPENDS += " \
    virtual/bootloader \
"

# HACK that imx-mkimage runs
# The soc.mak file has hardcoded u-boot devicetree names. For the perelease we
# use a HACK and rename our devictree equal to NXP EVK devicetree.
# https://git.phytec.de/meta-phytec / 247f8c6def52ea414ca1697f32e62427cad87056

do_compile_prepend () {
	if [ "${SOC_TARGET}" = "iMX8M" ]; then
		cp ${DEPLOY_DIR_IMAGE}/${BOOT_TOOLS}/${UBOOT_DTB_NAME} ${S}/${SOC_TARGET}/fsl-imx8mq-evk.dtb
	fi
}

##
# create link to real bootstream image in deploy dir, needed for wic image
##
do_deploy_append() {
    cd ${DEPLOYDIR}
    ln -sf ${BOOT_CONFIG_MACHINE}-${IMAGE_IMXBOOT_TARGET} ${BOOT_NAME}
    cd -
}
