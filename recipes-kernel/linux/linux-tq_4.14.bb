SUMMARY = "Linux for TQ-Group Freescale LS1012A based modules"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

#
# append linux-mainline if we provide mainline kernel versions
#
PROVIDES += "virtual/kernel linux"

inherit kernel siteinfo

DEPENDS += "lzop-native bc-native"
DEPENDS_append = " libgcc"

SRC_URI = "\
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://disable-unused-platform.cfg \
  file://gpio-enablement.cfg \
  file://i2c-devices.cfg \
  file://net-devices.cfg \
  file://rtc-support.cfg \
  file://bpf-support.cfg \
  file://thermal-devices.cfg \
"

SRCBRANCH = "TQMLS1012AL-bringup-LSDK-18.03-V4.14"
SRCREV = "7265353d3b62d66ffdab6f911999d70756b4dce0"

S = "${WORKDIR}/git"

# not put Images into /boot of rootfs, install kernel-image if needed
RDEPENDS_${KERNEL_PACKAGE_NAME}-base = ""

KERNEL_CC_append = " ${TOOLCHAIN_OPTIONS}"
KERNEL_LD_append = " ${TOOLCHAIN_OPTIONS}"
KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

KERNEL_DEFCONFIG ?= "defconfig"
DELTA_KERNEL_DEFCONFIG ?= ""
DELTA_KERNEL_DEFCONFIG_tqmls1012al-mbls1012al = "\
    lsdk.config \
    disable-unused-platform.cfg \
    gpio-enablement.cfg \
    i2c-devices.cfg \
    net-devices.cfg \
    rtc-support.cfg \
    bpf-support.cfg \
    thermal-devices.cfg \
"

do_merge_delta_config[dirs] = "${B}"

do_merge_delta_config() {
    # create config with make config
    oe_runmake  -C ${S} O=${B} ${KERNEL_DEFCONFIG}

    # check if bigendian is enabled
    if [ "${SITEINFO_ENDIANNESS}" = "be" ]; then
        echo "CONFIG_CPU_BIG_ENDIAN=y" >> .config
        echo "CONFIG_MTD_CFI_BE_BYTE_SWAP=y" >> .config
    fi

    # add config fragments
    for deltacfg in ${DELTA_KERNEL_DEFCONFIG}; do
        if [ -f ${S}/arch/${ARCH}/configs/${deltacfg} ]; then
            oe_runmake  -C ${S} O=${B} ${deltacfg}
        elif [ -f "${WORKDIR}/${deltacfg}" ]; then
            ${S}/scripts/kconfig/merge_config.sh -m .config ${WORKDIR}/${deltacfg}
        elif [ -f "${deltacfg}" ]; then
            ${S}/scripts/kconfig/merge_config.sh -m .config ${deltacfg}
        fi
    done
    cp .config ${WORKDIR}/defconfig
}
addtask merge_delta_config before do_configure after do_patch

COMPATIBLE_MACHINE = "tqmls1012al"
