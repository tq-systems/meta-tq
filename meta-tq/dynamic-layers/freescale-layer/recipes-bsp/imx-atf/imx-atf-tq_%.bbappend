# SPDX-License-Identifier: MIT
# SPDX-FileCopyrightText: Copyright (C) 2026 TQ-Systems GmbH <oss@ew.tq-group.com>

# imx-atf-tq is the TQ-maintained NXP fork of ARM Trusted Firmware (TF-A).
# Its BPN ("imx-atf-tq") is unknown in the NVD; map it to the upstream
# CPE so that CVE lookups match NVD entries for arm:trusted_firmware-a.
CVE_PRODUCT = "arm:trusted_firmware-a"

# CVE-2023-31339 is specific to AMD Zynq UltraScale+ MPSoC/RFSoC platforms.
# This TF-A fork targets NXP platforms exclusively; the affected Zynq-specific
# code path is never compiled for TQ boards.
CVE_STATUS[CVE-2023-31339] = "not-applicable-platform: AMD Zynq UltraScale+ specific vulnerability. This TF-A fork targets NXP platforms exclusively; the affected Zynq-specific code path is never compiled."
