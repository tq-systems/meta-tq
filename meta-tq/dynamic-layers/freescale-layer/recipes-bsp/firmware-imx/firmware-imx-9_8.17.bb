# Copyright (C) 2018-2020 NXP
# Copyright (C) 2022 TQ-Systems GmbH
SUMMARY = "NXP i.MX firmware for i.MX9 family"
DESCRIPTION = "NXP i.MX firmware for i.MX9 family"

require recipes-bsp/firmware-imx/firmware-imx-${PV}.inc

inherit deploy

do_install[noexec] = "1"

do_deploy() {
    # Synopsys DDR
    for ddr_firmware in ${DDR_FIRMWARE_NAME}; do
        install -m 0644 ${S}/firmware/ddr/synopsys/${ddr_firmware} ${DEPLOYDIR}
    done
}
addtask deploy after do_install before do_build

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "(mx9-generic-bsp)"
