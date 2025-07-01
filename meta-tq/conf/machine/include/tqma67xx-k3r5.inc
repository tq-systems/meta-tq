# SPDX-License-Identifier: MIT
# Copyright (C) 2025 TQ-Systems GmbH <oss@ew.tq-group.com>, D-82229 Seefeld, Germany.

# Common definitions for R5 bootloader on TQMa67xx

MACHINEOVERRIDES =. "tqma67xx-k3r5:"

# From meta-ti
require conf/machine/include/k3r5.inc

# The tiboot3.bin filename is built from these variables. The sysfw
# configuration can be adjusted in the U-Boot source.
SYSFW_SOC = "j722s"
SYSFW_CONFIG = "tqma67xx"
SYSFW_SUFFIX = "hs-fs"

PREFERRED_PROVIDER_virtual/bootloader = "u-boot-tq"
PREFERRED_PROVIDER_u-boot = "u-boot-tq"
