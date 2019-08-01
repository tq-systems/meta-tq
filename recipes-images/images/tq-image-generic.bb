require recipes-core/images/core-image-minimal.bb

SUMMARY =  "This is a generic image for TQ SOM with some test / debug features."

DESCRIPTION = "Demo image based on core-image-minimal and essential packages \
for the machine. This creates a large image and includes also debug tools, not \
directly suitable for production - also from the aspect of security."

IMAGE_LINGUAS_append = " en-us "

LICENSE = "MIT"

inherit distro_features_check

IMAGE_INSTALL += "\
    packagegroup-base \
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
IMAGE_FEATURES += " ssh-server-openssh"
