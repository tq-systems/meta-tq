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

# Only install kernel-devicetree if a non-fitImage kernel is installed
MACHINE_ESSENTIAL_EXTRA_RDEPENDS += "\
    ${@oe.utils.ifelse(oe.utils.str_filter_out('^fitImage$', d.getVar('KERNEL_IMAGETYPES_INSTALL'), d) != '', \
        'kernel-devicetree', \
        '', \
    )} \
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
