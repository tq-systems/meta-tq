# SPDX-License-Identifier: MIT
# SPDX-FileCopyrightText: Copyright (C) 2026 TQ-Systems GmbH <oss@ew.tq-group.com>

# u-boot-imx-tq is the TQ-maintained NXP i.MX U-Boot fork. Its BPN
# ("u-boot-imx-tq") is unknown in the NVD; set the same CVE_PRODUCT as
# meta-freescale uses for its u-boot-imx recipes so that CVE lookups
# correctly match both the NXP SPL entry and the upstream U-Boot entry.
CVE_PRODUCT = "nxp:uboot_secondary_program_loader denx:u-boot"
