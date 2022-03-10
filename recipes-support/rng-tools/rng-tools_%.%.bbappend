FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "\
    file://check_hwrng \
    file://default \
    file://rngd.start-check.conf \
"

# make sure we do not miss it for kernel / hardware without functional /dev/hwrng
PACKAGECONFIG_append = " libjitterentropy"

do_install_append() {
    install -Dm 755 ${WORKDIR}/check_hwrng ${D}${bindir}/check_hwrng

    # Install systemd service override file which will be merged into regular one
    install -Dm 0644 ${WORKDIR}/rngd.start-check.conf \
                ${D}${systemd_system_unitdir}/rngd.service.d/start-check.conf
}

FILES_${PN} += "${systemd_system_unitdir}/rngd.service.d/start-check.conf"
