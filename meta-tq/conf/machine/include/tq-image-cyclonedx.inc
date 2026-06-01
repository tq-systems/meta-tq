# SPDX-License-Identifier: MIT
# SPDX-FileCopyrightText: Copyright (C) 2026 TQ-Systems GmbH <oss@ew.tq-group.com>
#
# Bootloader and firmware components deployed via EXTRA_IMAGEDEPENDS are
# invisible to standard rootfs package discovery. List them here so that
# do_deploy_cyclonedx includes them in the SBOM.

# Resolve virtual/bootloader to the actual PN for the current machine.
# cyclonedx-export looks up per-recipe JSON files by PN, not by virtual target.
CYCLONEDX_EXTRA_RUNTIME_RECIPES_BOOTLOADER = \
    "${@ d.getVar('PREFERRED_PROVIDER_virtual/bootloader') or ''}"

# --- i.MX (NXP) ---

# Common ATF + bootloader stack for all i.MX ARM64 machines.
# Resolve ATF via IMX_DEFAULT_ATF_PROVIDER (set in tqmxx.inc / imx-base.inc).
# Resolve boot container via PREFERRED_PROVIDER_imx-boot (set per machine include).
CYCLONEDX_EXTRA_RUNTIME_RECIPES_IMX_ARM8A = "\
    ${@ d.getVar('IMX_DEFAULT_ATF_PROVIDER') or ''} \
    ${@ d.getVar('PREFERRED_PROVIDER_imx-boot') or ''} \
    ${CYCLONEDX_EXTRA_RUNTIME_RECIPES_BOOTLOADER} \
"

# LPDDR training firmware required on i.MX 8M / 91 / 93 (SPL + DRAM init).
CYCLONEDX_EXTRA_RUNTIME_RECIPES_IMX_SPLRAM = "\
    imx-boot-firmware-files \
"

# System Controller Firmware (SCFW) used on i.MX 8QM and i.MX 8X / 8DX / 8DXL.
# Resolve via PREFERRED_PROVIDER_imx-sc-firmware (set in tqma8xx-common.inc / tqma8x.inc).
CYCLONEDX_EXTRA_RUNTIME_RECIPES_IMX_SCU = "\
    ${@ d.getVar('PREFERRED_PROVIDER_imx-sc-firmware') or ''} \
"

# NXP SECO (Security Controller) firmware used on i.MX 8QM and i.MX 8X / 8DX / 8DXL.
CYCLONEDX_EXTRA_RUNTIME_RECIPES_IMX_SECO = "\
    imx-seco \
"

# Edge Lock Enclave (ELE) firmware used on i.MX 91 / 93 / 95.
CYCLONEDX_EXTRA_RUNTIME_RECIPES_IMX_ELE = "\
    firmware-ele-imx \
"

# i.MX95 with SCP (system control processor) aka system manager
CYCLONEDX_EXTRA_RUNTIME_RECIPES_IMX_SCP = "\
    ${@ d.getVar('PREFERRED_PROVIDER_virtual/imx-oei') or ''} \
    ${@ d.getVar('PREFERRED_PROVIDER_virtual/imx-system-manager') or ''} \
    imx-boot-firmware-files \
"

CYCLONEDX_EXTRA_RUNTIME_RECIPES:append:mx8m-generic-bsp = "\
    ${CYCLONEDX_EXTRA_RUNTIME_RECIPES_IMX_ARM8A} \
    ${CYCLONEDX_EXTRA_RUNTIME_RECIPES_IMX_SPLRAM} \
    tq-bootscripts \
"
CYCLONEDX_EXTRA_RUNTIME_RECIPES:append:mx91-generic-bsp = "\
    ${CYCLONEDX_EXTRA_RUNTIME_RECIPES_IMX_ARM8A} \
    ${CYCLONEDX_EXTRA_RUNTIME_RECIPES_IMX_SPLRAM} \
    ${CYCLONEDX_EXTRA_RUNTIME_RECIPES_IMX_ELE} \
    tq-bootscripts \
"
CYCLONEDX_EXTRA_RUNTIME_RECIPES:append:mx93-generic-bsp = "\
    ${CYCLONEDX_EXTRA_RUNTIME_RECIPES_IMX_ARM8A} \
    ${CYCLONEDX_EXTRA_RUNTIME_RECIPES_IMX_SPLRAM} \
    ${CYCLONEDX_EXTRA_RUNTIME_RECIPES_IMX_ELE} \
    tq-bootscripts \
"
CYCLONEDX_EXTRA_RUNTIME_RECIPES:append:mx95-generic-bsp = "\
    ${CYCLONEDX_EXTRA_RUNTIME_RECIPES_IMX_ARM8A} \
    ${CYCLONEDX_EXTRA_RUNTIME_RECIPES_IMX_SCP} \
    ${CYCLONEDX_EXTRA_RUNTIME_RECIPES_IMX_ELE} \
    tq-bootscripts \
"
# mx8-generic-bsp is intentionally NOT used for SCU/SECO: it also covers i.MX 8M machines,
# which do not use SCFW or SECO.
# imx-boot-firmware-files deploys Cadence HDMI firmware for mx8qm (not LPDDR training).
# imx-boot-firmware-files is NOT compatible with mx8x-generic-bsp (excluded upstream).
CYCLONEDX_EXTRA_RUNTIME_RECIPES:append:mx8qm-generic-bsp = "\
    ${CYCLONEDX_EXTRA_RUNTIME_RECIPES_IMX_ARM8A} \
    ${CYCLONEDX_EXTRA_RUNTIME_RECIPES_IMX_SCU} \
    ${CYCLONEDX_EXTRA_RUNTIME_RECIPES_IMX_SECO} \
    imx-boot-firmware-files \
"
CYCLONEDX_EXTRA_RUNTIME_RECIPES:append:mx8x-generic-bsp = "\
    ${CYCLONEDX_EXTRA_RUNTIME_RECIPES_IMX_ARM8A} \
    ${CYCLONEDX_EXTRA_RUNTIME_RECIPES_IMX_SCU} \
    ${CYCLONEDX_EXTRA_RUNTIME_RECIPES_IMX_SECO} \
"

