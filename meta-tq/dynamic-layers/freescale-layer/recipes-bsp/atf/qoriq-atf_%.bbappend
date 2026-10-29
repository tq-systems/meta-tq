# SPDX-License-Identifier: MIT
# SPDX-FileCopyrightText: Copyright (C) 2026 TQ-Systems GmbH <oss@ew.tq-group.com>

# qoriq-atf is the NXP QorIQ fork of ARM Trusted Firmware (TF-A).
# Its BPN ("qoriq-atf") is unknown in the NVD; map it to the upstream
# CPE so that CVE lookups match NVD entries for arm:trusted_firmware-a.
CVE_PRODUCT = "arm:trusted_firmware-a"

# CVE-2023-31339 is specific to AMD Zynq UltraScale+ MPSoC/RFSoC platforms.
# This TF-A fork targets NXP platforms exclusively; the affected Zynq-specific
# code path is never compiled for TQ boards.
CVE_STATUS[CVE-2023-31339] = "not-applicable-platform: AMD Zynq UltraScale+ specific vulnerability. This TF-A fork targets NXP platforms exclusively; the affected Zynq-specific code path is never compiled."

# DEPENDS is platform specific and shall contain all packages needed to build
# the TF-A image.
# cyclonedx-export class documentation states, that assigning CYCLONEDX_EXPORT_DEPENDS
# with DEPENDS will filter all packages with suffixes listed in SPECIAL_PKGSUFFIX
# from CYCLONEDX_EXPORT_DEPENDS before collecting SBOM.
CYCLONEDX_EXPORT_DEPENDS = "${DEPENDS}"
# remove for CycloneDX, virtual/cross-cc is expanded here
CYCLONEDX_EXPORT_DEPENDS:remove = "${PREFERRED_PROVIDER_virtual/cross-cc}"

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
CYCLONEDX_IMAGE_NAME = "boot-firmware-${MACHINE}"
# Since we create no version (yet) leave it empty
CYCLONEDX_IMAGE_VERSION = ""

# Add source URL and git references. Should be done global to have all of them
# in resulting SBOM
CYCLONEDX_ADD_COMPONENT_SOURCE_REFERENCES = "1"
