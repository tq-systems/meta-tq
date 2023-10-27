DESCRIPTION =  "Kernel drivers for the PowerVR SGX chipset found in the TI SoCs (legacy kernel version)"
HOMEPAGE = "https://git.ti.com/graphics/omap5-sgx-ddk-linux"
LICENSE = "MIT | GPL-2.0-only"
LIC_FILES_CHKSUM = "file://eurasia_km/README;beginline=13;endline=22;md5=74506d9b8e5edbce66c2747c50fcef12"

inherit module

PROVIDES = "virtual/gpudriver"

COMPATIBLE_MACHINE = "ti33x|ti43x|omap-a15|am65xx"

MACHINE_KERNEL_PR:append = "v"
PR = "${MACHINE_KERNEL_PR}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "virtual/kernel"

SRC_URI = "git://git.ti.com/git/graphics/omap5-sgx-ddk-linux.git;protocol=https;branch=${BRANCH}"

S = "${WORKDIR}/git"

TARGET_PRODUCT:omap-a15 = "jacinto6evm"
TARGET_PRODUCT:ti33x = "ti335x"
TARGET_PRODUCT:ti43x = "ti437x"
TARGET_PRODUCT:am65xx = "ti654x"
PVR_BUILD = "release"
PVR_WS = "nulldrmws"

EXTRA_OEMAKE += 'KERNELDIR="${STAGING_KERNEL_DIR}" BUILD=${PVR_BUILD} TARGET_PRODUCT=${TARGET_PRODUCT} WINDOW_SYSTEM=${PVR_WS}'

do_compile:prepend() {
    cd ${S}/eurasia_km/eurasiacon/build/linux2/omap_linux
}

do_install() {
    make -C ${STAGING_KERNEL_DIR} M=${B}/eurasia_km/eurasiacon/binary_omap_linux_${PVR_WS}_${PVR_BUILD}/target_armhf/kbuild INSTALL_MOD_PATH=${D}${root_prefix} PREFIX=${STAGING_DIR_HOST} modules_install
}

do_install:am65xx() {
    make -C ${STAGING_KERNEL_DIR} M=${B}/eurasia_km/eurasiacon/binary_omap_linux_${PVR_WS}_${PVR_BUILD}/target_aarch64/kbuild INSTALL_MOD_PATH=${D}${root_prefix} PREFIX=${STAGING_DIR_HOST} modules_install
}

RRECOMMENDS:${PN} += "ti-sgx-ddk-um"

# TQ additions

OVERRIDES =. "ti-sgx-ddk-km-${TI_SGX_DDK_KM_KERNVER}:"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/linux-${TI_SGX_DDK_KM_KERNVER}:"

SRC_URI:append:ti-sgx-ddk-km-5.4 = "\
    file://0001-ti-sgx-ddk-km-properly-handle-more-OE-YP-compiler-pr.patch \
    file://0001-pvr_linux_fence-enable-unconditional-PVR_BUILD_FOR_R.patch \
"
MACHINE_KERNEL_PR:append:ti-sgx-ddk-km-5.4 = "v"
BRANCH:ti-sgx-ddk-km-5.4 = "ti-img-sgx/1.17.4948957/k5.4"
SRCREV:ti-sgx-ddk-km-5.4 = "50c1ec2308b9f64488d252ac55d65b51a0dfe287"

SRC_URI:append:ti-sgx-ddk-km-5.10 = "\
    file://0001-ti-sgx-ddk-km-properly-handle-more-OE-YP-compiler-pr.patch \
"
BRANCH:ti-sgx-ddk-km-5.10 = "ti-img-sgx/1.17.4948957/k5.10"
SRCREV:ti-sgx-ddk-km-5.10 = "eda7780bfd5277e16913c9bc0b0e6892b4e79063"

TI_SGX_DDK_KM_LEGACY_RECIPE = "1"

python () {
    if d.getVar('TI_SGX_DDK_KM_KERNVER') != '5.4' and d.getVar('TI_SGX_DDK_KM_KERNVER') != '5.10':
        raise bb.parse.SkipRecipe('TI_SGX_DDK_KM_KERNVER is not 5.4 or 5.10')
}
