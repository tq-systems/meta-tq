DESCRIPTION = "U-boot for TQ-Systems GmbH Renesas RZ/G2 based modules"

# Include the files from meta-renesas
require recipes-common/recipes-bsp/u-boot/u-boot-common_${PV}.inc
require recipes-common/recipes-bsp/u-boot/u-boot.inc

DEPENDS += "bc-native dtc-native"
PROVIDES += "u-boot"

SRCREV = "eb4346dfa115e7941230eb4d4bcea7319fbcfe1b"
SRCBRANCH = "TQMaRZG2x-u-boot-v2021.10"

SRC_URI = "\
    ${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
"
PV = "v2021.10+git${SRCPV}"

#
# This part is taken from meta-renesas/recipes-rzg2h/recipes-bsp/u-boot/u-boot_2021.10.bbappend
#

UBOOT_SREC_SUFFIX = "srec"
UBOOT_SREC ?= "u-boot-elf.${UBOOT_SREC_SUFFIX}"
UBOOT_SREC_IMAGE ?= "u-boot-elf-${MACHINE}-${PV}-${PR}.${UBOOT_SREC_SUFFIX}"
UBOOT_SREC_SYMLINK ?= "u-boot-elf-${MACHINE}.${UBOOT_SREC_SUFFIX}"

do_deploy_append() {
    if [ -n "${UBOOT_CONFIG}" ]
    then
        for config in ${UBOOT_MACHINE}; do
            i=$(expr $i + 1);
            for type in ${UBOOT_CONFIG}; do
                j=$(expr $j + 1);
                if [ $j -eq $i ]
                then
                    install -m 644 ${B}/${config}/${UBOOT_SREC} ${DEPLOYDIR}/u-boot-elf-${type}-${PV}-${PR}.${UBOOT_SREC_SUFFIX}
                    cd ${DEPLOYDIR}
                    ln -sf u-boot-elf-${type}-${PV}-${PR}.${UBOOT_SREC_SUFFIX} u-boot-elf-${type}.${UBOOT_SREC_SUFFIX}
                fi
            done
            unset j
        done
        unset i
    else
        install -m 644 ${B}/${UBOOT_SREC} ${DEPLOYDIR}/${UBOOT_SREC_IMAGE}
        cd ${DEPLOYDIR}
        rm -f ${UBOOT_SREC} ${UBOOT_SREC_SYMLINK}
        ln -sf ${UBOOT_SREC_IMAGE} ${UBOOT_SREC_SYMLINK}
        ln -sf ${UBOOT_SREC_IMAGE} ${UBOOT_SREC}
    fi
}

#
# End of meta-renesas import
#

COMPATIBLE_MACHINE = "tqmarzg2x"
