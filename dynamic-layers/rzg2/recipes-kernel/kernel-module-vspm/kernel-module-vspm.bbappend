FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append := " file://0001-HACK-rcar_gen3-explicit-path-variable-instead-of-PWD.patch"

do_compile() {
    VSPM_MODULE_PATH=${S}/${VSPM_DRV_DIR}/drv
    cd ${VSPM_MODULE_PATH}
    make all VSPM_MODULE_PATH=${VSPM_MODULE_PATH}
}
