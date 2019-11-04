LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

DEPENDS += "lzop-native bc-native openssl-native"
#
# append linux-mainline if we provide mainline kernel versions
#
PROVIDES += "linux-imx linux-tq-imx"

# Put a local version until we have a true SRCREV to point to
LOCALVERSION ?= ""
SCMVERSION ?= "y"

inherit kernel
inherit cml1

# todo: make it optional
require ${@bb.utils.contains("BBFILE_COLLECTIONS", "meta-freescale", 'linux-tq-imx-conditional.inc', '', d)}

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

S = "${WORKDIR}/git"
