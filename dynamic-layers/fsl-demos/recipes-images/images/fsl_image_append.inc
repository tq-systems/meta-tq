# extends fsl-image-<multimedia..*|gui> with additional tools

IMAGE_LINGUAS_append = " en-us "

inherit distro_features_check

# normal tq systems packages
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

IMAGE_INSTALL_append_imxgpu3d      = " glmark2"
IMAGE_INSTALL_append_imxdrm        = " kmscube"
