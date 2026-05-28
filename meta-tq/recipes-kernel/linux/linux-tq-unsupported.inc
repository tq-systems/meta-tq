# SPDX-License-Identifier: MIT
#
# Copyright (c) 2026 TQ-Systems GmbH <oss@ew.tq-group.com>, D-82229 Seefeld, Germany.
# Author: Markus Niebel
#

python () {
    if d.getVar("PREFERRED_PROVIDER_virtual/kernel") == d.getVar("PN"):
        bb.fatal("This recipe and the BSP configuration is completely unmaintained, please update to new LTS branch ASAP.")
}
