require recipes-bsp/u-boot/u-boot.inc
require u-boot-tq.inc

DESCRIPTION = "u-boot for TQ-Systems GmbH Freescale LS10xxA based modules"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

DEPENDS += "bc-native bison-native"
DEPENDS += "swap-file-endianess-native tcl-native"

SRCREV = "42021947ff51df345f6b1013d25bffe2eafa92e1"
SRCBRANCH = "TQMLS10xxA-u-boot-v2018.07"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

# target not supported in U-Boot before v2019.07
UBOOT_INITIAL_ENV = ""

do_configure:prepend() {
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

UBOOT_WITHPBL = ""
UBOOT_WITHPBL:fsl-lsch2 = "true"

do_compile:append () {
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
                            *esdhc*)
                                cp ${config}/u-boot-with-spl-pbl.bin ${config}/u-boot-${type}.${UBOOT_SUFFIX};;
                        esac
                    fi
                else
                    j=`expr $j + 1`;
                    if [ $j -eq $i ]; then
                        case "${config}" in
                            *qspi*)
                                cp ${config}/u-boot.bin ${config}/u-boot-${type}.${UBOOT_SUFFIX};;
                            *esdhc*)
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

COMPATIBLE_MACHINE = "tqmls10xxa"

####
#PACKAGES += "${PN}-images"
#FILES:${PN}-images += "/boot"


