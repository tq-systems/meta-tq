FILESEXTRAPATHS_prepend := "${THISDIR}/lmsensors-config:"
SRC_URI += "file://mba8x.conf"

do_install_append() {
    case ${MACHINE} in
        tqma8qm-*-mba8x) install -m 0644 ${WORKDIR}/mba8x.conf ${D}${sysconfdir}/sensors.d
                         ;;
        tqma8mp-*-mba8mpxl) install -m 0644 ${WORKDIR}/mba8mpxl.conf ${D}${sysconfdir}/sensors.d
                         ;;
    esac
}

# libsensors configuration file
FILES_${PN}-libsensors += "${sysconfdir}/sensors.d/*"
