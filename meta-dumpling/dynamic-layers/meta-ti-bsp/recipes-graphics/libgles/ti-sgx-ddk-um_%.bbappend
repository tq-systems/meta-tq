FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI += "file://pvrsrvkm.rules"

PACKAGECONFIG ??= "udev"
PACKAGECONFIG[udev] = ",,,udev"

do_install:append () {
    local without_sysvinit=${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'false', 'true', d)}
    local with_udev=${@bb.utils.contains('PACKAGECONFIG', 'udev', 'true', 'false', d)}

    # Delete initscript if it is not needed
    if $without_sysvinit || $with_udev; then
        rm -rf ${D}/etc/init.d
    fi

    if $with_udev; then
        install -m644 -D ${WORKDIR}/pvrsrvkm.rules ${D}${nonarch_base_libdir}/udev/rules.d/80-pvrsrvkm.rules
    fi
}
FILES:${PN} += "${nonarch_base_libdir}/udev/rules.d"
