# SPDX-License-Identifier: MIT
# Copyright (C) 2025 TQ-Systems GmbH <oss@ew.tq-group.com>, D-82229 Seefeld, Germany.

# settings to be included by <machine>.conf
# this keeps reusability and easier settings for custom boards

KERNEL_IMAGETYPES_INSTALL = "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'tiny', \
        d.getVar('KERNEL_IMAGETYPE'), \
        d.getVar('KERNEL_IMAGETYPES'), \
        d, \
    )} \
"

FIT_IMAGE ?= ""
# Only install kernel-image and kernel-devicetree if not fitimage is installed
MACHINE_ESSENTIAL_EXTRA_RDEPENDS += "\
    ${FIT_IMAGE} \
    ${@oe.utils.conditional('FIT_IMAGE', '', 'kernel-devicetree', '', d)} \
    ${@oe.utils.conditional('FIT_IMAGE', '', ' '.join( \
        ['kernel-image-' + type.lower() \
            for type in d.getVar('KERNEL_IMAGETYPES_INSTALL').split() \
        ] \
    ), '', d)} \
"

MACHINE_EXTRA_RRECOMMENDS += "\
    kernel-modules \
"
