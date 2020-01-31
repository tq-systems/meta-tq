require u-boot-tq-common_${PV}.inc
require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "u-boot for TQ-Group Freescale LS1012A based modules"

PROVIDES += "virtual/bootloader"
PROVIDES += "u-boot"

DEPENDS += "swap-file-endianess-native tcl-native"
DEPENDS_append = " dtc-native bc-native"

PACKAGE_ARCH = "${MACHINE_ARCH}"

do_compile_append () {
mkdir -p ${DEPLOYDIR}

 unset i j
    if [ "x${UBOOT_CONFIG}" != "x" ]; then
        for config in ${UBOOT_MACHINE}; do
            i=`expr $i + 1`;
            for type in ${UBOOT_CONFIG}; do
                j=`expr $j + 1`;
                if [ $j -eq $i ]; then
                    case "${config}" in
                        *qspi*)
                            tclsh ${STAGING_BINDIR_NATIVE}/byteswap.tcl ${config}/u-boot.bin ${config}/u-boot.swap.bin 8
                            cp ${config}/u-boot.swap.bin ${config}/u-boot-${type}.${UBOOT_SUFFIX}

                            tclsh ${STAGING_BINDIR_NATIVE}/byteswap.tcl ${config}/u-boot-pbl.bin ${config}/u-boot-pbl.swap.bin 8
                            cp ${config}/u-boot-pbl.swap.bin ${config}/u-boot-pbl-${type}.${UBOOT_SUFFIX}
                            cp -f ${config}/u-boot-pbl-${type}.${UBOOT_SUFFIX} ${DEPLOYDIR}/

                            tclsh ${STAGING_BINDIR_NATIVE}/byteswap.tcl ${config}/pbl.bin ${config}/pbl.swap.bin 8
                            cp ${config}/pbl.swap.bin ${config}/pbl-${type}.${UBOOT_SUFFIX}
                            install -d ${DEPLOYDIR}/rcw
                            cp -f ${config}/pbl-${type}.${UBOOT_SUFFIX} ${DEPLOYDIR}/rcw/
                    esac
                fi
            done
            unset j
        done
        unset i
    fi
}

PACKAGES += "${PN}-images"
FILES_${PN}-images += "/boot"
