# SPDX-License-Identifier: MIT
#
# Copyright (C) 2017-2020 NXP
# Copyright (c) 2020-2024 TQ-Systems GmbH <oss@ew.tq-group.com>,
# D-82229 Seefeld, Germany.

require imx-mkimage-tq_git.inc

DESCRIPTION = "Generate Boot Stream for i.MX 8/9 device"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"
SECTION = "BSP"

# This is needed to set SECO_FIRMWARE_NAME for imx8 / imx8x
# and EdgeLock firmware for i.MX9
inherit use-imx-security-controller-firmware

IMX_EXTRA_FIRMWARE:append = " ${@bb.utils.contains('IMXBOOT_TARGETS', 'flash_linux_m4', 'virtual/imx-cortexm-demos', '', d)}"

inherit imx-hab

DEPENDS += "\
    ${IMX_EXTRA_FIRMWARE} \
    ${IMX_DEFAULT_ATF_PROVIDER} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'optee', 'optee-os', '', d)} \
    u-boot \
    xxd-native \
"

# Secure Boot / HAB support
DEPENDS += "${@bb.utils.contains('DISTRO_FEATURES', 'secure', 'imx-cst-native imx-cst-keys-native', '', d)}"

# imx8m needs mkimage and dtc for ITB images
DEPENDS:append:mx8m-generic-bsp = "\
    dtc-native \
    u-boot-mkimage-native \
"

BOOT_NAME = "imx-boot"
PROVIDES = "${BOOT_NAME}"

inherit deploy

# Add CFLAGS with native INCDIR & LIBDIR for imx-mkimage build
CFLAGS = "-O2 -Wall -std=c99 -I ${STAGING_INCDIR_NATIVE} -L ${STAGING_LIBDIR_NATIVE}"

M4_DEFAULT_IMAGE    ??= "INVALID"
M4_1_DEFAULT_IMAGE  ??= "INVALID"

# This package aggregates output deployed by other packages,
# so set the appropriate dependencies
do_compile[depends] += "\
    ${IMX_DEFAULT_ATF_PROVIDER}:do_deploy \
    ${@' '.join('%s:do_deploy' % r for r in '${IMX_EXTRA_FIRMWARE}'.split() )} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'optee', 'optee-os:do_deploy', '', d)} \
    virtual/bootloader:do_deploy \
"

SC_FIRMWARE_NAME ?= "scfw_tcm.bin"

OEI_ENABLE = "${@bb.utils.contains('DEPENDS', 'virtual/imx-oei', 'YES', 'NO', d)}"
OEI_NAME ?= "oei-${OEI_CORE}-*.bin"

ATF_MACHINE_NAME ?= "bl31-${ATF_PLATFORM}.bin"
ATF_MACHINE_NAME:append = "${@bb.utils.contains('MACHINE_FEATURES', 'optee', '-optee', '', d)}"

UBOOT_NAME = "u-boot-${MACHINE}.bin-${UBOOT_CONFIG}"
UBOOT_SPL_NAME = "${@os.path.basename(d.getVar("SPL_BINARY"))}-${MACHINE}-${UBOOT_CONFIG}"

TOOLS_NAME ?= "mkimage_imx8"

DEPLOY_OPTEE = "${@bb.utils.contains('MACHINE_FEATURES', 'optee', 'true', 'false', d)}"

IMXBOOT_TARGETS ??= "unknown"

# used as SOC parameter for make invocation of imx-mkimage tool
IMX_BOOT_SOC_TARGET = "INVALID"
IMX_BOOT_SOC_TARGET:mx8mq-generic-bsp = "iMX8M"
IMX_BOOT_SOC_TARGET:mx8mm-generic-bsp = "iMX8MM"
IMX_BOOT_SOC_TARGET:mx8mn-generic-bsp = "iMX8MN"
IMX_BOOT_SOC_TARGET:mx8mp-generic-bsp = "iMX8MP"
IMX_BOOT_SOC_TARGET:mx8qm-generic-bsp = "iMX8QM"
IMX_BOOT_SOC_TARGET:mx8x-generic-bsp = "iMX8QX"
IMX_BOOT_SOC_TARGET:mx91-generic-bsp = "iMX91"
IMX_BOOT_SOC_TARGET:mx93-generic-bsp = "iMX93"
IMX_BOOT_SOC_TARGET:mx95-generic-bsp = "iMX95"

