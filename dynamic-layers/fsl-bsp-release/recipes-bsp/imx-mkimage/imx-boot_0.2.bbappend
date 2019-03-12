
# declare bootstream to be built. See target names in soc.mak provided by
# imx-mkimage for target processor
IMXBOOT_TARGETS_tqma8qx = "flash"
IMXBOOT_TARGETS_tqma8qxs-mb-smarc-2 = "flash"

IMXBOOT_TARGETS_mx8qxpa0 = "flash_a0 flash_dcd_a0"
IMXBOOT_TARGETS_mx8qxp = "flash"

# DCD_NAME is optional - provide RAM DCD name if RAM bringup not implemented in SCFW

# HACK that imx-mkimage runs
# The soc.mak file has hardcoded u-boot devicetree names. For the perelease we
# use a HACK and rename the our devictree equal to NXP EVK devicetree.
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

    if [ "${SOC_TARGET}" = "iMX8QX" ]; then
        case ${MACHINE} in
            tqma8qxs* |\
            tqma8dx* | \
            tqma8qx*)
                ln -sf ${BOOT_CONFIG_MACHINE}-${IMAGE_IMXBOOT_TARGET} ${SOC_TARGET}-b0-bootstream.bin
                ;;
            *)
                break
                ;;
        esac
    fi

    if [ "${SOC_TARGET}" = "iMX8M" ]; then
       case ${MACHINE} in
            tqma8mx* |\
            tqma8mq*)
                ln -sf ${BOOT_CONFIG_MACHINE}-${IMAGE_IMXBOOT_TARGET} ${SOC_TARGET}-bootstream.bin
                ;;
            *)
                break
                ;;
        esac
    fi

    cd -
}
