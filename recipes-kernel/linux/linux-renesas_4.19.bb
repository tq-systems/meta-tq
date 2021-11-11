DESCRIPTION = "Linux kernel for the RZG2 based board"

require recipes-kernel/linux/linux-yocto.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/:"
COMPATIBLE_MACHINE = "(tqmarzg2n_b-mbarzg2x|tqmarzg2m_e-mbarzg2x|tqmarzg2m_aa-mbarzg2x|tqmarzg2h_c-mbarzg2x)"

SRC_URI = "${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"
SRCBRANCH = "TQMaRZG2x-linux-v4.19.140-VLP_1.0.6"
SRCREV = "663fd93cd9e4b62d4efc054ed1943409fd18d34c"


LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
LINUX_VERSION ?= "4.19.140"

PV = "${LINUX_VERSION}+git${SRCPV}"
PR = "r1"

KBUILD_DEFCONFIG = "defconfig"
KCONFIG_MODE = "alldefconfig"

# Install USB3.0 firmware to rootfs
USB3_FIRMWARE_V2 = "https://git.kernel.org/pub/scm/linux/kernel/git/firmware/linux-firmware.git/plain/r8a779x_usb3_v2.dlmem;md5sum=645db7e9056029efa15f158e51cc8a11"
USB3_FIRMWARE_V3 = "https://git.kernel.org/pub/scm/linux/kernel/git/firmware/linux-firmware.git/plain/r8a779x_usb3_v3.dlmem;md5sum=687d5d42f38f9850f8d5a6071dca3109"

SRC_URI_append = " \
    ${USB3_FIRMWARE_V2} \
    ${USB3_FIRMWARE_V3} \
    ${@bb.utils.contains('MACHINE_FEATURES','usb3','file://usb3.cfg','',d)} \
"

SRC_URI_append = " \
    file://touch.cfg \
    file://qspi.cfg \
    file://tqmarzg2.cfg \
    file://mbarzg2.cfg \
"

do_download_firmware () {
    install -m 755 ${WORKDIR}/r8a779x_usb3_v*.dlmem ${STAGING_KERNEL_DIR}/firmware
}

addtask do_download_firmware after do_configure before do_compile

# Fix race condition, which can causes configs in defconfig file be ignored
#do_kernel_configme[depends] += "virtual/${TARGET_PREFIX}binutils:do_populate_sysroot"
#do_kernel_configme[depends] += "virtual/${TARGET_PREFIX}gcc:do_populate_sysroot"
#do_kernel_configme[depends] += "bc-native:do_populate_sysroot bison-native:do_populate_sysroot"

# Fix error: openssl/bio.h: No such file or directory
DEPENDS += "openssl-native"
