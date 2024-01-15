# SPDX-License-Identifier: MIT
# Copyright (C) 2024 TQ-Systems GmbH <oss@ew.tq-group.com>, D-82229 Seefeld, Germany.

# We will have tar.gz for NFS, wic for SD/eMMC
#
# It is set unconditionally and should be included after SoC includes to override differing
# settings from vendor layers.
IMAGE_FSTYPES = "tar.gz wic wic.bmap"
