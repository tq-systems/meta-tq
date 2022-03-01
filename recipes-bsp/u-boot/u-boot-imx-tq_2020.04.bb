
DESCRIPTION = "u-boot for TQ-Systems GmbH NXP i.MX8 based modules"

require u-boot-imx-tq-common_${PV}.inc
require recipes-bsp/u-boot/u-boot.inc
require u-boot-tq.inc

# inherit pythonnative

DEPENDS:append = " dtc-native bc-native python3-native"

BOOT_TOOLS = "imx-boot-tools"

do_deploy:append:mx8m-generic-bsp () {
    # Deploy the u-boot-nodtb.bin and ${UBOOT_DTB_NAME} for mkimage to generate boot binary
    if [ -n "${UBOOT_CONFIG}" ]
    then
        for config in ${UBOOT_MACHINE}; do
            i=$(expr $i + 1);
            for type in ${UBOOT_CONFIG}; do
                j=$(expr $j + 1);
                if [ $j -eq $i ]
                then
                    install -d ${DEPLOYDIR}/${BOOT_TOOLS}
                    for dtb in ${UBOOT_DTB_NAME}; do
                        install -m 0777 ${B}/${config}/arch/arm/dts/${dtb}  ${DEPLOYDIR}/${BOOT_TOOLS}/${dtb}-${MACHINE}-${type}
                    done
                    install -m 0777 ${B}/${config}/u-boot-nodtb.bin  ${DEPLOYDIR}/${BOOT_TOOLS}/u-boot-nodtb.bin-${MACHINE}-${type}
                fi
            done
            unset  j
        done
        unset  i
    fi
}

UBOOT_NAME:mx8-generic-bsp = "u-boot-${MACHINE}.bin-${UBOOT_CONFIG}"
