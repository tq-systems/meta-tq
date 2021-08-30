FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0002-ti-sgx-ddk-km-properly-handle.patch"

EXTRA_DIRECTORY = "${nonarch_base_libdir}/modules/4.19.93-00050-g9a728271801f-dirty/"
EXTRA_FILE = "extrapvrsrvkm.ko"

do_install_append () {
	install -d ${D}${EXTRA_DIRECTORY}
	ln -srf ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/pvrsrvkm.ko ${D}${EXTRA_DIRECTORY}${EXTRA_FILE}
}

FILES_${PN} += "${EXTRA_DIRECTORY}"
FILES_${PN} += "${EXTRA_DIRECTORY}${EXTRA_FILE}"

MACHINE_KERNEL_PR_append = "v"
BRANCH = "ti-img-sgx/${PV}/k5.4"
SRCREV = "50c1ec2308b9f64488d252ac55d65b51a0dfe287"