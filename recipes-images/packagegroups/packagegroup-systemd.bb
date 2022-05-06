DESCRIPTION = "systemd packages"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS:${PN} = "\
    systemd \
    systemd-machine-units \
    systemd-serialgetty \
"
