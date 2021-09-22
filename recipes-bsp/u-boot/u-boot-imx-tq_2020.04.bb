
DESCRIPTION = "u-boot for TQ-Group NXP i.MX8 based modules"

require u-boot-imx-tq-common_${PV}.inc
# require ${@bb.utils.contains("BBFILE_COLLECTIONS", "freescale-layer", 'u-boot-tq-imx-conditional.inc', '', d)}
require recipes-bsp/u-boot/u-boot.inc

PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

UBOOT_LOCALVERSION = "-${GITPKGVTAG}"

# inherit pythonnative

PROVIDES += "u-boot"
DEPENDS_append = " dtc-native bc-native python3-native"

BOOT_TOOLS = "imx-boot-tools"

do_deploy_append_mx8m () {
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

PACKAGE_ARCH = "${MACHINE_ARCH}"

UBOOT_NAME_mx8 = "u-boot-${MACHINE}.bin-${UBOOT_CONFIG}"
