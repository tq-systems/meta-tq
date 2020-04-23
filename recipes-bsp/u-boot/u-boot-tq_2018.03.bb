require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "u-boot for TQ-Group Freescale LS1028A based modules"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

PROVIDES += "u-boot"

SRCREV_tqmls1028a-mbls1028a = "79d354fcad0479c2b832044f41fe574978fc6541"
SRCBRANCH_tqmls1028a-mbls1028a = "TQMLS1028A-u-boot-v2018.03-OpenIL-u-boot-201901"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

DEPENDS += "dtc-native bc-native"
DEPENDS += "flex-native bison-native"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SCMVERSION ??= "y"
LOCALVERSION ??= "+tq"

UBOOT_LOCALVERSION = "${LOCALVERSION}"

do_configure_prepend() {
    if [ "${UBOOT_RCW_CONFIG}" ]; then
        unset i j
        if [ "${UBOOT_CONFIG}" ]; then
            for config in ${UBOOT_MACHINE}; do
                i=`expr $i + 1`;
                for type in ${UBOOT_CONFIG}; do
                    j=`expr $j + 1`;
                    if [ $j -eq $i ]; then
                        echo "CONFIG_${UBOOT_RCW_CONFIG}=y" >> ${S}/configs/${config}
                    fi
                done
                unset j
            done
            unset i
        fi
    fi
}

do_compile_prepend() {
    if [ "${SCMVERSION}" = "y" ]; then
        # Add GIT revision to the local version
        head=`cd ${S} ; git describe --tags --always 2> /dev/null`
        printf "%s%s" "+" $head > ${S}/.scmversion
        printf "%s%s" "+" $head > ${B}/.scmversion
    else
        printf "%s" "${UBOOT_LOCALVERSION}" > ${S}/.scmversion
        printf "%s" "${UBOOT_LOCALVERSION}" > ${B}/.scmversion
    fi
}

UBOOT_WITHPBL = ""
UBOOT_WITHPBL_fsl-lsch2 = "true"

do_compile_append () {
 unset i j
    if [ "${UBOOT_CONFIG}" ]; then
        for config in ${UBOOT_MACHINE}; do
            i=`expr $i + 1`;
            for type in ${UBOOT_CONFIG}; do
                if [ "${UBOOT_WITHPBL}" ]; then
                    j=`expr $j + 1`;
                    if [ $j -eq $i ]; then
                        case "${config}" in
                            *qspi*)
                                tclsh ${STAGING_BINDIR_NATIVE}/byteswap.tcl ${config}/u-boot-pbl.bin ${config}/u-boot-pbl.swap.bin 8
                                cp ${config}/u-boot-pbl.swap.bin ${config}/u-boot-${type}.${UBOOT_SUFFIX};;
                            *)
                                cp ${config}/u-boot-with-spl-pbl.bin ${config}/u-boot-${type}.${UBOOT_SUFFIX};;
                        esac
                    fi
                else
                    j=`expr $j + 1`;
                    if [ $j -eq $i ]; then
                        case "${config}" in
                            *qspi*)
                                cp ${config}/u-boot.bin ${config}/u-boot-${type}.${UBOOT_SUFFIX};;
                            *)
                                cp ${config}/u-boot-with-spl.bin ${config}/u-boot-${type}.${UBOOT_SUFFIX};;
                        esac
                    fi
                fi
            done
            unset j
        done
        unset i
    fi
}

PACKAGES += "${PN}-images"
FILES_${PN}-images += "/boot"


COMPATIBLE_MACHINE = "tqmls1028a-mbls1028a"
