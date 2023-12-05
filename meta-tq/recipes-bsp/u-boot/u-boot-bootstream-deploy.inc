# Copyright (C) 2022-2023 TQ-Systems GmbH

do_deploy_uboot_dtb () {
    # Deploy the u-boot-nodtb.bin and ${UBOOT_DTB_NAME} for mkimage to generate boot binary
    if [ -n "${UBOOT_CONFIG}" ]
    then
        for config in ${UBOOT_MACHINE}; do
            i=$(expr $i + 1);
            for type in ${UBOOT_CONFIG}; do
                j=$(expr $j + 1);
                if [ $j -eq $i ]
                then
                    for dtb in ${UBOOT_DTB_NAME}; do
                        install -m 0644 ${B}/${config}/arch/arm/dts/${dtb}  ${DEPLOYDIR}/u-boot-${dtb}-${MACHINE}-${type}
                    done
                    install -m 0644 ${B}/${config}/u-boot-nodtb.bin  ${DEPLOYDIR}/u-boot-nodtb.bin-${MACHINE}-${type}
                fi
            done
            unset  j
        done
        unset  i
    fi
}
