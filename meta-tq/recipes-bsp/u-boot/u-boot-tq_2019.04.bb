require recipes-bsp/u-boot/u-boot.inc
require u-boot-tq.inc

DESCRIPTION = "u-boot for TQ-Systems GmbH TI AM57 and TI AM335 based modules"

DEPENDS += "xxd-native"

SRCREV = "8090f7cab5233d9d56eef23f1f7402114ffeb52a"
SRCBRANCH = "TQMa57xx-u-boot-v2019.04"

# target not supported in U-Boot before v2019.07
UBOOT_INITIAL_ENV = ""

COMPATIBLE_MACHINE = "^("
COMPATIBLE_MACHINE .= "tqma335x"
COMPATIBLE_MACHINE .= ")$"

do_install:append:tqma335x () {
    if [ -n "${SPL_BINARY}" ]; then
        if [ -n "${UBOOT_CONFIG}" ]; then
            for config in ${UBOOT_MACHINE}; do
                i=$(expr $i + 1);
                for type in ${UBOOT_CONFIG}; do
                    j=$(expr $j + 1);
                    if [ $j -eq $i ]; then
                         install -m 644 ${B}/${config}/${SPL_BINARY}.byteswap ${D}/boot/${SPL_IMAGE}.byteswap-${type}-${PV}-${PR}
                         ln -sf ${SPL_IMAGE}.byteswap-${type}-${PV}-${PR} ${D}/boot/${SPL_BINARYNAME}.byteswap-${type}
                         ln -sf ${SPL_IMAGE}.byteswap-${type}-${PV}-${PR} ${D}/boot/${SPL_BINARYNAME}.byteswap
                    fi
                done
                unset j
            done
        else
            install -m 644 ${B}/${SPL_BINARY}.byteswap ${D}/boot/${SPL_IMAGE}.byteswap
            ln -sf ${SPL_IMAGE}.byteswap ${D}/boot/${SPL_BINARYNAME}.byteswap
        fi
    fi
}

do_deploy:append:tqma335x () {
     if [ -n "${SPL_BINARY}" ]; then
        if [ -n "${UBOOT_CONFIG}" ]; then
            for config in ${UBOOT_MACHINE}; do
                i=$(expr $i + 1);
                for type in ${UBOOT_CONFIG}; do
                    j=$(expr $j + 1);
                    if [ $j -eq $i ]; then
                        install -m 644 ${B}/${config}/${SPL_BINARY}.byteswap ${DEPLOYDIR}/${SPL_IMAGE}.byteswap-${type}-${PV}-${PR}
                        rm -f ${DEPLOYDIR}/${SPL_BINARYNAME}.byteswap ${DEPLOYDIR}/${SPL_SYMLINK}.byteswap-${type}
                        ln -sf ${SPL_IMAGE}.byteswap-${type}-${PV}-${PR} ${DEPLOYDIR}/${SPL_BINARYNAME}.byteswap-${type}
                        ln -sf ${SPL_IMAGE}.byteswap-${type}-${PV}-${PR} ${DEPLOYDIR}/${SPL_BINARYNAME}.byteswap
                        ln -sf ${SPL_IMAGE}.byteswap-${type}-${PV}-${PR} ${DEPLOYDIR}/${SPL_SYMLINK}.byteswap-${type}
                        ln -sf ${SPL_IMAGE}.byteswap-${type}-${PV}-${PR} ${DEPLOYDIR}/${SPL_SYMLINK}.byteswap
                    fi
                done
                unset j
            done
        else
            install -m 644 ${B}/${SPL_BINARY}.byteswap ${DEPLOYDIR}/${SPL_IMAGE}.byteswap
            rm -f ${DEPLOYDIR}/${SPL_BINARYNAME}.byteswap ${DEPLOYDIR}/${SPL_SYMLINK}.byteswap
            ln -sf ${SPL_IMAGE}.byteswap ${DEPLOYDIR}/${SPL_BINARYNAME}.byteswap
            ln -sf ${SPL_IMAGE}.byteswap ${DEPLOYDIR}/${SPL_SYMLINK}.byteswap
        fi
    fi
}
