LICENSE = "MIT"

require tq-image-base.inc

IMAGE_INSTALL:append = "\
    coreutils \
    ${@bb.utils.contains('MACHINE_FEATURES', 'camera', ' packagegroup-v4l2', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'vpu', ' packagegroup-v4l2', '', d)} \
"

# force openssh to prevent conflict with dropbear
IMAGE_FEATURES:append = " ssh-server-openssh"
IMAGE_FEATURES:remove = "ssh-server-dropbear"