BOOT_STAGING = "${S}/${IMX_BOOT_SOC_TARGET}"
BOOT_STAGING:mx8m-generic-bsp = "${S}/iMX8M"
BOOT_STAGING:mx8dx-generic-bsp = "${S}/iMX8QX"
BOOT_STAGING:mx91-generic-bsp  = "${S}/iMX91"
BOOT_STAGING:mx93-generic-bsp  = "${S}/iMX93"
BOOT_STAGING:mx95-generic-bsp  = "${S}/iMX95"

SOC_FAMILY = "INVALID"
SOC_FAMILY:mx8-generic-bsp = "mx8"
SOC_FAMILY:mx8m-generic-bsp = "mx8m"
SOC_FAMILY:mx8x-generic-bsp = "mx8x"
SOC_FAMILY:mx8ulp-generic-bsp = "mx8ulp"
SOC_FAMILY:mx91-generic-bsp   = "mx91"
SOC_FAMILY:mx93-generic-bsp   = "mx93"
SOC_FAMILY:mx95-generic-bsp   = "mx95"

REV_OPTION ?= "REV=${IMX_SOC_REV_UPPER}"

MKIMAGE_EXTRA_ARGS ?= ""
MKIMAGE_EXTRA_ARGS:mx95-nxp-bsp ?= " \
    OEI=${OEI_ENABLE} \
    LPDDR_TYPE=${DDR_TYPE} \
    ${@'LPDDR_FW_VERSION='+d.getVar('LPDDR_FW_VERSION') if d.getVar('LPDDR_FW_VERSION') else ''} \
    ${@bb.utils.contains('SYSTEM_MANAGER_CONFIG', 'mx95alt', 'MSEL=1', '', d)}"

##
# do assignment for TQMa8Xx[S] / TQMa8x SOM to enable bootstream with M4 demo
##
M4_DEFAULT_IMAGE:tqma8xx ?= "rpmsg_lite_pingpong_rtos_linux_remote.bin"
M4_DEFAULT_IMAGE:tqma8xxs ?= "rpmsg_lite_pingpong_rtos_linux_remote.bin"

M4_DEFAULT_IMAGE:tqma8x ?= "rpmsg_lite_pingpong_rtos_linux_remote_m40.bin"
M4_1_DEFAULT_IMAGE:tqma8x ?= "rpmsg_lite_pingpong_rtos_linux_remote_m41.bin"

compile_prepare() {
    bberror 'Invalid SOC family'
}

compile_prepare:mx8-generic-bsp() {
    bbnote '8QM boot binary build'
    cp ${DEPLOY_DIR_IMAGE}/${SC_FIRMWARE_NAME} ${BOOT_STAGING}/scfw_tcm.bin
    cp ${DEPLOY_DIR_IMAGE}/${SECO_FIRMWARE_NAME}             ${BOOT_STAGING}
    if [ "$1" = "flash_linux_m4" ]; then
        cp ${DEPLOY_DIR_IMAGE}/${M4_DEFAULT_IMAGE}           ${BOOT_STAGING}/m4_image.bin
        cp ${DEPLOY_DIR_IMAGE}/${M4_1_DEFAULT_IMAGE}           ${BOOT_STAGING}/m4_1_image.bin
    fi
    for type in ${UBOOT_CONFIG}; do
        cp ${DEPLOY_DIR_IMAGE}/u-boot-${MACHINE}.bin-${type} ${BOOT_STAGING}/u-boot.bin-${type}
        if [ -e ${DEPLOY_DIR_IMAGE}/u-boot-spl.bin-${MACHINE}-${type} ] ; then
            cp ${DEPLOY_DIR_IMAGE}/u-boot-spl.bin-${MACHINE}-${type} \
                                                             ${BOOT_STAGING}/u-boot-spl.bin-${type}
        fi
    done
}

