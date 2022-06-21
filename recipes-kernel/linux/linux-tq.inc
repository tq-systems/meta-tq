LICENSE = "GPL-2.0-only"

DEPENDS += "lzop-native bc-native openssl-native"

PROVIDES += "virtual/kernel"

inherit kernel-yocto kernel

# empty initialized from kernel.bbclass
KERNEL_LOCALVERSION = "-tq"

# Set the PV to the correct kernel version to satisfy the kernel version sanity check
LINUX_VERSION ??= "invalid"
PV = "${LINUX_VERSION}+git${SRCPV}"

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
# To properly expand a defconfig like this, we need to specify: KCONFIG_MODE="--alldefconfig"
# in the kernel recipe include.
KCONFIG_MODE="--alldefconfig"

####
# kernel.bbclass unconditionally creates empty .scmversion files in ${B} and ${S}.
# This prevents using CONFIG_LOCALVERSION_AUTO without additional provisioning
# instead fiddling with .scmversion file we fix this using a localversion file
# in build directory
####
do_configure:append() {
    head=$(git --git-dir=${S}/.git rev-parse --short HEAD 2> /dev/null)
    printf "%s%s%s" "${KERNEL_LOCALVERSION}" +g ${head} > ${B}/localversion-tq
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
