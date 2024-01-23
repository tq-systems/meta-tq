require conf/distro/poky.conf
require tq-common.inc

DISTRO_SHORT_NAME = "dumpling"
DISTRO_FEATURES_DEFAULT:remove = "argp irda pcmcia zeroconf x11"
DISTRO_FEATURES = "${DISTRO_FEATURES_DEFAULT} npu opengl systemd"

# add some locales per default
IMAGE_LINGUAS:append = " de-de en-us"

# point some runtime providers to full fledged packages
PREFERRED_PROVIDER_virtual/base-utils = "packagegroup-core-base-utils"
VIRTUAL-RUNTIME_base-utils = "packagegroup-core-base-utils"
VIRTUAL-RUNTIME_base-utils-hwclock = "util-linux-hwclock"
VIRTUAL-RUNTIME_base-utils-lscpu = "util-linux-lscpu"

# strip some unused stuff to lower dependencies, work in progress:

# we do not have firewire support
PACKAGECONFIG_pn_gstreamer1.0-plugin-good:remove = "dv1394"
# at the moment gtk is not used
PACKAGECONFIG_pn_gstreamer1.0-plugin-good:remove = "gtk"

# Conflicts with systemd-networkd
SYSTEMD_AUTO_ENABLE:dhcpcd = "disable"

# TODO: cairo and gdk-pixbuf are default on - if not gstreamer dependencies
# can be removed.
