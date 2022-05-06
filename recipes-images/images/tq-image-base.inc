LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

IMAGE_INSTALL:append = "\
    packagegroup-base \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', ' packagegroup-systemd', '', d)} \
    packagegroup-hwutils \
    packagegroup-fsutils \
    packagegroup-netutils \
    packagegroup-sysutils \
    ${@bb.utils.contains('MACHINE_FEATURES', 'can', 'packagegroup-can', '', d)} \
    ${@bb.utils.contains('COMBINED_FEATURES', 'alsa', 'packagegroup-audio', '', d)} \
    ${@bb.utils.contains('COMBINED_FEATURES', 'wifi', 'packagegroup-wifi', '',d)} \
"