compile_prepare:mx8m-generic-bsp() {
    bbnote '8MQ/8MM/8MN/8MP boot binary build'
    for ddr_firmware in ${DDR_FIRMWARE_NAME}; do
        bbnote "Copy ddr_firmware: ${ddr_firmware} from ${DEPLOY_DIR_IMAGE} -> ${BOOT_STAGING} "
        cp ${DEPLOY_DIR_IMAGE}/${ddr_firmware}               ${BOOT_STAGING}
    done
    cp ${DEPLOY_DIR_IMAGE}/signed_dp_imx8m.bin               ${BOOT_STAGING}
    cp ${DEPLOY_DIR_IMAGE}/signed_hdmi_imx8m.bin             ${BOOT_STAGING}
    for type in ${UBOOT_CONFIG}; do
        for dtb in ${UBOOT_DTB_NAME}; do
            cp ${DEPLOY_DIR_IMAGE}/u-boot-${dtb}-${MACHINE}-${type} \
                                                             ${BOOT_STAGING}/${dtb}-${type}
        done
        cp ${DEPLOY_DIR_IMAGE}/u-boot-spl.bin-${MACHINE}-${type} \
                                                             ${BOOT_STAGING}/u-boot-spl.bin-${type}
        cp ${DEPLOY_DIR_IMAGE}/u-boot-nodtb.bin-${MACHINE}-${type} \
                                                             ${BOOT_STAGING}/u-boot-nodtb.bin-${type}
        cp ${DEPLOY_DIR_IMAGE}/u-boot-${MACHINE}.bin-${type} ${BOOT_STAGING}/u-boot.bin-${type}
    done
}

compile_prepare:mx8x-generic-bsp() {
    bbnote '8QX boot binary build'
    if [ "$1" = "flash_linux_m4" ]; then
        cp ${DEPLOY_DIR_IMAGE}/${M4_DEFAULT_IMAGE}           ${BOOT_STAGING}/m4_image.bin
    fi
    cp ${DEPLOY_DIR_IMAGE}/${SECO_FIRMWARE_NAME}             ${BOOT_STAGING}
    cp ${DEPLOY_DIR_IMAGE}/${SC_FIRMWARE_NAME}               ${BOOT_STAGING}/scfw_tcm.bin
    for type in ${UBOOT_CONFIG}; do
        cp ${DEPLOY_DIR_IMAGE}/u-boot-${MACHINE}.bin-${type} ${BOOT_STAGING}/u-boot.bin-${type}
        if [ -e ${DEPLOY_DIR_IMAGE}/u-boot-spl.bin-${MACHINE}-${type} ] ; then
            cp ${DEPLOY_DIR_IMAGE}/u-boot-spl.bin-${MACHINE}-${type} \
                                                             ${BOOT_STAGING}/u-boot-spl.bin-${type}
        fi
    done
}

compile_prepare_mx9_common() {
    bbnote 'i.MX9 boot binary build'
    for ddr_firmware in ${DDR_FIRMWARE_NAME}; do
        bbnote "Copy ddr_firmware: ${ddr_firmware} from ${DEPLOY_DIR_IMAGE} -> ${BOOT_STAGING} "
        cp "${DEPLOY_DIR_IMAGE}/${ddr_firmware}"            "${BOOT_STAGING}/"
    done
    cp "${DEPLOY_DIR_IMAGE}/${SECO_FIRMWARE_NAME}"          "${BOOT_STAGING}/"
    for type in ${UBOOT_CONFIG}; do
        cp "${DEPLOY_DIR_IMAGE}/u-boot-spl.bin-${MACHINE}-${type}" \
                                                            "${BOOT_STAGING}/u-boot-spl.bin-${type}"
        cp "${DEPLOY_DIR_IMAGE}/u-boot-${MACHINE}.bin-${type}" "${BOOT_STAGING}/u-boot.bin-${type}"
    done
}

compile_prepare:mx9-generic-bsp() {
    compile_prepare_mx9_common "$1"
}

compile_prepare:mx95-generic-bsp() {
    compile_prepare_mx9_common "$1"

    if [ "${OEI_SOC}" = "mx95" ] ; then
        bbnote 'i.MX95 copy OEI / SM'
        # Copy OEI images to be used
        cp "${DEPLOY_DIR_IMAGE}/oei-m33-ddr.bin" "${BOOT_STAGING}/"
        cp "${DEPLOY_DIR_IMAGE}/oei-m33-tcm.bin" "${BOOT_STAGING}/"
        # Copy SM image to be used
        cp "${DEPLOY_DIR_IMAGE}/${SYSTEM_MANAGER_FIRMWARE_BASENAME}-${SYSTEM_MANAGER_CONFIG}.bin" "${BOOT_STAGING}/${SYSTEM_MANAGER_FIRMWARE_BASENAME}.bin"
    fi
}

