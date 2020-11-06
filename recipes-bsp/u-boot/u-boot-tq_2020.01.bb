# UBOOT_LOCALVERSION can be set to add a tag to the end of the
# U-boot version string.  such as the commit id
def get_git_revision(p):
    import subprocess

    try:
        return subprocess.Popen("git rev-parse HEAD 2>/dev/null ", cwd=p, shell=True, stdout=subprocess.PIPE, universal_newlines=True).communicate()[0].rstrip()
    except OSError:
        return None

COMPATIBLE_MACHINE = "tqma65xx"

UBOOT_SUFFIX ?= "img"
SPL_BINARY ?= "tispl.bin"

require ${COREBASE}/meta/recipes-bsp/u-boot/u-boot.inc

inherit python3native

FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot:"

SUMMARY = "u-boot for TQ-Group TI AM65 based modules"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

SRCREV = "40e6ecf58fd231d4d4cdf33d95875086e229cfab"
SRCBRANCH = "TQMa65xx-u-boot-v2020.01"

PV_append = "+git${SRCPV}"

# u-boot needs devtree compiler to parse dts files
DEPENDS += "dtc-native bc-native lzop-native flex-native bison-native"
DEPENDS_remove = "python-native"

PACKAGECONFIG[atf] = "ATF=${STAGING_DIR_HOST}/boot/bl31.bin,,arm-trusted-firmware"
PACKAGECONFIG[optee] = "TEE=${STAGING_DIR_HOST}/boot/bl32.bin,,optee-os"

# optee-os is not a direct dependency, do not enable optee by default
PACKAGECONFIG_append_tqma65xx= " atf optee"
EXTRA_OEMAKE += "${PACKAGECONFIG_CONFARGS}"

PROVIDES += "u-boot"
PKG_${PN} = "u-boot"
PKG_${PN}-dev = "u-boot-dev"
PKG_${PN}-dbg = "u-boot-dbg"

S = "${WORKDIR}/git"

TOOLCHAIN = "gcc"
