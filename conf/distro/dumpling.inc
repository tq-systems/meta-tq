require conf/distro/poky.conf
require tq-common.inc

DISTRO_SHORT_NAME = "dumpling"
DISTRO_FEATURES_DEFAULT:remove = "argp irda pcmcia zeroconf x11 bluetooth"
DISTRO_FEATURES = "${DISTRO_FEATURES_DEFAULT} opengl systemd"

# add some locales per default
IMAGE_LINGUAS:append = " de-de en-us"

# point some runtime providers to full fledged packages
VIRTUAL-RUNTIME_base-utils-hwclock ?= "util-linux-hwclock"

# strip some unused stuff to lower dependencies, work in progress:

# we do not have firewire support
PACKAGECONFIG_pn_gstreamer1.0-plugin-good:remove = "dv1394"
# at the moment gtk is not used
PACKAGECONFIG_pn_gstreamer1.0-plugin-good:remove = "gtk"

# TODO: cairo and gdk-pixbuf are default on - if not gstreamer dependencies
# can be removed.
