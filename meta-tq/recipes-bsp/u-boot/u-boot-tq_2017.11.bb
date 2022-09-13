require recipes-bsp/u-boot/u-boot.inc
require u-boot-tq.inc

DESCRIPTION = "u-boot for TQ-Systems GmbH Freescale / NXP LS102xA based modules"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

DEPENDS += "bc-native bison-native"
DEPENDS += "swap-file-endianess-native tcl-native"
#DEPENDS += "dtc-native bc-native"

SRCREV = "f6e872244f807f68b2c984936b6fdd77cb91c31b"
SRCBRANCH = "TQMaxx-u-boot-v2017.11"

SRC_URI = "\
    ${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
"

do_compile:append:tqmls102xa () {
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
#FILES:${PN}-images += "/boot"


