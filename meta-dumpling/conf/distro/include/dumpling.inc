require conf/distro/poky.conf
require tq-common.inc

DISTRO_SHORT_NAME = "dumpling"
DISTRO_FEATURES_DEFAULT:remove = "argp irda pcmcia zeroconf x11"
DISTRO_FEATURES = "${DISTRO_FEATURES_DEFAULT}"
DISTRO_FEATURES += "npu"
DISTRO_FEATURES += "opengl"
DISTRO_FEATURES += "pam"
DISTRO_FEATURES += "pni-names"
DISTRO_FEATURES += "ppp"
DISTRO_FEATURES += "vulkan"
DISTRO_FEATURES += "wayland"
DISTRO_FEATURES += "wifi"

# add some locales per default
IMAGE_LINGUAS:append = " de-de en-us"

# point some runtime providers to full fledged packages
PREFERRED_PROVIDER_virtual/base-utils = "packagegroup-core-base-utils"
VIRTUAL-RUNTIME_base-utils = "packagegroup-core-base-utils"
VIRTUAL-RUNTIME_base-utils-hwclock = "util-linux-hwclock"
VIRTUAL-RUNTIME_base-utils-lscpu = "util-linux-lscpu"

# Conflicts with systemd-networkd
SYSTEMD_AUTO_ENABLE:dhcpcd = "disable"
