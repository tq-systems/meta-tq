# SPDX-License-Identifier: MIT
#
# Copyright (c) 2026 TQ-Systems GmbH <oss@ew.tq-group.com>, D-82229 Seefeld, Germany.
# Author: Markus Niebel
#
# The kernel versions in use by this layer implements systemd support unconditionally.
# The default install location for systemd units is what Open Embedded encodes
# with the 'usrmerge' feature.
#
# This bbappend can be removed when
#
# * all kernel versions in use make systemd support optional
# * upstream cpupower recipe handles the optional support as config option
#

inherit features_check

REQUIRED_DISTRO_FEATURES += "usrmerge"

EXTRA_OEMAKE += "unitdir=${systemd_unitdir}"

do_install:append() {
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'false', 'true', d)}; then
        rm -rf "${D}/${sysconfdir}/cpupower-service.conf"
        rm -rf "${D}/${systemd_unitdir}"
    fi
}