compile_finish() {
    # Do nothing by default
    :
}

generate_habinfo_hab4() {
    local target="$1" print_fit_hab_target

    awk '
        /^ csf_off\>/ { print "SPL_CSF_OFF=\""$2"\"" }
        /^ spl hab block:/ { print "SPL_HAB_BLOCK=\""$4" "$5" "$6"\"" }
        /^ sld_csf_off\>/ { print "SLD_CSF_OFF=\""$2"\"" }
        /^ sld hab block:/ { print "SLD_HAB_BLOCK=\""$4" "$5" "$6"\"" }
    ' ${BOOT_STAGING}/mkimage.log

    case "${target}" in
    *_flexspi)
        print_fit_hab_target='print_fit_hab_flexspi'
        ;;
    *)
        print_fit_hab_target='print_fit_hab'
        ;;
    esac

    echo -n 'FIT_HAB_BLOCK="'
    oe_runmake SOC=${IMX_BOOT_SOC_TARGET} ${REV_OPTION} dtbs=${UBOOT_DTB_NAME} -s ${print_fit_hab_target}
    echo '"'
}

generate_habinfo_ahab() {
    local target="$1" print_fit_hab_target

    awk '
        /^\tOffsets = / {
            print "CONTAINER_HEADER_OFF=\""$3"\""
            print "SIGNATURE_BLOCK_OFF=\""$4"\""
        }
    ' ${BOOT_STAGING}/mkimage.log
}

generate_csf_hab4() {
    local target="$1" type="$2"
    local flash_bin="${BOOT_NAME}-${MACHINE}-${type}.bin-${target}"

    # habinfo has been sourced by compile_finish for _HAB_BLOCK data

    local hab_blocks="$(
        printf '%s "%s"' "${SLD_HAB_BLOCK}" "${flash_bin}"

        printf '%s' "${FIT_HAB_BLOCK}" | while IFS= read -r block; do
            printf '%s' ', \\\n'
            printf '             %s "%s"' "${block}" "${flash_bin}"
        done
    )"

    imx_hab_generate_csf_hab4 \
        ${S}/csf_spl-${type}.txt-${target} \
        ${WORKDIR}/csf_spl.txt.in \
        "${SPL_HAB_BLOCK} \"${flash_bin}\""
    imx_hab_generate_csf_hab4 \
        ${S}/csf_fit-${type}.txt-${target} \
        ${WORKDIR}/csf_fit.txt.in \
        "${hab_blocks}"
}

generate_csf_ahab() {
    local target="$1" type="$2"
    local flash_bin="${BOOT_NAME}-${MACHINE}-${type}.bin-${target}"
    local csf_template="csf_boot_image"
    if [ "${target}" = "u-boot-atf-container.img" ] ; then
        csf_template="csf_uboot_atf"
        flash_bin="${BOOT_STAGING}/u-boot-atf-container.img"
    fi

    # The offsets for flexspi build are calculated before prepending the
    # SPI header to the bootstream. Therefore, the size and padding for SPI
    # header (which is 4kiB) needs to be added to the offsets for container
    # header and signature block.
    # 
    # ${string##*pattern} is substring parameter expansion for delete largest
    # prefix pattern, i.e. the largest prefix from 'string' that matches
    # 'pattern' is removed from 'string'. If ${target} is anything that ends
    # on 'flexspi' the resulting output is an empty string.
    if [ -z "${target##*flexspi}" ] ; then
        CONTAINER_HEADER_OFF=$(sh -c 'printf '0x%x' $(($1 + 0x1000))' - "${CONTAINER_HEADER_OFF}")
        SIGNATURE_BLOCK_OFF=$(sh -c 'printf '0x%x' $(($1 + 0x1000))' - "${SIGNATURE_BLOCK_OFF}")
    fi

    local offsets="${CONTAINER_HEADER_OFF} ${SIGNATURE_BLOCK_OFF}"

    imx_hab_generate_csf_ahab \
        ${S}/${csf_template}-${type}.txt-${target} \
        ${WORKDIR}/${csf_template}.txt.in \
        "${offsets}" \
        "${flash_bin}"
}

