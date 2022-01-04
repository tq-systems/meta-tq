require u-boot-tq.inc

DESCRIPTION = "u-boot for TQ-Systems GmbH Freescale / NXP LS102xA based modules"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

DEPENDS += "bc-native bison-native"
DEPENDS += "swap-file-endianess-native tcl-native"
#DEPENDS += "dtc-native bc-native"

SRCREV = "b9bb44db2039d6c2f8c46215b4b647796128cf7a"
SRCBRANCH = "TQMaxx-u-boot-v2017.11"

SRC_URI = " \
    ${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
"

do_compile_append_tqmls102xa () {
 unset i j
    if [ "${UBOOT_CONFIG}" ]; then
        for config in ${UBOOT_MACHINE}; do
            i=`expr $i + 1`;
            for type in ${UBOOT_CONFIG}; do
                j=`expr $j + 1`;
                if [ $j -eq $i ]; then
                    case "${config}" in
                        *mmcsd*)
                            cp ${config}/u-boot-with-spl-pbl.bin ${config}/u-boot-${type}.${UBOOT_SUFFIX};;
                        *qspi*)
                            tclsh ${STAGING_BINDIR_NATIVE}/byteswap.tcl ${config}/u-boot-pbl.bin ${config}/u-boot-pbl.swap.bin 8
                            cp ${config}/u-boot-pbl.swap.bin ${config}/u-boot-${type}.${UBOOT_SUFFIX};;
                    esac
                fi
            done
            unset j
        done
        unset i
    fi
}

COMPATIBLE_MACHINE = "tqmls102xa"

####
#PACKAGES += "${PN}-images"
#FILES_${PN}-images += "/boot"


