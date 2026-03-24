FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI += "\
    file://mba8mp-ras314.rules \
"

do_install:append:tqma8mpxl-mba8mp-ras314() {
    install -Dm 644 ${UNPACKDIR}/mba8mp-ras314.rules \
        ${D}${nonarch_base_libdir}/udev/rules.d/10-mba8mp-ras314.rules
}
