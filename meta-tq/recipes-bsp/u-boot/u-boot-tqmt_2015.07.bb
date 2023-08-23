require recipes-bsp/u-boot/u-boot.inc
require u-boot-tq.inc

DESCRIPTION = "U-Boot for TQ-Group TQMT modules"
PROVIDES += "virtual/bootloader u-boot"
DEPENDS += "u-boot-mkimage-native rcw"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=0507cd7da8e7ad6d6701926ec9b84c95"

SRC_URI = "${TQ_GIT_BASEURL}/uboot-tqmt.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

SRCREV = "e846803ca31f2d9d25a7298fc4b13271350ab0ab"
SRCBRANCH = "TQMTxxxx-u-boot-v2015.07"

# target not supported in U-Boot before v2019.07
UBOOT_INITIAL_ENV = ""

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

EXTRA_OEMAKE = 'CROSS_COMPILE=${WRAP_TARGET_PREFIX} CC="${WRAP_TARGET_PREFIX}gcc ${TOOLCHAIN_OPTIONS}"'
EXTRA_OEMAKE += 'HOSTCC="${BUILD_CC} ${BUILD_CFLAGS} ${BUILD_LDFLAGS}"'

RCW_FOLDER ??= "${MACHINE}"
RCW_FOLDER:tqmt1024 ?= "tqmt1024"
RCW_FOLDER:tqmt1040 ?= "tqmt1040"
RCW_FOLDER:tqmt1042 ?= "tqmt1042"
RCW_FOLDER:tqmt1022 ?= "tqmt1042"
RCW_SUFFIX ?= ".bin"

do_compile:append() {
    unset i j
    for config in ${UBOOT_MACHINE}; do
        i=$(expr $i + 1);
        for type in ${UBOOT_CONFIG}; do
            j=$(expr $j + 1);
            if [ $j -eq $i ] && [ $type = "sdcard" ]; then
                cd ${B}/$config
                for rcw_file in ${UBOOT_RCW_VARIANTS}; do
                    spl_file="u-boot-spl-$(basename ${rcw_file}).pbl"
                    spl_pbl_file="u-boot-with-spl-pbl-$(basename ${rcw_file}).bin"

                    uboot-mkimage -n ${DEPLOY_DIR_IMAGE}/rcw/${RCW_FOLDER}/${rcw_file}${RCW_SUFFIX} -T pblimage \
                                -A powerpc -a 0xFFFD8000 -d spl/u-boot-spl.bin spl/${spl_file}

                    ${OBJCOPY} --gap-fill=0xff -I binary -O binary --pad-to=0x40000 --gap-fill=0xff spl/${spl_file} ${spl_pbl_file}
                    cat u-boot.bin >> ${spl_pbl_file}
                done

            fi
        done
        unset j
    done
    unset i
}

do_deploy:append() {
    unset i j
    for config in ${UBOOT_MACHINE}; do
        i=$(expr $i + 1);
        for type in ${UBOOT_CONFIG}; do
            j=$(expr $j + 1);
            if [ $j -eq $i ] && [ $type = "sdcard" ]; then
                cp ${B}/${config}/u-boot-with-spl-pbl-rcw*.bin  ${DEPLOY_DIR_IMAGE}
            fi
        done
        unset j
    done
    unset i
}

COMPATIBLE_MACHINE = "(tqmt10xx)"
