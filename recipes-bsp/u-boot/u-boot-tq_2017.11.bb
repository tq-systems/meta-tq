require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "u-boot for TQ-Group Freescale LS1012A based modules"
SECTION = "bootloader"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

PROVIDES = "u-boot"

SRCBRANCH = "TQMLS1012AL-bringup-v2017.11-LSDK-18.03"
SRCREV = "56bdd35181e81e6496dc64c89b10b0496d491313"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

S = "${WORKDIR}/git"

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

COMPATIBLE_MACHINE = "tqmls1012al-mbls1012al"
