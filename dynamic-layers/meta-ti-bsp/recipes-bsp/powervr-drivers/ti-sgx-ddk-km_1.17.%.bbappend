FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "\
    file://0001-ti-sgx-ddk-km-properly-handle-more-OE-YP-compiler-pr.patch \
    file://0001-pvr_linux_fence-enable-unconditional-PVR_BUILD_FOR_R.patch \
"

MACHINE_KERNEL_PR:append = "v"
BRANCH = "ti-img-sgx/${PV}/k5.4"
SRCREV = "50c1ec2308b9f64488d252ac55d65b51a0dfe287"
