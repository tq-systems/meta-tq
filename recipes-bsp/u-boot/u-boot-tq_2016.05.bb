require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "u-boot for TQ-Group Freescale LS102xA based modules"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

PROVIDES = "u-boot"

SRCREV = "cd39a89012192ecbd903ea5f6de5d5bcdc066058"
SRCBRANCH = "TQMLS102x-v2016.05"

SRC_URI = "git://github.com/tq-systems/u-boot-tqmaxx.git;protocol=https;branch=${SRCBRANCH}"

DEPENDS += "swap-file-endianess-native tcl-native"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

do_compile_append () {
 unset i j
    if [ "x${UBOOT_CONFIG}" != "x" ]; then
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

PACKAGES += "${PN}-images"
FILES_${PN}-images += "/boot"


COMPATIBLE_MACHINE = "(tqmls102xa-mbls102xa)"
