require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "u-boot for TQ-Group Freescale / NXP LS102xA based modules"
SECTION = "bootloader"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

PROVIDES += "u-boot"

SRCREV = "62e4a0babb8e386017cad13379c8fc16c10d6d48"
SRCBRANCH = "TQMaxx-u-boot-v2017.11"

SRC_URI = " \
    ${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
    file://0001-tools-Include-U-Boot-libfdt-headers-from-their-actua.patch \
"

DEPENDS += "swap-file-endianess-native tcl-native"

DEPENDS_append = " dtc-native bc-native"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

do_compile_append_tqmls102xa () {
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

COMPATIBLE_MACHINE = "tqmls102xa"
COMPATIBLE_MACHINE_append = "|tqma6x"
