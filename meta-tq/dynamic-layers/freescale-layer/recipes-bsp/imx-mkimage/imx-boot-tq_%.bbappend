# SPDX-License-Identifier: MIT
#
# Copyright (c) 2026 TQ-Systems GmbH <oss@ew.tq-group.com>, D-82229 Seefeld, Germany.
# Author: Markus Niebel

# The bbappend is not bound to a specific version although the recipe version was
# not updated for a long time. This allows local copies of the recipe with
# version precedence to use the logic of this bbappend.

# DEPENDS is platform specific and shall contain all packages needed to build
# the boot image.
# cyclonedx-export class documentation states, that assigning CYCLONEDX_EXPORT_DEPENDS
# with DEPENDS will filter all packages with suffixes listed in SPECIAL_PKGSUFFIX
# from CYCLONEDX_EXPORT_DEPENDS before collecting SBOM.
CYCLONEDX_EXPORT_DEPENDS = "${DEPENDS}"

# conditionally generate SBOM for recipe: add the task and dependencies based on
# 'cyclonedx-export' in INHERIT to prevent warning for non-existing task function

do_deploy[recrdeptask] += "${@bb.utils.contains_any('INHERIT', 'cyclonedx-export', \
    'do_populate_cyclonedx', '', d)}"

python () {
    if bb.utils.contains('INHERIT', 'cyclonedx-export', True, False, d):
        bb.build.addtask('do_deploy_cyclonedx', 'do_build', 'do_deploy', d)
}

# meta data configuration:

# This is the same base name as used to generate the artifacts
CYCLONEDX_IMAGE_NAME = "${BOOT_NAME}-${MACHINE}"
# Since we create no version (yet) leave it empty
CYCLONEDX_IMAGE_VERSION = ""

# Add source URL and git references. Should be done global to have all of them
# in resulting SBOM
CYCLONEDX_ADD_COMPONENT_SOURCE_REFERENCES = "1"
