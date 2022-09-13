FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://wayland_display.sh"

do_install:append() {
    install -Dm0755 ${WORKDIR}/wayland_display.sh ${D}${sysconfdir}/profile.d/wayland_display.sh
}

FILES:${PN} += "\
    ${sysconfdir}/profile.d/wayland_display.sh \
"
