# Copyright 2021-2022 NXP
SUMMARY = "NXP i.MX Sentinel firmware"
DESCRIPTION = "Firmware for i.MX Sentinel Security Controller"
SECTION = "base"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://COPYING;md5=5a0bf11f745e68024f37b4724a5364fe"

inherit fsl-eula-unpack deploy

SRC_URI = "${FSL_MIRROR}/${BP}.bin;fsl-eula=true"
SRC_URI[md5sum] = "9d4a60e4629460d19aa87527e82a2f8f"
SRC_URI[sha256sum] = "53de08ed62c28b1287ee9bd45021c50a6970e0b1a48baf7b8c30213d8a7f242a"

do_compile[noexec] = "1"
do_install[noexec] = "1"

do_deploy () {
    # Deploy the related firmware to be package by imx-boot
    install -m 0644 ${S}/${SECO_FIRMWARE_NAME} ${DEPLOYDIR}
}
addtask deploy after do_install before do_build

# TODO:
# meta-imx has here: `COMPATIBLE_MACHINE = "(mx8ulp-nxp-bsp|mx9-nxp-bsp)"`
# at the moment there is no mx8ulp nor mx9 support in meta-freescale
# so lets add imx93 for now to support our upcomming TQMa93xx SOM
# family and allow using with mainline kernel
COMPATIBLE_MACHINE = "(mx93-generic-bsp)"
