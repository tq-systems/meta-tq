DESCRIPTION = "systemd packages"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS_${PN} = " \
    systemd \
    systemd-machine-units \
    systemd-serialgetty \
    "
