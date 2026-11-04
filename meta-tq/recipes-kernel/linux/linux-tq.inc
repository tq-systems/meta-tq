LICENSE = "GPL-2.0-only"

# linux-tq / linux-rt-tq are TQ-maintained branches of the upstream kernel.
# Their BPN is unknown in the NVD; map it to the canonical upstream CPE.
CVE_PRODUCT = "linux:linux_kernel"

DEPENDS += "lzop-native bc-native openssl-native"

PROVIDES += "virtual/kernel"

inherit kernel-yocto kernel

# Set the PV to the correct kernel version to satisfy the kernel version sanity check
LINUX_VERSION ??= "invalid"
PV = "${LINUX_VERSION}+git"

# This comment was copied from linux-imx recipes in meta-freescale:
# Tell to kernel class that we would like to use our defconfig to configure the kernel.
# Otherwise, the --allnoconfig would be used per default which leads to mis-configured
# kernel.
#
# This behavior happens when a defconfig is provided, the kernel-yocto configuration
# uses the filename as a trigger to use a 'allnoconfig' baseline before merging
# the defconfig into the build.
#
# If the defconfig file was created with make_savedefconfig, not all options are
# specified, and should be restored with their defaults, not set to 'n'.
# To properly expand a defconfig like this, we need to specify: KCONFIG_MODE = "--alldefconfig"
# in the kernel recipe include.
KCONFIG_MODE = "--alldefconfig"

####
# kernel.bbclass unconditionally creates .scmversion files in ${B} and ${S}.
# starting with 6.3 these are not used anymore. When building from git
# CONFIG_LOCALVERSION_AUTO therefore adds the SCM version unconditionally.
# When the kernel is patched git will be used. As result the kernel build system
# generates different version strings everytime patching is executed even if patches
# do not change.
# To simplify handling and to generate a reproducible version string a localversion file
# is generated in the shared source directory which works with older and newer kernel
# versions.
#
# Multiple before/after tasks are listed, so the order is correct for recipes with
# and without linux-yocto.
# CONFIG_LOCALVERSION_AUTO will be disabled unconditionally using an append to the
# configure task to prevent doubled entries in the kernel version string.
####
KERNEL_LOCALVERSION = "-tq"

python do_recipe_qa:append() {
    msrev = d.getVar('SRCREV_machine')
    srev = d.getVar('SRCREV')

    if msrev and srev != "INVALID":
        oe.qa.handle_error(
            "src-uri-bad",
            "%s: SRCREV and SRCREV_machine set at the same time! This is a mistake." % d.getVar('PN'),
            d
        )
}

do_set_localversion_tq[dirs] += "${STAGING_KERNEL_DIR} ${WORKDIR}"
do_set_localversion_tq() {
    if [ "${SRCREV}" = "INVALID" ]; then
        hash=${SRCREV_machine}
    else
        hash=${SRCREV}
    fi
    if [ "$hash" = "AUTOINC" ]; then
        branch=$(git --git-dir=${STAGING_KERNEL_DIR}/.git  symbolic-ref --short -q HEAD)
        head=$(git --git-dir=${STAGING_KERNEL_DIR}/.git rev-parse --verify --short --quiet "origin/${branch}^{commit}" || echo 'unknown')
    else
        head=$(git --git-dir=${STAGING_KERNEL_DIR}/.git rev-parse --verify --short --quiet "${hash}^{commit}" || echo 'unknown')
    fi
    patches=$(git --git-dir=${STAGING_KERNEL_DIR}/.git rev-list --count ${head}..HEAD 2> /dev/null || echo "0")
    printf -- "-g%s-p%s\n" "${head}" "${patches}" > ${STAGING_KERNEL_DIR}/localversion-tq
}
addtask set_localversion_tq before do_configure after do_patch do_symlink_kernsrc do_kernel_metadata

do_configure:append() {
    sed -i -e "/CONFIG_LOCALVERSION_AUTO[ =]/d" ${B}/.config
    echo "# CONFIG_LOCALVERSION_AUTO is not set" >> ${B}/.config
}

do_install:append(){
    if [ -n "${KMETA}" ]; then
        rm -rf "${STAGING_KERNEL_DIR}/${KMETA}"
    fi
}

S = "${WORKDIR}/git"

# copied from linux-yocto.inc
# Skip processing of this recipe if it is not explicitly specified as the
# PREFERRED_PROVIDER for virtual/kernel. This avoids network access required
# by the use of AUTOREV SRCREVs, which are the default for this recipe.
python () {
    if d.getVar("KERNEL_PACKAGE_NAME") == "kernel" and d.getVar("PREFERRED_PROVIDER_virtual/kernel") != d.getVar("PN"):
        d.delVar("BB_DONT_CACHE")
        raise bb.parse.SkipRecipe("Set PREFERRED_PROVIDER_virtual/kernel to %s to enable it" % (d.getVar("PN")))
}
