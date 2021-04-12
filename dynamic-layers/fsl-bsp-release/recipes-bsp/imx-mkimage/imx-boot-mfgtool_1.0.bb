# Copyright 2017-2018 NXP

require imx-boot_${PV}.bb

BOOT_NAME = "imx-boot-mfgtool"
PROVIDES = "${BOOT_NAME}"
DEPLOYDIR_IMXBOOT = "${BOOT_NAME}"
BOOT_CONFIG_MACHINE = "${BOOT_NAME}-${MACHINE}-mfgtool.bin"

# NOTE: flexspi target leads to non working image, so restrict to e-MMC/SD
IMXBOOT_TARGETS_mx8qxp = "flash_spl"

UBOOT_NAME = "u-boot-${MACHINE}-mfgtool.${UBOOT_SUFFIX}"
UBOOT_SPL_NAME = "${@os.path.basename(d.getVar("SPL_BINARY"))}-mfgtool-${MACHINE}"

# This package aggregates output deployed by other packages,
# so set the appropriate dependencies

do_compile[depends] += " \
    u-boot-mfgtool:do_deploy \
    ${@' '.join('%s:do_deploy' % r for r in '${IMX_EXTRA_FIRMWARE}'.split() )} \
    imx-atf:do_deploy \
    ${@bb.utils.contains('MACHINE_FEATURES', 'optee', 'optee-os:do_deploy', '', d)} \
"
