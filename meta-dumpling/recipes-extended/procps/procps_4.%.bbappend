FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://90-printk.conf"

do_install:append () {
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -m 0644 ${UNPACKDIR}/90-printk.conf ${D}${sysconfdir}/sysctl.d/90-printk.conf
    else
        cat ${UNPACKDIR}/90-printk.conf >> ${D}${sysconfdir}/sysctl.conf
    fi
}
