LICENSE = "MIT"

require tq-image-base.inc

IMAGE_INSTALL_append = "\
    coreutils \
    ${@bb.utils.contains('MACHINE_FEATURES', 'camera', ' packagegroup-v4l2', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'vpu', ' packagegroup-v4l2', '', d)} \
    packagegroup-testutils \
"

# force openssh to prevent conflict with dropbear
IMAGE_FEATURES_append = " ssh-server-openssh"
IMAGE_FEATURES_remove = "ssh-server-dropbear"