hab_sign_part_hab4() {
    local target="$1" type="$2" part="$3" offset="$4"
    local flash_bin="${BOOT_NAME}-${MACHINE}-${type}.bin-${target}"

    cst -i ${S}/csf_${part}-${type}.txt-${target} -o ${S}/csf_${part}-${type}.bin-${target}

    # Patch signature into bootstream at given offset
    dd if=${S}/csf_${part}-${type}.bin-${target} of=${flash_bin} seek=$(printf '%d' "${offset}") oflag=seek_bytes conv=notrunc
}

hab_sign_hab4() {
    local target="$1" type="$2"

    # No key set, signing is skipped
    imx_hab_check_keys_configured || return 0

    # habinfo has been sourced by compile_finish for offsets
    hab_sign_part_hab4 "${target}" "${type}" spl "${SPL_CSF_OFF}"
    hab_sign_part_hab4 "${target}" "${type}" fit "${SLD_CSF_OFF}"
}

hab_sign_ahab() {
    local target="$1" type="$2"
    local flash_bin="${BOOT_NAME}-${MACHINE}-${type}.bin-${target}"

    # No key set, signing is skipped
    imx_hab_check_keys_configured || return 0

    if [ "${target}" = "u-boot-atf-container.img" ] ; then
        cst -i ${S}/csf_uboot_atf-${type}.txt-${target} -o ${S}/csf_uboot_atf-${type}.bin-${target}
    else
        cst -i ${S}/csf_boot_image-${type}.txt-${target} -o ${flash_bin}
    fi
}

compile_finish:nxp-hab4() {
    local target="$1" type="$2"

    generate_habinfo_hab4 "${target}" > ${S}/habinfo-${type}.env-${target}

    . ${S}/habinfo-${type}.env-${target}
    generate_csf_hab4 "${target}" "${type}"

    if ${@bb.utils.contains('DISTRO_FEATURES', 'secure', 'true', 'false', d)}; then
        hab_sign_hab4 "${target}" "${type}"
    fi
}

compile_finish:nxp-ahab() {
    local target="$1" type="$2"

    generate_habinfo_ahab "${target}" > ${S}/habinfo-${type}.env-${target}

    . ${S}/habinfo-${type}.env-${target}
    generate_csf_ahab "${target}" "${type}"

    if ${@bb.utils.contains('DISTRO_FEATURES', 'secure', 'true', 'false', d)}; then
	hab_sign_ahab "${target}" "${type}"
        if [ "${target}" = "u-boot-atf-container.img" ]; then
            # Copy signed container back to working directory, needed for
            # generation of flash.bin
            cp ${S}/csf_uboot_atf-${type}.bin-${target} ${BOOT_STAGING}/u-boot-atf-container.img
        fi
    fi
}

