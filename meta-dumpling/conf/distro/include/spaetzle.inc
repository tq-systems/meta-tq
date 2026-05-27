# SPDX-License-Identifier: MIT
#
# Copyright (c) 2024-2026 TQ-Systems GmbH <oss@ew.tq-group.com>, D-82229 Seefeld, Germany.
###############################################################################

###############################################################################
# Shared distribution definitions for spaetzle and derived distros
#
# Based on poky-tiny.conf from meta-poky to circumvent hard assignments:
# PREFERRED_PROVIDER_virtual/kernel = "linux-yocto-tiny"
# IMAGE_FSTYPES = "cpio.gz"
#
# This file needs to evaluated at updates of the distro definitions in poky
###############################################################################


# based on conf/distro/poky-tiny.conf:

require conf/distro/poky.conf
require conf/distro/include/gcsections.inc

DISTRO ?= "spaetzle"
DISTRO_NAME ?= "Spaetzle (TQ-Systems GmbH Spaetzle - a tiny example distribution)"
# This is needed for branding 'etc/issue[.net]' (figlet ascii graphics in recipe base-files)
DISTRO_SHORT_NAME = "spaetzle"
DISTROOVERRIDES = "poky:spaetzle"
TCLIBC = "musl"

FULL_OPTIMIZATION = "-Os"

# do not overwrite the kernel provider and version here

# empty this to prevent packages slipping in from poky
POKY_DEFAULT_EXTRA_RRECOMMENDS = ""

# as log as we do not use ncurses we do not care for wide character support
# ENABLE_WIDEC = "false"
# ENABLE_WIDEC:class-native = "true"

# Drop native language support. This removes the
# eglibc->bash->gettext->libc-posix-clang-wchar dependency.
USE_NLS = "no"
# As we don't have native language support, don't install locales into images
IMAGE_LINGUAS = ""

# Define a small set of distro features. A sane base is ext2 and pci
DISTRO_FEATURES = "ext2 pci"
# basic network support
DISTRO_FEATURES += "ipv4 ipv6"

# Make sure we have sysvinit in the list. Needed to get tasks injected by
# update-rc.d.bbclass. This will bring in the initscripts from different
# packages
DISTRO_FEATURES += "sysvinit"

# add a new "tiny" feature, maybe this will be supported in OE core in future, too
# this will give a chance to select / unselect in packagegroups and the like.
DISTRO_FEATURES += "tiny"

# The following features are not enabled, mostly for their dependencies
# that usually cause increase of rootfs size
# * alsa
# * usbgadget
# * wifi
# * bluetooth

# opt out all features from DISTRO_FEATURES_DEFAULTS
DISTRO_FEATURES_OPTED_OUT = "*"

DISTRO_FEATURES:class-native = "${POKY_DEFAULT_DISTRO_FEATURES}"
DISTRO_FEATURES:class-nativesdk = "${POKY_DEFAULT_DISTRO_FEATURES}"

# FIXME: Consider adding "modules" to MACHINE_FEATURES and using that in
# packagegroup-core-base to select modutils-initscripts or not.  Similar with "net" and
# netbase.

# do not overwrite the IMAGE_FSTYPES list here

# do not overwrite MACHINE_ESSENTIAL_EXTRA_RDEPENDS list here

# We have some images that can't be built with spaetzle
SKIP_RECIPE[build-appliance-image] = "not buildable with spaetzle"
SKIP_RECIPE[core-image-rt] = "not buildable with spaetzle"
SKIP_RECIPE[core-image-rt-sdk] = "not buildable with spaetzle"
SKIP_RECIPE[core-image-sato] = "not buildable with spaetzle"
SKIP_RECIPE[core-image-sato-dev] = "not buildable with spaetzle"
SKIP_RECIPE[core-image-sato-sdk] = "not buildable with spaetzle"
SKIP_RECIPE[core-image-x11] = "not buildable with spaetzle"
SKIP_RECIPE[core-image-weston] = "not buildable with spaetzle"

SKIP_RECIPE[tq-image-generic] = "not buildable with spaetzle: use tq-image-small"
SKIP_RECIPE[tq-image-weston] = "not buildable with spaetzle: use tq-image-small"
SKIP_RECIPE[tq-image-qt6] = "not buildable with spaetzle: use tq-image-small"

SKIP_RECIPE[ostree] = "not buildable with spaetzle"

# Disable python usage in opkg-utils since it won't build with tiny config
PACKAGECONFIG:remove:pn-opkg-utils = "python"

require conf/distro/include/tq-common.inc

# tinification of tq-common.inc (mostly similar to init-manager-mdev-busybox.inc).
# start with default ...
INIT_MANAGER = "mdev-busybox"
VIRTUAL-RUNTIME_dev_manager = "eudev"
VIRTUAL-RUNTIME_login_manager = "busybox"
VIRTUAL-RUNTIME_init_manager = "busybox"
VIRTUAL-RUNTIME_initscripts = "initscripts"
VIRTUAL-RUNTIME_keymaps = "keymaps"

# include additional udev rules for eudev
DISTRO_EXTRA_RDEPENDS += "tq-udev-rules"

# point some runtime providers to busybox
PREFERRED_PROVIDER_virtual/base-utils = "busybox"
VIRTUAL-RUNTIME_base-utils = "busybox"
VIRTUAL-RUNTIME_base-utils-hwclock = "busybox-hwclock"

IMAGE_FSTYPES += "${@bb.utils.contains('MACHINE_FEATURES', 'ubi', 'ubi', '', d)}"
