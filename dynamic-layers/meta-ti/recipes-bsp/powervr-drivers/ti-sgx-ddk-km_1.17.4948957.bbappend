FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0002-ti-sgx-ddk-km-properly-handle.patch"

MACHINE_KERNEL_PR_append = "v"
BRANCH = "ti-img-sgx/${PV}/k5.4"
SRCREV = "50c1ec2308b9f64488d252ac55d65b51a0dfe287"