SUMMARY = "Linux kernel for TQ-Group Freescale i.MX based modules"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

DEPENDS += "lzop-native bc-native"
#
# append linux-mainline if we provide mainline kernel versions
#
PROVIDES = "virtual/kernel"

inherit kernel
inherit cml1

# linux-tq.inc

KERNEL_TREE_DEFCONFIG ??= ""

# returns and .cfg filenames from SRC_URI
def find_cfgs(d):
    sources=src_patches(d, True)
    sources_list=[]
    for s in sources:
        base, ext = os.path.splitext(os.path.basename(s))
        if ext and ext in [".cfg"]:
            sources_list.append(s)
    return sources_list

do_configure_prepend() {
    defconfig="${WORKDIR}/defconfig"
    config="${B}/.config"

   rm -rf $config
   set -e

   if [ -f "$defconfig" ]; then
        bbnote "Using defconfig from SRC_URI"
        cp -f "$defconfig" "$config"
    elif [ ! -z "${KERNEL_TREE_DEFCONFIG}" ]; then
        bbnote "Using intree defconfig: ${KERNEL_TREE_DEFCONFIG}"
        oe_runmake -C ${S} ${KERNEL_TREE_DEFCONFIG}
   fi

    # translate the kconfig_mode into something that merge_config.sh
    # understands
        case ${KCONFIG_MODE} in
        *allnoconfig)
            config_flags="-n"
            ;;
       *alldefconfig)
            config_flags=""
            ;;
        *)
            if [ -f $defconfig ]; then
                config_flags="-n"
            fi
            ;;
    esac

    fragments="${@' '.join(find_cfgs(d))}"
    if test ! -z "$fragments"; then
        bbnote "combining kconfig fragments into .config"
        # Change directory to WORKDIR, because the fragments are located there
        # and filenames in variable $fragments are not absolute.
        # Use subshell to avoid changing the work directory of current shell.
        (cd "${WORKDIR}" && CFLAGS="${CFLAGS} ${TOOLCHAIN_OPTIONS}" ARCH=${ARCH} ${S}/scripts/kconfig/merge_config.sh -m -O "${B}" "$config" $fragments)
    fi
}

SRC_URI = "${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
           file://defconfig \
           file://disable-non-fsl-architecture.cfg \
           file://disable-non-used-network-devices.cfg \
           file://optimize-filesystem-selection.cfg \
           file://0001-aarch64-dt-rewrite-trees-for-TQMa8QX.patch \
           file://0002-arm64-dt-fsl-imx8qxp-tqma8qx-mba8qx-enable-ethernet.patch \
           "

SRCBRANCH = "TQMa8xx-bringup-imx_4.9.88_imx8qxp_beta2"
SRCREV = "618794a8fc3fd49bfe8b99f3bedea5cc6da6205c"

COMPATIBLE_MACHINE = "tqma8qx-mba8qx|tqma8qxa0-mba8qx"

S = "${WORKDIR}/git"