# i.MX 6 / 7 (ARMv7) — no ATF, bootloader only.
CYCLONEDX_EXTRA_RUNTIME_RECIPES_IMX_ARM7A = "\
    ${CYCLONEDX_EXTRA_RUNTIME_RECIPES_BOOTLOADER} \
"

CYCLONEDX_EXTRA_RUNTIME_RECIPES:append:tqma6x = " ${CYCLONEDX_EXTRA_RUNTIME_RECIPES_IMX_ARM7A}"
CYCLONEDX_EXTRA_RUNTIME_RECIPES:append:tqma6ulx = " ${CYCLONEDX_EXTRA_RUNTIME_RECIPES_IMX_ARM7A}"
CYCLONEDX_EXTRA_RUNTIME_RECIPES:append:tqma6ullx = " ${CYCLONEDX_EXTRA_RUNTIME_RECIPES_IMX_ARM7A}"
CYCLONEDX_EXTRA_RUNTIME_RECIPES:append:tqma7x = " ${CYCLONEDX_EXTRA_RUNTIME_RECIPES_IMX_ARM7A}"

# --- QorIQ (NXP Layerscape / LX2) ---
# tqmlsx covers LS1012A, LS1028A, LS1043A, LS1046A, LS1088A, LX2160A (ARM64).
CYCLONEDX_EXTRA_RUNTIME_RECIPES:append:tqmlsx = "\
    qoriq-atf \
    rcw \
    ${CYCLONEDX_EXTRA_RUNTIME_RECIPES_BOOTLOADER} \
"
# tqmls102xa (LS1021A, ARM32) has no tqmlsx in MACHINEOVERRIDES, so there is no overlap.
CYCLONEDX_EXTRA_RUNTIME_RECIPES:append:tqmls102xa = " ${CYCLONEDX_EXTRA_RUNTIME_RECIPES_BOOTLOADER}"

# --- TI AM335x (Sitara, ARMv7) ---
# tqma335x (AM335x, Cortex-A8) — no ATF, bootloader only.
CYCLONEDX_EXTRA_RUNTIME_RECIPES:append:tqma335x = " ${CYCLONEDX_EXTRA_RUNTIME_RECIPES_BOOTLOADER}"

# --- TI K3 (AM62x / AM64x / AM67x) ---
# Common stack for all K3 machines:
#   u-boot-tq      — A53 stage (tispl.bin + u-boot.img) plus R5 stage (tiboot3.bin)
#                    built via the k3r5 multiconfig; same recipe covers both builds.
#   ti-sci-fw      — TI SYSFW/TIFS binary embedded into tiboot3.bin by the R5 u-boot build.
#   trusted-firmware-a — ATF for the A53 secure world.
#   optee-os       — OP-TEE for the A53 secure world.
#   tq-bootscripts — TQ boot scripts deployed to the boot partition.
CYCLONEDX_EXTRA_RUNTIME_RECIPES_TI_K3 = "\
    ${CYCLONEDX_EXTRA_RUNTIME_RECIPES_BOOTLOADER} \
    ti-sci-fw \
    trusted-firmware-a \
    optee-os \
    tq-bootscripts \
"

CYCLONEDX_EXTRA_RUNTIME_RECIPES:append:tqma62xx = " ${CYCLONEDX_EXTRA_RUNTIME_RECIPES_TI_K3}"
CYCLONEDX_EXTRA_RUNTIME_RECIPES:append:tqma64xxl = " ${CYCLONEDX_EXTRA_RUNTIME_RECIPES_TI_K3}"
CYCLONEDX_EXTRA_RUNTIME_RECIPES:append:tqma67xx = " ${CYCLONEDX_EXTRA_RUNTIME_RECIPES_TI_K3}"
