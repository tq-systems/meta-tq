FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append := " file://0001-HACK-rcar_gen3-explicit-path-variable-instead-of-PWD.patch"

do_compile() {
    VSPMIF_MODULE_PATH=${S}/${VSPMIF_DRV_DIR}/drv
    cd ${VSPMIF_MODULE_PATH}
    make all VSPMIF_MODULE_PATH=${VSPMIF_MODULE_PATH}
}
