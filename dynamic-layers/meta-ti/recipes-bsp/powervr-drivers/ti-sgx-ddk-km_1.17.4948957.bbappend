FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-ti-sgx-ddk-km-properly-handle-arm-poky-linux-gnueabi.patch"

EXTRA_DIRECTORY = "${nonarch_base_libdir}/modules/4.19.93-00050-g9a728271801f-dirty/"
EXTRA_FILE = "extrapvrsrvkm.ko"

do_install_append () {
	install -d ${D}${EXTRA_DIRECTORY}
	ln -srf ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/pvrsrvkm.ko ${D}${EXTRA_DIRECTORY}${EXTRA_FILE}
}

FILES_${PN} += "${EXTRA_DIRECTORY}"
FILES_${PN} += "${EXTRA_DIRECTORY}${EXTRA_FILE}"
