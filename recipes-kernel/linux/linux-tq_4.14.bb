SUMMARY = "Linux for TQ-Group Freescale LS1012A based modules"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

#
# append linux-mainline if we provide mainine kernel versions
#
PROVIDES = "virtual/kernel"

inherit kernel siteinfo

DEPENDS += "lzop-native bc-native"
DEPENDS_append = " libgcc"

SRC_URI = "\
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
"

SRCBRANCH = "TQMLS1012AL-bringup-LSDK-18.03-V4.14"
SRCREV = "7265353d3b62d66ffdab6f911999d70756b4dce0"

S = "${WORKDIR}/git"

KERNEL_CC_append = " ${TOOLCHAIN_OPTIONS}"
KERNEL_LD_append = " ${TOOLCHAIN_OPTIONS}"
KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

KERNEL_DEFCONFIG ?= "defconfig"
DELTA_KERNEL_DEFCONFIG = "lsdk.config"

do_merge_delta_config[dirs] = "${B}"

do_merge_delta_config() {
    # create config with make config
    oe_runmake  -C ${S} O=${B} ${KERNEL_DEFCONFIG}

    # add config fragments
    for deltacfg in ${DELTA_KERNEL_DEFCONFIG}; do
        oe_runmake  -C ${S} O=${B} ${deltacfg}
    done
    cp .config ${WORKDIR}/defconfig
}
addtask merge_delta_config before do_configure after do_patch

COMPATIBLE_MACHINE = "(tqmls1012al-mbls1012al)"