do_compile() {
    rm -f ${S}/habinfo-* ${S}/csf_*.txt-* ${S}/csf_*.bin-*

    if ${@bb.utils.contains('DISTRO_FEATURES', 'secure', 'true', 'false', d)}; then
        if imx_hab_check_keys_configured; then
            imx_hab_install_keys ${S}
        else
            bbwarn 'IMX_HAB_KEY_NAME unset, skipping signature generation.'
        fi
    fi

    # mkimage for i.MX8
    # Copy TEE binary to SoC target folder to mkimage
    if ${DEPLOY_OPTEE}; then
        cp ${DEPLOY_DIR_IMAGE}/tee.bin ${BOOT_STAGING}
    fi
    cp ${DEPLOY_DIR_IMAGE}/${ATF_MACHINE_NAME} ${BOOT_STAGING}/bl31.bin
    for target in ${IMXBOOT_TARGETS}; do
        for config in ${UBOOT_CONFIG}; do
            compile_prepare "$target"

            allbins="u-boot.bin u-boot-nodtb.bin u-boot-spl.bin"
            for bin in ${allbins} ; do
                if [ -e "${BOOT_STAGING}/${bin}" ]; then
                    rm ${BOOT_STAGING}/${bin}
                fi
                if [ -e "${BOOT_STAGING}/${bin}-${config}" ]; then
                    ln -s ${bin}-${config} ${BOOT_STAGING}/${bin}
                fi
            done
            #bbnote "SOC_FAMILY is "${SOC_FAMILY}"
            if [ "${SOC_FAMILY}" = "mx8m" ] ; then
                for dtb in ${UBOOT_DTB_NAME}; do
                    if [ -e "${BOOT_STAGING}/${dtb}" ]; then
                        rm ${BOOT_STAGING}/${dtb}
                    fi
                    if [ -e "${BOOT_STAGING}/${dtb}-${config}" ]; then
                        ln -s ${dtb}-${config} ${BOOT_STAGING}/${dtb}
                    fi
                done
            fi

            oe_runmake clean

            if [ "$target" = "flash_linux_m4_no_v2x" ]; then
                # Special target build for i.MX 8DXL with V2X off
                bbnote "building ${IMX_BOOT_SOC_TARGET} - ${REV_OPTION} V2X=NO ${target}"
                oe_runmake SOC=${IMX_BOOT_SOC_TARGET} ${REV_OPTION} V2X=NO dtbs=${UBOOT_DTB_NAME} flash_linux_m4
            else
                if ${@bb.utils.contains('DISTRO_FEATURES', 'secure', 'true', 'false', d)} \
                   && imx_hab_check_keys_configured \
                   && [ "${HAB_TYPE}" = "ahab" ]
                then
                    # If bootstream should be signed during build, make
                    # u-boot-atf-container.img first and sign it before
                    # building flash.bin.
                    imx_bl3x_container="u-boot-atf-container.img"
                    bbnote "building ${IMX_BOOT_SOC_TARGET} - ${REV_OPTION} ${imx_bl3x_container}"
                    oe_runmake SOC=${IMX_BOOT_SOC_TARGET} ${REV_OPTION} dtbs=${UBOOT_DTB_NAME} ${imx_bl3x_container}
                    compile_finish "$imx_bl3x_container" "$config"
                fi
                bbnote "building ${IMX_BOOT_SOC_TARGET} - ${REV_OPTION} ${MKIMAGE_EXTRA_ARGS} ${target}"
                oe_runmake SOC=${IMX_BOOT_SOC_TARGET} ${REV_OPTION} ${MKIMAGE_EXTRA_ARGS} dtbs=${UBOOT_DTB_NAME} ${target}
            fi

            if [ -e "${BOOT_STAGING}/flash.bin" ]; then
                cp ${BOOT_STAGING}/flash.bin ${S}/${BOOT_NAME}-${MACHINE}-${config}.bin-${target}
                compile_finish "$target" "$config"
            fi
        done
    done
}

do_install () {
    install -d ${D}/boot
    for target in ${IMXBOOT_TARGETS}; do
        for type in ${UBOOT_CONFIG}; do
            install -m 0644 ${S}/${BOOT_NAME}-${MACHINE}-${type}.bin-${target} ${D}/boot/
        done
    done
}

IMX_HAB_DEPLOY_TARGETS = "${IMXBOOT_TARGETS}"
IMX_HAB_DEPLOY_TARGETS:nxp-ahab = "${IMXBOOT_TARGETS} u-boot-atf-container.img"

do_deploy() {
    # copy the generated boot images to deploy path
    for target in ${IMXBOOT_TARGETS}; do
        for type in ${UBOOT_CONFIG}; do
            install -m 0644 ${S}/${BOOT_NAME}-${MACHINE}-${type}.bin-${target} \
                                                             ${DEPLOYDIR}
        done
    done

    for target in ${IMX_HAB_DEPLOY_TARGETS}; do
        for type in ${UBOOT_CONFIG}; do
            for file in habinfo-${type}.env \
                        csf_spl-${type}.txt \
                        csf_fit-${type}.txt \
                        csf_uboot_atf-${type}.txt \
                        csf_boot_image-${type}.txt; do
                if [ -e ${S}/${file}-${target} ]; then
                    install -m 0644 ${S}/${file}-${target} ${DEPLOYDIR}/
                fi
            done
        done
    done
}
addtask deploy before do_build after do_compile

PACKAGE_ARCH = "${MACHINE_ARCH}"
FILES:${PN} = "/boot"

COMPATIBLE_MACHINE = "(mx8-generic-bsp|mx9-generic-bsp)"
