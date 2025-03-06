
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "\
    file://0001-ddr-imx9-limit-visibility-of-SAVED_DRAM_TIMING_BASE.patch \
    file://0002-imx9-scmi-Add-missing-UART-clocks.patch \
    file://0003-imx9-native-Add-missing-UART-clocks.patch \
    file://0004-mach-imx-spl-fix-MCU-RDC-config-for-i.MX8MN-i.MX8MP.patch \
    file://0005-fdt-Correct-condition-for-devicetree-from-bloblist.patch \
    file://0006-arm-dts-imx8mp-HACK-add-nodes-to-enable-usage-of-ups.patch \
    file://0007-arm-dts-imx8mp-pinfunc.h-add-upstream-names-for-USB-.patch \
    file://0008-tq-tq_board_gpio-add-missing-include.patch \
    file://0009-net-fec-mxc-prevent-crash-if-no-MAC-address-is-set.patch \
    file://0010-net-phy-add-phy-reset-code-to-phy_connect.patch \
    file://0011-tq-add-missing-definitions-for-private-bloblist-entr.patch \
    file://0012-arm-dts-imx8mp-tqma8mpql-mba8mpxl-import-from-linux-.patch \
    file://0013-arm-dts-tqma8mpql-add-U-Boot-dtsi-fragments.patch \
    file://0014-tq-add-TQMa8MPxL-SoM-and-starter-kit.patch \
    file://0015-tqma8mpxl-mba8mpxl-UART-EFI-EVK-sync.patch \
    file://0016-tqma8mpxl-UART-WDOG-mux.patch \
    file://0017-tqma8mpxl-WIP-rely-on-board_interface_eth_init.patch \
    file://0018-board-tq-tqma8mpxl-spl.c-do-not-call-board_init_r-di.patch \
    file://0019-tqma8mpxl-add-guards-for-BLOBLIST-related-code.patch \
    file://0020-tqma8mpxl-add-defconfigs.patch \
    file://0021-fixup-arm-dts-tqma8mpql-add-U-Boot-dtsi-fragments.patch \
    file://0022-fixup-tq-add-TQMa8MPxL-SoM-and-starter-kit.patch \
    file://0023-fixup-arm-dts-tqma8mpql-add-U-Boot-dtsi-fragments.patch \
    file://0024-fixup-tq-add-TQMa8MPxL-SoM-and-starter-kit.patch \
    file://0025-boards-tqma8mpxl-WIP-tqma8mpxl-mba8mp-ras314.patch \
    file://0026-tqma8mpxl-add-tqma8mpxl_multi_mba8mp_ras314_defconfi.patch \
    file://0027-tqma8mpxl-remove-USB-MMC-SPL-support.patch \
    file://0028-tqma8mpxl-defconfigs-increase-SPL-SIZE-to-192K-0x300.patch \
    file://0029-tqma8mpxl-defconfig-move-BLOBLIST-to-start-of-RAM.patch \
    file://0030-imx8mp-tqma8mpql-u-boot.dtsi-cleanup.patch \
    file://0031-tqma8mpxl-board-env-fix.patch \
    file://0032-tqma93xx-board-env-fix.patch \
    file://0033-ddr-imx-phy-ddrphy_train.c-debug.patch \
    file://0034-tqma8mpxl-defconfig-switch-CONFIG_BOOTCOMMAND-to-dis.patch \
"

#
#    file://0035-drivers-ddr-imx-phy-ddrphy_train.c-remove-debug.patch \
#

DISTROBOOT_EXTRA_SOURCES = ""

DISTROBOOT_EXTRA_SOURCES:tqma91xx = "\
    file://loadaddr.cfg \
"

DISTROBOOT_EXTRA_SOURCES:tqma93xx = "\
    file://loadaddr.cfg \
"

DISTROBOOT_EXTRA_SOURCES:tqma8mpxl = "\
    file://loadaddr.cfg \
"

SRC_URI += "${DISTROBOOT_EXTRA_SOURCES}"

COMPATIBLE_MACHINE:tqma8mpxl = "tqma8mpxl"
