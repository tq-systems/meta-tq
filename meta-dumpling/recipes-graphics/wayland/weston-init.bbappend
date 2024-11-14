FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://wayland_display.sh"

do_install:append() {
    install -Dm0755 ${WORKDIR}/wayland_display.sh ${D}${sysconfdir}/profile.d/wayland_display.sh
}

do_install:append:mx93-nxp-bsp() {
    # imx-pxp-g2d needs root access to some devices
    if [ "${@bb.utils.contains('PACKAGECONFIG', 'use-g2d', 'yes', 'no', d)}" = "yes" ]; then
        sed -i -e "s/User=weston/User=root/g" ${D}${systemd_system_unitdir}/weston.service
    fi
}

FILES:${PN} += "\
    ${sysconfdir}/profile.d/wayland_display.sh \
"

# When using imx-gpu-viv to 6.4.11.p1.2 the option changed to boolean
# Applies only to i.MX8 as i.MX6 uses different driver,
# thus requiring 'use-g2d=1'
INI_UNCOMMENT_USE_G2D:mx8-nxp-bsp:imxgpu2d = "use-g2d=true"
