###############################################################################
# Attention: poky-tiny has a hard assignment:
# PREFERRED_PROVIDER_virtual/kernel = "linux-yocto-tiny"
# this will break our boards, preventing to use own kernel recipes, branches
# etc. To workaround this, store the value before inclusion, unset after
# inclusion and weak assign after inclusion. This allows all sort of reassignment
# from distro or environment and leaves the default.
###############################################################################
SPAETZLE_DEFAULT_KERNEL := "${PREFERRED_PROVIDER_virtual/kernel}"
require conf/distro/poky-tiny.conf
unset PREFERRED_PROVIDER_virtual/kernel
PREFERRED_PROVIDER_virtual/kernel ??= "${SPAETZLE_DEFAULT_KERNEL}"

require tq-common.inc

DISTRO_FEATURES += "tiny"
DISTRO_SHORT_NAME = "spaetzle"

# Make sure we have sysvinit in the list. Needed to get tasks injected by
# update-rc.d.bbclass. This will bring in the initscripts from different
# packages
DISTRO_FEATURES_append = " sysvinit"

# We have some images that can't be built with spaetzle
PNBLACKLIST[tq-image-weston] = "not buildable with spaetzle: use tq-image-small"
PNBLACKLIST[tq-image-qt5] = "not buildable with spaetzle: use tq-image-small"

# tinification of tq-common.inc (mostly similar to init-manager-mdev-busybox.inc).
# Although it is used as default assigned value from poky-tiny.conf documentation
# says it is good to set explicitely
INIT_MANAGER = "mdev-busybox"

# some modification of settings from INIT_MANAGER
# use eudev instead of mdev
VIRTUAL-RUNTIME_dev_manager = "eudev"
VIRTUAL-RUNTIME_login_manager = "busybox"
VIRTUAL-RUNTIME_init_manager = "busybox"
VIRTUAL-RUNTIME_initscripts = "initscripts"
VIRTUAL-RUNTIME_keymaps = "keymaps"

# force removal of systemd from DISTRO_FEATURES
# take care when updating yocto, at the moment sysvinit must not be removed,
# see above
DISTRO_FEATURES_BACKFILL_CONSIDERED_append = " systemd"

IMAGE_FSTYPES_append = "${@bb.utils.contains('MACHINE_FEATURES', 'ubi', ' ubi', '', d)}"
