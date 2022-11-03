SUMMARY = "WiFi/Bluetooth firmware for Marvell/NXP W8987 (SDIO/UART version)"
DESCRIPTION = "WiFi/Bluetooth firmware for Marvell/NXP W8987, \
which is currently not covered by the linux-firmware package. \
\
This variant of the firmware uses SDIO for WiFi and UART for Bluetooth."

SECTION = "kernel"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://EULA.txt;md5=be5ff43682ed6c57dfcbeb97651c2829"

SRC_URI = "git://github.com/NXP/imx-firmware.git;protocol=https;branch=lf-5.15.52_2.1.0"
SRCREV = "b6f070e3d4cab23932d9e6bc29e3d884a7fd68f4"

S = "${WORKDIR}/git"

inherit allarch

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${nonarch_base_libdir}/firmware/mrvl
    install -m 0644 nxp/FwImage_8987/sdiouart8987_combo_v0.bin ${D}${nonarch_base_libdir}/firmware/mrvl/sd8987_uapsta.bin
}

# Alternative provider for mrvl/sd8987_uapsta.bin - not packaged at the moment
RCONFLICTS:${PN} += "linux-firmware-nxp-w8987-sdio"

RPROVIDES:${PN} += "linux-firmware-nxp-w8987"
FILES:${PN} += " \
    ${nonarch_base_libdir}/firmware/mrvl/ \
"
