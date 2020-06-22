LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

DEPENDS += "lzop-native bc-native openssl-native"
#
# append linux-mainline if we provide mainline kernel versions
#
PROVIDES += "linux-imx linux-tq-imx"

inherit kernel

require ${@bb.utils.contains("BBFILE_COLLECTIONS", "freescale-layer", 'linux-imx-tq-conditional.inc', '', d)}

# name the intree defconfig file
KERNEL_TREE_DEFCONFIG ??= ""

# returns and .cfg filenames from SRC_URI
def find_cfgs(d):
    sources=src_patches(d, True)
    sources_list=[]
    for s in sources:
        base, ext = os.path.splitext(os.path.basename(s))
        if ext and ext in [".cfg"]:
            sources_list.append(os.path.join(d.getVar("WORKDIR", True), (base + ext)))
    return sources_list

do_configure_prepend() {
    defconfig="${WORKDIR}/defconfig"
    config="${B}/.config"

    # force rebuilding
    rm -rf $config

    # if we have an intree defconfig, create a .config - has to be done in the source
    # dir but the final .config is needed in build dir. Source dir needs to be clean
    # just before the compile step.
    # We check this befor a defconfig in workdir since this could be supplied
    # automagically by some basic kernel classes
    if [ ! -z "${KERNEL_TREE_DEFCONFIG}" ]; then
        bbnote "Using intree defconfig: ${KERNEL_TREE_DEFCONFIG}"
        oe_runmake -C ${S} ${KERNEL_TREE_DEFCONFIG}
        cp ${S}/.config ${config}
        oe_runmake -C ${S} mrproper
    elif [ -f "$defconfig" ]; then
        bbnote "Using defconfig from SRC_URI"
        cp -f "$defconfig" "$config"
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
        bbnote "combining kconfig $fragments into $config"
        # Change directory to WORKDIR, because the fragments are located there
        # and filenames in variable $fragments are not absolute.
        # Use subshell to avoid changing the work directory of current shell.
        (cd "${WORKDIR}" && ${S}/scripts/kconfig/merge_config.sh -m -O "${B}" "$config" $fragments)
    fi
}

S = "${WORKDIR}/git"
