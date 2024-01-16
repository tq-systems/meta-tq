# SPDX-License-Identifier: MIT
#
# Copyright (c) 2024 TQ-Systems GmbH <oss@ew.tq-group.com>,
# D-82229 Seefeld, Germany.

# Mimic meta-freescale master feature in classes/use-imx-security-controller-firmware.bbclass
IMX_SOC_REV_LOWER   = "${@d.getVar('IMX_SOC_REV').lower()}"
IMX_SOC_REV_UPPER   = "${@d.getVar('IMX_SOC_REV').upper()}"
SECO_FIRMWARE_NAME:mx93-nxp-bsp = "mx93${IMX_SOC_REV_LOWER}-ahab-container.img"
