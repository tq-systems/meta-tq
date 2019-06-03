require recipes-core/images/core-image-minimal.bb

SUMMARY =  "This is a generic image for TQ SOM with some test / debug features."

IMAGE_LINGUAS = " en-us "

LICENSE = "MIT"

inherit distro_features_check

IMAGE_INSTALL += "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', ' packagegroup-systemd', '', d)} \
    packagegroup-hwutils \
    packagegroup-fsutils \
    packagegroup-netutils \
    packagegroup-sysutils \
    packagegroup-testutils \
    ${@bb.utils.contains('MACHINE_FEATURES', 'can', ' packagegroup-can', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'alsa', ' packagegroup-audio', '', d)} \
"

# force openssh to prevent conflict with dropbear
EXTRA_IMAGE_FEATURES += " ssh-server-openssh"
