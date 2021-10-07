LICENSE = "MIT"

require tq-image-base.inc

IMAGE_INSTALL += "\
    ${@bb.utils.contains('MACHINE_FEATURES', 'camera', ' packagegroup-camera', '', d)} \
    packagegroup-testutils \
"

# force openssh to prevent conflict with dropbear
IMAGE_FEATURES_append = " ssh-server-openssh"
IMAGE_FEATURES_remove = " ssh-server-dropbear"
