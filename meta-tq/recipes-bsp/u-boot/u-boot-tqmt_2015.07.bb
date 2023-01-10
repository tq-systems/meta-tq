require recipes-bsp/u-boot/u-boot.inc
require u-boot-tq.inc

DESCRIPTION = "U-Boot for TQ-Group TQMT modules"
PROVIDES += "virtual/bootloader u-boot"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=0507cd7da8e7ad6d6701926ec9b84c95"

SRC_URI = "${TQ_GIT_BASEURL}/uboot-tqmt.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

SRCREV = "ddb620b749ecfcadd2709bc0ff754808b9c5386a"
SRCBRANCH = "TQMTxxxx-u-boot-v2015.07"

python () {
    ml = d.getVar("MULTILIB_VARIANTS")
    arch = d.getVar("OVERRIDES")

    if "e5500-64b:" in arch or "e6500-64b:" in arch:
        if not "lib32" in ml:
            raise bb.parse.SkipPackage("Building the u-boot for this arch requires multilib to be enabled")
        sys_multilib = d.getVar('TARGET_VENDOR') + 'mllib32-' + d.getVar('TARGET_OS')
        workdir = d.getVar('WORKDIR')
        d.setVar('DEPENDS:append', ' lib32-gcc-cross-powerpc lib32-libgcc')
        d.setVar('PATH:append', ':' + d.getVar('STAGING_BINDIR_NATIVE') + '/powerpc' + sys_multilib)
        d.setVar('TOOLCHAIN_OPTIONS', '--sysroot=' + workdir + '/lib32-recipe-sysroot')
        d.setVar("WRAP_TARGET_PREFIX", 'powerpc' + sys_multilib + '-')
}

WRAP_TARGET_PREFIX ?= "${TARGET_PREFIX}"

UBOOT_MAKE_TARGET:append = "${@d.getVar('FSL_RCW', True) and ' fsl_rcw.bin' or ''}"

EXTRA_OEMAKE = 'CROSS_COMPILE=${WRAP_TARGET_PREFIX} CC="${WRAP_TARGET_PREFIX}gcc ${TOOLCHAIN_OPTIONS}"'
EXTRA_OEMAKE += 'HOSTCC="${BUILD_CC} ${BUILD_CFLAGS} ${BUILD_LDFLAGS}"'

do_configure:prepend() {
    if [ "${UBOOT_CONFIG}" ] && [ "${FSL_RCW}" ]; then
        for config in ${UBOOT_MACHINE}; do
            sed -i '/CONFIG_RCW_CFG/d' ${S}/configs/${config}
            echo "CONFIG_RCW_CFG_${FSL_RCW}=y" >> ${S}/configs/${config}
        done
    fi
}

do_deploy:append() {
    # For sdcard boot, we don't use u-boot.bin, but u-boot-with-spl-pbl.bin
    unset i j
    if [ "${UBOOT_CONFIG}" ]; then
        for config in ${UBOOT_MACHINE}; do
            i=`expr $i + 1`;
            for type in ${UBOOT_CONFIG}; do
                j=`expr $j + 1`;
                if [ $j -eq $i ] && [ "${type}" = "sdcard" ]; then
                    install -d ${DEPLOYDIR}
                    install ${B}/${config}/u-boot-with-spl-pbl.${UBOOT_SUFFIX} ${DEPLOYDIR}/u-boot-${type}-${PV}-${PR}.${UBOOT_SUFFIX}
                fi
            done
            unset j
        done
        unset i
    fi

    # Install RCW
    if [ "${UBOOT_CONFIG}" ] && [ "${FSL_RCW}" ]; then
        for config in ${UBOOT_MACHINE}; do
            i=`expr $i + 1`;
            for type in ${UBOOT_CONFIG}; do
                j=`expr $j + 1`;
                if [ $j -eq $i ] && [ "${type}" != "sdcard" ]; then
                    install -d ${DEPLOYDIR}
                    install -v ${B}/${config}/fsl_rcw.bin ${DEPLOYDIR}/fsl_rcw-${type}-${FSL_RCW}.bin
                fi
            done
            unset j
        done
        unset i
    fi
}

COMPATIBLE_MACHINE = "(tqmt10xx)"
