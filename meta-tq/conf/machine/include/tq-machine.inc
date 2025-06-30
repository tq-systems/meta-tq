# SPDX-License-Identifier: MIT
# Copyright (C) 2025 TQ-Systems GmbH <oss@ew.tq-group.com>, D-82229 Seefeld, Germany.

# settings to be included by <machine>.conf
# this keeps reusability and easier settings for custom boards

KERNEL_IMAGETYPES_INSTALL = "\
    ${@oe.utils.all_distro_features(d, 'tiny', \
        d.getVar('KERNEL_IMAGETYPE'), \
        d.getVar('KERNEL_IMAGETYPES'), \
    )} \
"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS += "\
    kernel-devicetree \
"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS += "\
    ${@ ' '.join( \
        ['kernel-image-' + type.lower() \
            for type in d.getVar('KERNEL_IMAGETYPES_INSTALL').split() \
        ] \
    )} \
"

MACHINE_EXTRA_RRECOMMENDS += "\
    kernel-modules \
"
