require recipes-bsp/u-boot/u-boot.inc
require u-boot-tq.inc

DESCRIPTION = "U-Boot for TQ-Systems GmbH Freescale / NXP LS102xA based modules"

LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

DEPENDS += "\
    bc-native \
    rcw \
"

DEPENDS:append:tqmls102xa = "\
    swap-file-endianess-native \
"

SRCREV = "e7980913deb34f2b202f5640cab76a6b024c6419"
SRCBRANCH = "TQMaxx-u-boot-v2017.11"

# target not supported in U-Boot before v2019.07
UBOOT_INITIAL_ENV = ""

RCW_FOLDER ??= "${MACHINE}"
RCW_FOLDER:tqmls102xa ?= "tqmls1021a"
RCW_SUFFIX ?= ".bin"

# This package aggregates output deployed by other packages,
# so set the appropriate dependencies
do_compile[depends] += "\
    rcw:do_deploy \
"

do_compile:append() {
    if [ "${UBOOT_CONFIG}" ]; then
        for config in ${UBOOT_MACHINE}; do
            i=$(expr $i + 1);
            for type in ${UBOOT_CONFIG}; do
                j=$(expr $j + 1);
                if [ $j -eq $i ]; then
                    RCW_FILE=""
                    # Remove '_ecc' suffix when searching for RCW
                    plain_type=${type%_ecc}
                    # Remove 'ae_' prefix when searching for RCW
                    plain_type=${plain_type#ae_}
                    for rcw_file in ${UBOOT_RCW_VARIANTS}; do
                        PATTERN=$(echo $rcw_file | grep "_${plain_type}") || true
                        if [ -n "$PATTERN" ]; then
                            RCW_FILE=$rcw_file
                            break
                        fi
                    done
                    if [ -z "$RCW_FILE" ]; then
                        bbwarn "No RCW file found for configuration: $config"
                        continue
                    fi
                    AE_CONFIG=$(echo $type | grep "ae_") || true
                    if [ -n "$AE_CONFIG" ]; then
                        RCW_FILE="${RCW_FILE}_1200"
                    else
                        RCW_FILE="${RCW_FILE}_1000"
                    fi
                    case "${plain_type}" in
                        *_sd)
                            cd ${B}/$config
                                spl_file="u-boot-spl-$(basename ${RCW_FILE}).pbl"
                                # Must match u-boot's CONFIG_SPL_PAD_TO
                                spl_pad_to=0x1c000

                                ./tools/mkimage -n ${DEPLOY_DIR_IMAGE}/rcw/${RCW_FOLDER}/${RCW_FILE}${RCW_SUFFIX} -T pblimage \
                                            -A arm -C none -a 0x10000000 -e 0 -d spl/u-boot-spl.bin spl/${spl_file}
                                ${OBJCOPY} --gap-fill=0xff -I binary -O binary --pad-to=${spl_pad_to} --gap-fill=0xff spl/${spl_file} u-boot-${type}.${UBOOT_SUFFIX}
                                cat u-boot.bin >> u-boot-${type}.${UBOOT_SUFFIX}
                            ;;
                        *_qspi)
                            cd ${B}/$config
                                # Must match u-boot's CONFIG_SPL_PAD_TO
                                spl_pad_to=0x10000

                                ${OBJCOPY} --gap-fill=0xff -I binary -O binary --pad-to=${spl_pad_to} --gap-fill=0xff  ${DEPLOY_DIR_IMAGE}/rcw/${RCW_FOLDER}/${RCW_FILE}${RCW_SUFFIX} rcw.temp.bin
                                cat rcw.temp.bin u-boot.bin > rcw_uboot.bin
                                ${OBJCOPY} --gap-fill=0xff -I binary -O binary --pad-to=0x40000 --gap-fill=0xff rcw_uboot.bin u-boot-${type}.${UBOOT_SUFFIX}.temp
                                tclsh ${STAGING_BINDIR_NATIVE}/byteswap.tcl u-boot-${type}.${UBOOT_SUFFIX}.temp u-boot-${type}.${UBOOT_SUFFIX} 8
                            ;;
                    esac
                    break
                fi
            done
            unset j
        done
        unset i
    fi
}

do_deploy:append() {
    unset i j
    for config in ${UBOOT_MACHINE}; do
        i=$(expr $i + 1);
        for type in ${UBOOT_CONFIG}; do
            j=$(expr $j + 1);
            if [ $j -eq $i ] && [ $type = "sdcard" ]; then
                cp ${B}/${config}/u-boot-with-rcw*.bin  ${DEPLOY_DIR_IMAGE}
            fi
        done
        unset j
    done
    unset i
}

COMPATIBLE_MACHINE = "tqmls102xa"

