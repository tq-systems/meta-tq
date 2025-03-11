# SPDX-License-Identifier: MIT
# Copyright (C) 2025 TQ-Systems GmbH <oss@ew.tq-group.com>, D-82229 Seefeld, Germany.

# settings to be included by <machine>.conf
# this keeps reusability and easier settings for custom boards

MACHINE_EXTRA_RRECOMMENDS += "\
    kernel-devicetree \
    kernel-image \
    kernel-modules \
"
