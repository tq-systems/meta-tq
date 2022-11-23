TI_SGX_DDK_KM_KERNVER ??= "5.4"
OVERRIDES =. "ti-sgx-ddk-km-${TI_SGX_DDK_KM_KERNVER}:"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/linux-${TI_SGX_DDK_KM_KERNVER}:"

SRC_URI:append:ti-sgx-ddk-km-5.4 = "\
    file://0001-ti-sgx-ddk-km-properly-handle-more-OE-YP-compiler-pr.patch \
    file://0001-pvr_linux_fence-enable-unconditional-PVR_BUILD_FOR_R.patch \
"
SRC_URI:append:ti-sgx-ddk-km-5.10 = "\
    file://0001-ti-sgx-ddk-km-properly-handle-more-OE-YP-compiler-pr.patch \
"

MACHINE_KERNEL_PR:append:ti-sgx-ddk-km-5.4 = "v"
BRANCH:ti-sgx-ddk-km-5.4 = "ti-img-sgx/${PV}/k5.4"
SRCREV:ti-sgx-ddk-km-5.4 = "50c1ec2308b9f64488d252ac55d65b51a0dfe287"
