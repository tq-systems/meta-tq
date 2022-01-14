LICENSE = "GPLv2"

DEPENDS += "lzop-native bc-native openssl-native"

inherit kernel

# empty initialized from kernel.bbclass
KERNEL_LOCALVERSION = "-tq"

####
# kernel.bbclass unconditionally creates empty .scmversion files in ${B} and ${S}.
# This prevents using CONFIG_LOCALVERSION_AUTO without additional provisioning
# instead fiddling with .scmversion file we fix this using a localversion file
# in build directory
####
do_configure_append() {
    head=$(git --git-dir=${S}/.git rev-parse --short HEAD 2> /dev/null)
    printf "%s%s%s" "${KERNEL_LOCALVERSION}" +g ${head} > ${B}/localversion-tq
}

S = "${WORKDIR}/git"
