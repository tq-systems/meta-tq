require u-boot-tq.inc

DESCRIPTION = "U-boot for TQ-Systems TI AM65 based modules"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

SRCBRANCH = "TQMa65xx-ti-u-boot-v2020.01"
SRCREV = "95dd2ab6b44a540c3d9ef715da1e264cf283f2a0"

DEPENDS += "dtc-native bc-native lzop-native flex-native bison-native"

COMPATIBLE_MACHINE = "tqma65xx"

UBOOT_SUFFIX ?= "img"
SPL_BINARY ?= "tispl.bin"

PACKAGECONFIG[atf] = "ATF=${STAGING_DIR_HOST}/firmware/bl31.bin,,trusted-firmware-a"
PACKAGECONFIG[optee] = "TEE=${STAGING_DIR_HOST}${nonarch_base_libdir}/firmware/bl32.bin,,optee-os"
PACKAGECONFIG_append_aarch64 = " atf optee"
EXTRA_OEMAKE += "${PACKAGECONFIG_CONFARGS}"
