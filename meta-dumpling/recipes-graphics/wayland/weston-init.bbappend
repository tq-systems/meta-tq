FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://wayland_display.sh"

do_install:append() {
    install -Dm0755 ${WORKDIR}/wayland_display.sh ${D}${sysconfdir}/profile.d/wayland_display.sh
}

FILES:${PN} += "\
    ${sysconfdir}/profile.d/wayland_display.sh \
"

# When using imx-gpu-viv to 6.4.11.p1.2 the option changed to boolean
# Applies only to i.MX8 as i.MX6 uses different driver,
# thus requiring 'use-g2d=1'
INI_UNCOMMENT_USE_G2D:mx8-nxp-bsp:imxgpu2d = "use-g2d=true"
